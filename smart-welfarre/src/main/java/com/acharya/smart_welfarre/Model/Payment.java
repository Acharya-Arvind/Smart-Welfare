package com.acharya.smart_welfarre.Model;

import com.acharya.smart_welfarre.Controller.Controller_data;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generated ID
    private Long id;
    private String name=Controller_data.logedinusername;
    private String usermail =Controller_data.logedinusermail;
    private Long schemeid;
    private String schemeName;
    private Long donationAmount;
    private String cardNumber;

    public long getSchemeid() {
        return schemeid;
    }

    public void setSchemeid(Long schemeid) {
        this.schemeid = schemeid;
    }

    public String expirationDate;
    public String cvv;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsermail() {
        return usermail;
    }

    public void setUsermail(String usermail) {
        this.usermail = usermail;
    }

    public String getSchemeName() {
        return schemeName;
    }

    public void setSchemeName(String schemeName) {
        this.schemeName = schemeName;
    }

    public Long getDonationAmount() {
        return donationAmount;
    }

    public void setDonationAmount(Long donationAmount) {
        this.donationAmount = donationAmount;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }
}
