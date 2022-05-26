package com.dk.guest.room.booking.controller;

import java.io.IOException;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.dk.guest.room.booking.data.model.Account;
import com.dk.guest.room.booking.data.model.Booking;
import com.dk.guest.room.booking.data.repository.AccountRepository;
import com.dk.guest.room.booking.data.repository.BookingRepository;

/*
 * BookingController - process requests to create and view bookings
 * 
 */
@RestController
public class BookingController {

	private static Logger logger = LoggerFactory.getLogger(BookingController.class);

	@Autowired
	BookingRepository bookingRepository;

	@Autowired
	AccountRepository accountRepository;

	/*
	 * viewBookingRoom process GET request to view the details of a booking
	 * 
	 * id - booking id
	 * 
	 * @return Booking - details of the booking {id}
	 */
	@GetMapping("/bookings/{id}")
	public Booking viewBookingRoom(@PathVariable Long id) throws IOException {
		logger.info("Reading the Booking Room id = " + id);

		Optional<Booking> optional = bookingRepository.findById(id);
		if (optional.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Booking Room doesn't exist");
		}
		return optional.get();
	}

	/*
	 * createBookingRoom process POST request to create the booking room
	 * 
	 * id - booking id
	 * 
	 * @return Booking - returns created booking with new booking id
	 */
	@PostMapping("/bookings")
	public Booking createBookingRoom(@RequestBody Booking booking) throws IOException {
		logger.info("***Booking the room***");
		logger.info(booking.toString());

		if (booking.getBookingId() != null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Booking Id should be empty to booking a room");
		}
		if (booking.getCustomerId() == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"Customer Id should be provided to booking a room ");
		}

		Optional<Account> optionalAccount = accountRepository.findByAccountIdAndType(booking.getCustomerId(),
				"customer");
		if (optionalAccount.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer doesn't exist");
		}

		Optional<Booking> optionalBooking = bookingRepository.findByRoomIdAndDateOfBooking(booking.getRoomId(),
				booking.getDateOfBooking());

		if (optionalBooking.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Room already booked for the date asked");
		}

		// Confirming the booking by creating a new booking record
		bookingRepository.save(booking);

		return booking;
	}

}
