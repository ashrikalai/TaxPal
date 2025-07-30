package com.taxpal.taxpal.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Investment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String investmentType;
    private double amount;

    // Constructors
    public Investment() {}

    public Investment(String investmentType, double amount) {
        this.investmentType = investmentType;
        this.amount = amount;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public String getInvestmentType() {
        return investmentType;
    }

    public void setInvestmentType(String investmentType) {
        this.investmentType = investmentType;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
