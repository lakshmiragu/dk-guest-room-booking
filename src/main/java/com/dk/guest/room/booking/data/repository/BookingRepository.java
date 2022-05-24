package com.dk.guest.room.booking.data.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.dk.guest.room.booking.data.model.Booking;

public interface BookingRepository extends CrudRepository<Booking, Long> {

	Optional<Booking> findByRoomIdAndDateOfBooking(Long roomId, LocalDate dateOfBooking);

	Iterable<Booking> findAllByRoomId(Long id);

	
}
