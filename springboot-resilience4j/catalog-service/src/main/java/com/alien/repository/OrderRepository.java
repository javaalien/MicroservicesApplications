package com.alien.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alien.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {

	List<Order> findByCategory(String category);

}
