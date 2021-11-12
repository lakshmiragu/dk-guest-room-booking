package com.dk.guest.room.booking.data.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
public class Room {

	@Id
    @GeneratedValue(generator = "sequence-generator")
    @GenericGenerator(
      name = "sequence-generator",
      strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
      parameters = {
        @Parameter(name = "sequence_name", value = "room_sequence"),
        @Parameter(name = "initial_value", value = "1"),
        @Parameter(name = "increment_size", value = "1")
        }
    )
	Long roomId;
	Long ownerId;
	String name;
	String floorSize;
	String numberOfBeds;
	String amenities;
	String address;
	Integer minBookingPeriod;
	Integer maxBookingPeriod;
	Double rentPerDay;

	public Long getRoomId() {
		return roomId;
	}

	public void setRoomId(Long roomId) {
		this.roomId = roomId;
	}

	public Long getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFloorSize() {
		return floorSize;
	}

	public void setFloorSize(String floorSize) {
		this.floorSize = floorSize;
	}

	public String getNumberOfBeds() {
		return numberOfBeds;
	}

	public void setNumberOfBeds(String numberOfBeds) {
		this.numberOfBeds = numberOfBeds;
	}

	public String getAmenities() {
		return amenities;
	}

	public void setAmenities(String amenities) {
		this.amenities = amenities;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getMinBookingPeriod() {
		return minBookingPeriod;
	}

	public void setMinBookingPeriod(Integer minBookingPeriod) {
		this.minBookingPeriod = minBookingPeriod;
	}

	public Integer getMaxBookingPeriod() {
		return maxBookingPeriod;
	}

	public void setMaxBookingPeriod(Integer maxBookingPeriod) {
		this.maxBookingPeriod = maxBookingPeriod;
	}

	public Double getRentPerDay() {
		return rentPerDay;
	}

	public void setRentPerDay(Double rentPerDay) {
		this.rentPerDay = rentPerDay;
	}

	@Override
	public String toString() {
		return "Room [roomId=" + roomId + ", ownerId=" + ownerId + ", name=" + name + ", floorSize=" + floorSize
				+ ", numberOfBeds=" + numberOfBeds + ", amenities=" + amenities + ", address=" + address
				+ ", minBookingPeriod=" + minBookingPeriod + ", maxBookingPeriod=" + maxBookingPeriod + ", rentPerDay="
				+ rentPerDay + "]";
	}
		
}