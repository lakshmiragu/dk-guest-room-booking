package com.dk.guest.room.booking;

import java.io.IOException;
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

@RestController
public class RoomController {
	
	@Autowired
	RoomRepository roomRepository;
	
	
	@GetMapping("/rooms/{id}")
	public Room viewRoom(@PathVariable Integer id) throws IOException{
		System.out.println("Reading the Room id = " + id);
		
		Optional<Room> optional = roomRepository.findById(id);
		if(optional.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Room doesn't exist");
		}
		return optional.get();
	}
	
	@PostMapping("/rooms")
	public Room createRoom(@RequestBody Room room) throws IOException{
		System.out.println("***Creating the room***");
		if(room.getRoomId() != null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Room Id should be empty to create a room");
		}
		roomRepository.save(room);
		return room;
	}
	
	@PutMapping("/rooms")
	public Room updateRoom(@RequestBody Room room) throws IOException{
		System.out.println("***updating the room***");
		if(room.getRoomId() == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Room Id should not be empty to update an existing room");
		}
		roomRepository.save(room);
		return room;
	}
	
	@DeleteMapping("/rooms/{id}")
	public void deleteRoom(@PathVariable Integer id) throws IOException{
		System.out.println("Deleting the Room id = " + id);
		
		Optional<Room> optional = roomRepository.findById(id);
		if(optional.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Room doesn't exist");
		}
		Room room = optional.get();
		roomRepository.delete(room);
	}

}
