package com.taxpal.taxpal.controller;

import com.taxpal.taxpal.model.UserIncome;
import com.taxpal.taxpal.service.TaxCalculationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Controller for handling tax-related requests
 */
@RestController
@RequestMapping("/api/tax")
@CrossOrigin(origins = "*") // Allow requests from any frontend
public class TaxController {

    @Autowired
    private TaxCalculationService taxCalculationService;

    /**
     * Estimate tax from income & investment (GET)
     * URL: /api/tax/estimate?income=50000&investment=10000
     */
    @GetMapping("/estimate")
    public ResponseEntity<Map<String, Double>> estimateTax(
            @RequestParam double income,
            @RequestParam double investment
    ) {
        double tax = taxCalculationService.calculateTax(income, investment);
        return ResponseEntity.ok(Map.of("tax", tax));
    }

    /**
     * Save user income/investment data (POST)
     * JSON body: { "income": 50000, "investment": 10000, "email": "example@mail.com", ... }
     */
    @PostMapping("/user-income")
    public ResponseEntity<?> saveUserIncome(@RequestBody UserIncome userIncome) {
        if (userIncome == null || userIncome.getEmail() == null) {
            return ResponseEntity.badRequest().body(Map.of("error", "Invalid input data"));
        }
        taxCalculationService.saveUserIncome(userIncome);
        return ResponseEntity.status(HttpStatus.CREATED).body(userIncome);
    }

    /**
     * Get all users' income data
     */
    @GetMapping("/all")
    public ResponseEntity<List<UserIncome>> getAllUsers() {
        List<UserIncome> users = taxCalculationService.getAllUserIncomes();
        return ResponseEntity.ok(users);
    }

    /**
     * Get estimated tax by email
     * URL: /api/tax/user-tax?email=someone@mail.com
     */
    @GetMapping("/user-tax")
    public ResponseEntity<?> getTaxByEmail(@RequestParam String email) {
        if (email == null || email.trim().isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error", "Email is required"));
        }
        double tax = taxCalculationService.calculateTaxForUser(email);
        return ResponseEntity.ok(Map.of("tax", tax));
    }

    /**
     * Get user income by email
     * URL: /api/tax/user-income/{email}
     */
    @GetMapping("/user-income/{email}")
    public ResponseEntity<?> getUserIncome(@PathVariable String email) {
        UserIncome income = taxCalculationService.getUserIncomeByEmail(email);
        if (income == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "User not found"));
        }
        return ResponseEntity.ok(income);
    }

    /**
     * Save user data and return JSON success/error message
     */
    @PostMapping("/save")
    public ResponseEntity<Map<String, String>> saveUserData(@RequestBody UserIncome userIncome) {
        try {
            taxCalculationService.saveUserIncome(userIncome);
            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("message", "Saved!"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", e.getMessage()));
        }
    }
}
