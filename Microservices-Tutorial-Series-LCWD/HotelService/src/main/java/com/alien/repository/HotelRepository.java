package com.alien.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alien.entities.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, String> {
}
