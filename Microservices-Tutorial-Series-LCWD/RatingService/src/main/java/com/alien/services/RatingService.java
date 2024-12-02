package com.alien.services;

import java.util.List;

import com.alien.entities.Rating;

public interface RatingService {

	Rating create(Rating rating);

	// get all ratings
	List<Rating> getRatings();

	// get all by UserId
	List<Rating> getRatingByUserId(String userId);

	// get all by hotel
	List<Rating> getRatingByHotelId(String hotelId);

}
