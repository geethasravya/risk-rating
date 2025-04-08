package com.example.demo.model;

import javax.persistence.*;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int instances;
    private int exposure;

    @Transient
    private int vendorReputation;
    @Transient
    private int vulnerabilities;
    @Transient
    private String riskRating;

    public Product() {}
    public Product(String name, int instances, int exposure) {
        this.name = name;
        this.instances = instances;
        this.exposure = exposure;
    }

    public String getRiskRating() {
        int riskValue = vendorReputation * vulnerabilities * instances * exposure;
        if (riskValue > 125) return "High";
        else if (riskValue > 25) return "Medium";
        else return "Low";
    }

    // getters and setters omitted for brevity
}