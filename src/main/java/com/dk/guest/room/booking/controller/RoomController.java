package com.dk.guest.room.booking.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.dk.guest.room.booking.data.model.Account;
import com.dk.guest.room.booking.data.model.Booking;
import com.dk.guest.room.booking.data.model.Room;
import com.dk.guest.room.booking.data.repository.AccountRepository;
import com.dk.guest.room.booking.data.repository.BookingRepository;
import com.dk.guest.room.booking.data.repository.RoomRepository;

@RestController
public class RoomController {

	@Autowired
	RoomRepository roomRepository;

	@Autowired
	AccountRepository accountRepository;

	@Autowired
	BookingRepository bookingRepository;

	@GetMapping("/rooms/{id}/datesbooked")
	public ArrayList<LocalDate> viewRoomAvailability(@PathVariable Long id) throws IOException {
		System.out.println("Reading availablity of the Room id = " + id);
		Iterable<Booking> bookings = bookingRepository.findAllByRoomId(id);

		ArrayList<LocalDate> dateBooked = new ArrayList<>();

		for (Booking b : bookings) {

			dateBooked.add(b.getDateOfBooking());

		}

		return dateBooked;
	}

	@GetMapping("/rooms/{id}")
	public Room viewRoom(@PathVariable Long id) throws IOException {
		System.out.println("Reading the Room id = " + id);

		Optional<Room> optional = roomRepository.findById(id);
		if (optional.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Room doesn't exist");
		}
		return optional.get();
	}

	@GetMapping("/rooms")
	public Iterable<Room> viewAllRooms() throws IOException {
		System.out.println("Reading all rooms");
		return roomRepository.findAll();
	}

	@PostMapping("/rooms")
	public Room createRoom(@RequestBody Room room) throws IOException {
		System.out.println("***Creating the room***");
		if (room.getRoomId() != null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Room Id should be empty to create a room");
		}
		if (room.getOwnerId() == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Owner Id should be provided to create a room ");
		}

		Optional<Account> optional = accountRepository.findByAccountIdAndType(room.getOwnerId(), "owner");
		if (optional.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Owner doesn't exist");
		}

		roomRepository.save(room);
		return room;
	}

	@PutMapping("/rooms")
	public Room updateRoom(@RequestBody Room room) throws IOException {
		System.out.println("***updating the room***");
		if (room.getRoomId() == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"Room Id should not be empty to update an existing room");
		}
		roomRepository.save(room);
		return room;
	}

	@DeleteMapping("/rooms/{id}")
	public void deleteRoom(@PathVariable Long id) throws IOException {
		System.out.println("Deleting the Room id = " + id);

		Optional<Room> optional = roomRepository.findById(id);
		if (optional.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Room doesn't exist");
		}
		Room room = optional.get();
		roomRepository.delete(room);
	}

}
