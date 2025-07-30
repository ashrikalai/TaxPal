package com.taxpal.taxpal.controller;

import com.taxpal.taxpal.model.UserIncome;
import com.taxpal.taxpal.service.TaxCalculationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tax")
@CrossOrigin(origins = "*") // Optional: allows CORS requests
public class TaxController {

    @Autowired
    private TaxCalculationService taxCalculationService;

    // 1. Estimate tax without saving
    @GetMapping("/estimate")
    public double estimateTax(@RequestParam double income, @RequestParam double investment) {
        return taxCalculationService.calculateTax(income, investment);
    }

    // 2. Save user income and investment (RESTful)
    @PostMapping("/user-income")
    public ResponseEntity<UserIncome> saveUserIncome(@RequestBody UserIncome userIncome) {
        taxCalculationService.saveUserIncome(userIncome);
        return new ResponseEntity<>(userIncome, HttpStatus.CREATED);
    }

    // 3. Get all users' data
    @GetMapping("/all")
    public List<UserIncome> getAllUsers() {
        return taxCalculationService.getAllUserIncomes();
    }

    // 4. Get tax for a specific user by email
    @GetMapping("/user-tax")
    public double getTaxByEmail(@RequestParam String email) {
        return taxCalculationService.calculateTaxForUser(email);
    }

    // 5. Get income details by email
    @GetMapping("/user-income/{email}")
    public ResponseEntity<UserIncome> getUserIncome(@PathVariable String email) {
        UserIncome income = taxCalculationService.getUserIncomeByEmail(email);
        return new ResponseEntity<>(income, HttpStatus.OK);
    }
    @PostMapping("/save")
    public ResponseEntity<?> saveUserData(@RequestBody UserIncome userIncome) {
        try {
            taxCalculationService.saveUserIncome(userIncome);
            return ResponseEntity.status(HttpStatus.CREATED).body("Saved!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
        }
    }

}
