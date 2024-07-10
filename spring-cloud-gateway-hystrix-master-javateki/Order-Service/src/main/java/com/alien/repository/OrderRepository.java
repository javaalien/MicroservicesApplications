package com.alien.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.alien.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {

}
