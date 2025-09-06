package com.acharya.smart_welfarre.Controller;

import com.acharya.smart_welfarre.Model.*;
import com.acharya.smart_welfarre.Repository.User_Repo;
import com.acharya.smart_welfarre.Service.Services;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.*;

@Controller
public class Controllers {
    @Autowired
    Services ser;
    @Autowired
    User_Repo user_repo;
    private static final Logger logger = LoggerFactory.getLogger(Controller.class);

    @GetMapping("/")
    public String login() {
        return "login";
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/G-scheme")
    public String G_scheme(Model model) {
        List<Schemes> schemes=ser.getAllschemes();
        model.addAttribute("schemes",schemes);
        return "scheme";
    }

    @GetMapping("/app-status")
    public String A_status(Model model) {
        List<Government_Scheme> applications=ser.getAllApplications();
        List<Donation> applications1=ser.getAlldonation();
        List<Payment> applications2=ser.getfewtrans();
        model.addAttribute("applications", applications);
        model.addAttribute("applications1", applications1);
        model.addAttribute("applications2", applications2);
        return "status";
    }

    @GetMapping("/contact")
    public String contact() {
        return "contactus";
    }

    @GetMapping("/Signup-front")
    public String signup_front() {
        return "newprofile";
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/";
    }

    @PostMapping("/Signup-back")
    public String signup(@ModelAttribute User us) {
        ser.Add_user_data(us);
        return "login";
    }

    @GetMapping("/profile")
    public String profile(Model model){
        User user=ser.getUser(Controller_data.logedinusermail);
        model.addAttribute("user", user);
        return "profile";
    }

    @PostMapping("/apply-scheme")
    @ResponseBody
    public String apply_scheme(@ModelAttribute Government_Scheme gs) {
        ser.Add_g_scheme(gs);
        return "<script>window.location.href = \"/G-scheme\";window.history.back(); alert(\"Successfully Applied\");</script>";
    }

    @PostMapping("/Contact-us")
    @ResponseBody
    public String contact_us(@ModelAttribute ContactUs cu){
        ser.Add_query(cu);
        return "<script>window.location.href = \"/contact\";window.history.back(); alert(\"Successfully Sent\");</script>";
    }

    @GetMapping("/apply-donation")
    public String apply_donation(){
        return "apply_donation";
    }

    @PostMapping("/ask-donate")
    @ResponseBody
    public String ask_donation(@ModelAttribute Donation d){
        ser.Add_donation(d);
        return "<script>window.location.href = \"/apply-donation\";window.history.back(); alert(\"Successfully Applied\");</script>";
    }

    @GetMapping("/donate")
    public String donate(Model model) {
        List<Donation> donate=ser.getspecificdonation();
        model.addAttribute("donate",donate);
        return "confirmed_donation";
    }

    @GetMapping("/admin-home")
    public String admin_home(){
        return "adminhome";
    }

    @GetMapping("/add-gov-scheme")
    public String add_gov_scheme(){
        return "addgovscheme";
    }

    @PostMapping("/get-scheme")
    @ResponseBody
    public String add_scheme(@ModelAttribute Schemes s) {
        ser.addScheme(s);
        return "<script>window.location.href = \"/addgovscheme\";alert('Scheme data collected');window.history.back();document.getElementById('addSchemeForm').reset();</script>";
    }

    @GetMapping("/approve-application")
    public String approve_application(Model model){
        List<Government_Scheme> appli=ser.get_non_applied_applications();
        model.addAttribute("applications",appli);
        return "approve_scheme";
    }

    @GetMapping("/users/{email}")
    @ResponseBody
    public ResponseEntity<User> getUserDetails(@PathVariable String email) {
        try {
            User user = ser.getUser(email);
            if (user == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            logger.error("Error fetching user details for email: " + email, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @PostMapping("/application/{id}/approve")
    public ResponseEntity<?> approveApplication(@PathVariable("id") Long id) {
        try {
            ser.approveApplication(id);
            return ResponseEntity.ok().body(Map.of("message", "Application approved successfully"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Failed to approve application"));
        }
    }
    @PostMapping("/application/{id}/reject")
    public ResponseEntity<?> rejectApplication(@PathVariable("id") Long id) {
        try {
            ser.rejectApplication(id);
            return ResponseEntity.ok().body(Map.of("message", "Application rejected successfully"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Failed to reject application"));
        }
    }

    @GetMapping("/approve-donation")
    public String approve_donation(Model model){
        List<Donation> appli=ser.get_non_applied_donation();
        model.addAttribute("applications",appli);
        return "approve_donation";
    }
    @GetMapping("/users/donation/{id}")
    @ResponseBody
    public ResponseEntity<Donation> getDonationDetails(@PathVariable Long id) {
        try {
            Donation user = ser.getDonation(id);
            if (user == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            logger.error("Error fetching user details for id: " + id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @PostMapping("/donation/{id}/approve")
    public ResponseEntity<?> approveDonation(@PathVariable("id") Long id) {
        try {
            ser.approveDonation(id);
            return ResponseEntity.ok().body(Map.of("message", "Application approved successfully"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Failed to approve application"));
        }
    }
    @PostMapping("/donation/{id}/reject")
    public ResponseEntity<?> rejectDonation(@PathVariable("id") Long id) {
        try {
            ser.rejectDonation(id);
            return ResponseEntity.ok().body(Map.of("message", "Application Rejected successfully"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Failed to reject application"));
        }
    }
    @GetMapping("/approved-donation")
    public String approveddonation(Model model){
        List<Donation> app=ser.getentiredonation();
        model.addAttribute("applications1",app);
        return "approved_donation";
    }
    @GetMapping("/approved-scheme")
    public  String approvedschemes(Model model){
        List<Government_Scheme> applications=ser.getschemes();
        model.addAttribute("applications", applications);
        return "approved_scheme";
    }
    @GetMapping("user-manage")
    public String usermanagement(Model model){
        List<User> app=ser.fetchalluser();
                model.addAttribute("applications1",app);
        return "user_management";
    }

    @GetMapping("/user/status/{id}")
    public ResponseEntity<?> changestatus(@PathVariable("id") Long id) {

        try {
            ser.changestatus(id);

            return ResponseEntity.ok().body(Map.of("message", "User status changed"));
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Failed to change status"));
        }
    }
    @PostMapping("/store-donation-data")
    @ResponseBody
    public String storedonationdata(@ModelAttribute Payment p){
        ser.store_donation_data(p);
        return "<script>window.history.back();window.location.href = \"/donate\";</script>";
    }

    @GetMapping("/donation_transaction")
    public String donation_transaction(Model model){
        List<Payment> app=ser.getAlltrans();
        model.addAttribute("applications",app);
        return "donation_transaction";
    }
    @PostMapping("/admin-login")
    @ResponseBody
    public String admin_login(@RequestParam("admin-email") String adminEmail,
                              @RequestParam("admin-password") String adminPassword){

        System.out.println(adminEmail+" "+adminPassword);
        if(adminEmail.equals("admin@gmail.com") && adminPassword.equals("admin")) return "<script>window.location.href = '/admin-home';</script>";
         return "<script>alert('Invalid Details!');window.location.href = \"/\";</script></script>";

    }
    @PostMapping("/user-login")
    @ResponseBody
    public String user_login(@RequestParam("login-email") String email,
                             @RequestParam("login-password") String password){
        if(ser.checkuserexist(email)){
            if(ser.checkpass(email,password)) {
                Controller_data.logedinusername= user_repo.getusername(email);
                Controller_data.logedinusermail=email;
                System.out.println(Controller_data.logedinusermail+" "+Controller_data.logedinusername);
                return "<script>window.location.href = '/home';</script>";
            }
            else{
                return "<script>alert('Invalid Password!'); window.location.href = \"/\";</script>;</script>";
            }
        } else {
            return "<script>alert('Invalid Username!'); window.location.href = \"/\";</script></script>";
        }
    }

}