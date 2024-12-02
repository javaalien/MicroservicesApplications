package com.alien.service;

import java.util.List;

import com.alien.entities.Hotel;

public interface HotelService {

	Hotel create(Hotel hotel);

	// get all
	List<Hotel> getAll();

	// get single
	Hotel get(String id);

}
