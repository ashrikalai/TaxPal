package com.taxpal.taxpal.model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface InvestmentRepository extends JpaRepository<Investment, Long> {
}
