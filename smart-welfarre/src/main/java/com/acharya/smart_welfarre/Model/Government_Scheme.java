package com.acharya.smart_welfarre.Model;

import com.acharya.smart_welfarre.Controller.Controller_data;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.util.Date;

@Entity
public class Government_Scheme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generated ID
    private Long id;
    private String name=Controller_data.logedinusername;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String schemeName;
    private String usermail =Controller_data.logedinusermail;
    @CreationTimestamp
    @Column(nullable = false, updatable = false) // updatable = false prevents updates
    private LocalDate date;

    private String status = "PENDING";
    private String application_status="UNDER REVIEW";

    public String getApplication_status() {
        return application_status;
    }

    public void setApplication_status(String application_status) {
        this.application_status = application_status;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setSchemeName(String schemeName) {
        this.schemeName = schemeName;
    }



    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
