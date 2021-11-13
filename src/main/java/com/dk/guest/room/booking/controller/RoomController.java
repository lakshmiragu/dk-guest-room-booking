package com.dk.guest.room.booking.controller;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.dk.guest.room.booking.data.model.Account;
import com.dk.guest.room.booking.data.model.Booking;
import com.dk.guest.room.booking.data.model.Room;
import com.dk.guest.room.booking.data.repository.AccountRepository;
import com.dk.guest.room.booking.data.repository.BookingRepository;
import com.dk.guest.room.booking.data.repository.RoomRepository;

@RestController
public class RoomController {
	
	private static Logger logger = LoggerFactory.getLogger(RoomController.class);

	@Autowired
	RoomRepository roomRepository;

	@Autowired
	AccountRepository accountRepository;

	@Autowired
	BookingRepository bookingRepository;

	@GetMapping("/rooms/{id}/datesbooked")
	public ArrayList<LocalDate> viewRoomAvailability(@PathVariable Long id) throws IOException {
		logger.info("Reading availablity of the Room id = " + id);
		Iterable<Booking> bookings = bookingRepository.findAllByRoomId(id);

		ArrayList<LocalDate> dateBooked = new ArrayList<>();

		for (Booking b : bookings) {

			dateBooked.add(b.getDateOfBooking());

		}

		return dateBooked;
	}

	@GetMapping("/rooms/{id}")
	public Room viewRoom(@PathVariable Long id) throws IOException {
		logger.info("Reading the Room id = " + id);

		Optional<Room> optional = roomRepository.findById(id);
		if (optional.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Room doesn't exist");
		}
		return optional.get();
	}

	@GetMapping("/rooms")
	public Iterable<Room> viewAllRooms() throws IOException {
		logger.info("Reading all rooms");
		return roomRepository.findAll();
	}

	@PostMapping("/rooms")
	public Room createRoom(@RequestBody Room room) throws IOException {
		logger.debug("***Creating the room***");
		if (room.getRoomId() != null) {
			logger.error("room should be empty ");
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
		logger.info("***updating the room***");
		if (room.getRoomId() == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"Room Id should not be empty to update an existing room");
		}
		roomRepository.save(room);
		return room;
	}

	@DeleteMapping("/rooms/{id}")
	public void deleteRoom(@PathVariable Long id) throws IOException {
		logger.info("Deleting the Room id = " + id);

		Optional<Room> optional = roomRepository.findById(id);
		if (optional.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Room doesn't exist");
		}
		Room room = optional.get();
		roomRepository.delete(room);
	}

	@Value("${file.upload-dir}")
	public String fileUploadFolder;

	@PostMapping("/rooms/{id}/uploadphoto")
	public void uploadFile(@RequestParam MultipartFile file, @PathVariable Long id)
			throws IllegalStateException, IOException {

		File newFile = new File(fileUploadFolder + id + ".jpg");

		file.transferTo(newFile);
		logger.info(newFile.getAbsolutePath());

	}

}
