package com.alien.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alien.entities.Hotel;
import com.alien.exceptions.ResourceNotFoundException;
import com.alien.repository.HotelRepository;
import com.alien.service.HotelService;

@Service
public class HotelServiceImpl implements HotelService {

	@Autowired
	private HotelRepository hotelRepository;

	@Override
	public Hotel create(Hotel hotel) {

		return hotelRepository.save(hotel);
	}

	@Override
	public List<Hotel> getAll() {

		return hotelRepository.findAll();
	}

	@Override
	public Hotel get(String id) {

		return hotelRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("hotel with given id not found !!"));
	}
}
