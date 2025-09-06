package com.acharya.smart_welfarre.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Schemes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generated ID
    private Long id;
    private String schemeName;
    private String description; // Renamed to follow convention
    private String criteria;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSchemeName() {
        return schemeName;
    }

    public void setSchemeName(String schemeName) {
        this.schemeName = schemeName;
    }
    public String getDescription() { // Getter name remains consistent with functionality
        return description; // Returns the renamed field
    }

    public void setDescription(String description) { // Setter name remains consistent with functionality
        this.description = description; // Sets the renamed field
    }

    public String getCriteria() {
        return criteria;
    }

    public void setCriteria(String criteria) {
        this.criteria = criteria;
    }
}