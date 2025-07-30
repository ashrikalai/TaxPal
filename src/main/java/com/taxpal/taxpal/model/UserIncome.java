package com.taxpal.taxpal.model;

import jakarta.persistence.*;

@Entity
@Table(name = "user_income")
public class UserIncome {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String email;
	private double income;
	private double investment;
	@Column(name = "annual_income")
	private double annualIncome;

	public UserIncome() {
	}

	public UserIncome(String email, double income, double investment) {
		this.email = email;
		this.income = income;
		this.investment = investment;
	}

	public Long getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public double getIncome() {
		return income;
	}

	public double getInvestment() {
		return investment;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setIncome(double income) {
		this.income = income;
	}

	public void setInvestment(double investment) {
		this.investment = investment;
	}

	@Transient
	public double getAnnualIncome() {
		return this.income + this.investment;
	}

}
