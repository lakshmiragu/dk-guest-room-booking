package com.dk.guest.room.booking.data.repository;

import org.springframework.data.repository.CrudRepository;

import com.dk.guest.room.booking.data.model.Room;

public interface RoomRepository extends CrudRepository<Room, Long> {

}
