package com.taxpal.taxpal.service;

import com.taxpal.taxpal.model.UserIncome;
import com.taxpal.taxpal.model.UserIncomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaxCalculationService {

    @Autowired
    private UserIncomeRepository userIncomeRepository;

    // 1. Tax Estimation (No DB save)
    public double calculateTax(double income, double investment) {
        double taxableIncome = income - Math.min(investment, 150000);
        return calculateTaxAmount(taxableIncome);
    }

    // 2. Save UserIncome to DB
    public void saveUserIncome(UserIncome userIncome) {
        userIncomeRepository.save(userIncome);
    }

    // 3. Get all saved UserIncome entries
    public List<UserIncome> getAllUserIncomes() {
        return userIncomeRepository.findAll();
    }
    public UserIncome getUserIncomeByEmail(String email) {
        return userIncomeRepository.findByEmail(email);
    }


    // 4. Get tax for user by email (from DB)
    public double calculateTaxForUser(String email) {
        UserIncome user = userIncomeRepository.findByEmail(email);
        if (user == null) {
            throw new RuntimeException("User with email " + email + " not found");
        }
        double taxableIncome = user.getIncome() - Math.min(user.getInvestment(), 150000);
        return calculateTaxAmount(taxableIncome);
    }

    // Utility method to calculate tax slab-wise
    private double calculateTaxAmount(double taxableIncome) {
        if (taxableIncome <= 250000) {
            return 0;
        } else if (taxableIncome <= 500000) {
            return (taxableIncome - 250000) * 0.05;
        } else if (taxableIncome <= 1000000) {
            return (250000 * 0.05) + (taxableIncome - 500000) * 0.2;
        } else {
            return (250000 * 0.05) + (500000 * 0.2) + (taxableIncome - 1000000) * 0.3;
        }
    }
}
