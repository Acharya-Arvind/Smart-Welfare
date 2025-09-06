package com.acharya.smart_welfarre.Model;

import jakarta.persistence.*;


import java.util.Date;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Personal Details
    private String name;
    private int age;
    private String gender;
    private String email;
    private String password;
    private String phone;
//    private Date dob;
    private String status="VERIFIED USER";

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Address Details
    private String address;
    private String city;
    private String state;
    private String pincode;

    // Income Details
    private double annualIncome;
    private String employmentStatus;

    // Document Uploads (using BLOB to store file data)
    private String aadharnumber;

    @Lob
    @Basic(fetch = FetchType.LAZY) // For handling large data
    private byte[] photo;      // Store the photo as a byte array

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] aproof; // Store the address proof as a byte array

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] iproof;  // Store the income proof as a byte array

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public byte[] getAproof() {
        return aproof;
    }

    public void setAproof(byte[] aproof) {
        this.aproof = aproof;
    }

    public byte[] getIproof() {
        return iproof;
    }

    public void setIproof(byte[] iproof) {
        this.iproof = iproof;
    }

    public Long getId() {
        return id;
    }

    public String getAadharCardNumber() {
        return aadharnumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

//    public Date getDob() {
//        return dob;
//    }
//
//    public void setDob(Date dob) {
//        this.dob = dob;
//    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public double getAnnualIncome() {
        return annualIncome;
    }

    public void setAnnualIncome(double annualIncome) {
        this.annualIncome = annualIncome;
    }

    public String getEmploymentStatus() {
        return employmentStatus;
    }

    public void setEmploymentStatus(String employmentStatus) {
        this.employmentStatus = employmentStatus;
    }

    public String getAadharnumber() {
        return aadharnumber;
    }

    public void setAadharnumber(String aadharnumber) {
        this.aadharnumber = aadharnumber;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
