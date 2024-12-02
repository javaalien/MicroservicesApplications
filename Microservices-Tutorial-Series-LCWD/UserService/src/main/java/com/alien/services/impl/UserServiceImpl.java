package com.alien.services.impl;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import com.alien.entities.Hotel;
import com.alien.entities.Rating;
import com.alien.entities.User;
import com.alien.exceptions.ResourceNotFoundException;
import com.alien.external.services.HotelService;
import com.alien.repository.UserRepository;
import com.alien.services.UserService;

public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private HotelService hotelService;

	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Override
	public User saveUser(User user) {
		String randomUserId = UUID.randomUUID().toString();
		user.setUserId(randomUserId);
		return userRepository.save(user);
	}

	@Override
	public List<User> getAllUser() {
		return userRepository.findAll();
	}

	@Override
	public User getUser(String userId) {

		// get user from database with the help of user repository

		User user = userRepository.findById(userId).orElseThrow(
				() -> new ResourceNotFoundException("User with given id is not found on server !! : " + userId));

		// fetch rating of the above user from RATING SERVICE
		// http://localhost:8083/ratings/users/47e38dac-c7d0-4c40-8582-11d15f185fad

		Rating[] ratingsOfUser = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/" + user.getUserId(),
				Rating[].class);

		logger.info("{} ", ratingsOfUser);

		List<Rating> ratings = Arrays.stream(ratingsOfUser).toList();

		List<Rating> ratingList = ratings.stream().map(rating -> {
			
			// api call to hotel service to get the hotel
			// http://localhost:8082/hotels/1cbaf36d-0b28-4173-b5ea-f1cb0bc0a791
			// ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/"+rating.getHotelId(), Hotel.class);
			
			Hotel hotel = hotelService.getHotel(rating.getHotelId());
			rating.setHotel(hotel);
			return rating;
			
		}).collect(Collectors.toList());

		 user.setRatings(ratingList);
		 
		return user;
	}
}
