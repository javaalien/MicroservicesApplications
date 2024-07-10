package com.alien.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alien.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer>{

	Payment findByOrderId(int orderId);
	
}
