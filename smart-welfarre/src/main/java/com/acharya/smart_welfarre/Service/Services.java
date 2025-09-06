package com.acharya.smart_welfarre.Service;

import com.acharya.smart_welfarre.Controller.Controller_data;
import com.acharya.smart_welfarre.Model.*;
import com.acharya.smart_welfarre.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

@Service
public class Services {
    @Autowired
    User_Repo user_repo;
    @Autowired
    g_scheme_Repo g_repo;
    @Autowired
    contact_Repo contact_repo;
    @Autowired
    donation_Repo donation_repo;
    @Autowired
    scheme_Repo scheme_repo;
    @Autowired // Ensure this annotation is present
    private JavaMailSender mailSender;
    @Autowired
    payment_Repo pay_repo;

    public void Add_user_data(User us) {
        user_repo.save(us);
        System.out.println("Added successfully");
    }
    public User getUser(String email){
        User user=user_repo.findByemail(email);
        return user;
    }
    public void Add_g_scheme(Government_Scheme gs){
        g_repo.save(gs);
    }
    public List<Government_Scheme> getAllApplications(){
        String email=Controller_data.logedinusermail;
        return g_repo.findByemail(email);
    }
    public void Add_query(ContactUs cu){
        contact_repo.save(cu);
    }
    public void Add_donation(Donation d){
        donation_repo.save(d);
    }


    public List<Donation> getspecificdonation(){

        return donation_repo.getdonations();
    }


    public void addScheme(Schemes s){
        scheme_repo.save(s);
    }
    public List<Schemes> getAllschemes(){
        return scheme_repo.getAllscheme();
    }
    public List<Government_Scheme> get_non_applied_applications(){
        return g_repo.get_non_applied();
    }
    public void approveApplication(Long id){
        try {

            g_repo.update_aprove(id);
            sendApprovalEmail(g_repo.getusermail(id),g_repo.getschemename(id),id);
        } catch (Exception e) {
//            System.err.println("Error during application approval for ID: " + id + " - " + e.getMessage());
            throw e; // Re-throw the exception so the controller's catch block handles it
        }
    }
    public void rejectApplication(Long id){
        try {

            g_repo.update_reject(id);
            sendRejectionEmail(g_repo.getusermail(id),g_repo.getschemename(id),id);
        } catch (Exception e) {
//            System.err.println("Error during application approval for ID: " + id + " - " + e.getMessage());
            throw e; // Re-throw the exception so the controller's catch block handles it
        }
    }
    public List<Donation> get_non_applied_donation(){
        return donation_repo.getAllDonation();
    }
    public Donation getDonation(Long id){
        return donation_repo.getDonation(id);
    }
    public void approveDonation(Long id){
        String donationname=donation_repo.getDonationname(id);
        String to=donation_repo.getDonationmail(id);
        donation_repo.update_aprove(id);
        sendApprovalEmail(to,donationname,id);
    }
    public void rejectDonation(Long id){
        String donationname=donation_repo.getDonationname(id);
        String to=donation_repo.getDonationmail(id);
        donation_repo.update_reject(id);
        sendRejectionEmail(to,donationname,id);
    }


    public void sendApprovalEmail(String to, String donationName, Long applicationId) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Your Application Approved");
        message.setText(String.format("Dear User,\n\nYour application for '%s' with Application ID %d has been approved.\n\nSincerely,\nThe Smart Welfare Team", donationName, applicationId));
        mailSender.send(message);
    }

    public void sendRejectionEmail(String to, String donationName, Long applicationId) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Your Application Rejected");
        message.setText(String.format("Dear User,\n\nYour application for '%s' with Application ID %d has been rejected.\n\nSincerely,\nThe Smart Welfare Team", donationName, applicationId));
        mailSender.send(message);
    }
    public List<Donation> getAlldonation(){
        return donation_repo.getdonations();
    }
    public List<Government_Scheme> getschemes(){
        return g_repo.getschemes();
    }
    public List<User> fetchalluser(){
        return user_repo.fetchalluser();
    }
    public void changestatus(Long id){
        user_repo.changestatus(id);
    }
    public void store_donation_data(Payment p){
        try{
            pay_repo.save(p);
            donation_repo.subamount(p.getSchemeid(),p.getDonationAmount());
        }
        catch (Exception e){
            System.out.println(e);
        }

    }
    public List<Donation> getentiredonation(){
        return donation_repo.getentiredonation();
    }

    public List<Payment> getAlltrans(){
        return pay_repo.getAlltrans();
    }

    public List<Payment> getfewtrans(){
        return pay_repo.getfewtrans(Controller_data.logedinusermail);
    }

    public boolean checkuserexist(String email){
        return user_repo.checkuserexist(email) > 0;
    }

    public boolean checkpass(String email, String pass){
        String p = user_repo.checkpass(email);
        System.out.println(p);
        return p != null && p.equals(pass);
    }

}
