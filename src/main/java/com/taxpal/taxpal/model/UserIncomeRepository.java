package com.taxpal.taxpal.model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserIncomeRepository extends JpaRepository<UserIncome, Long> {
    UserIncome findByEmail(String email);
}
