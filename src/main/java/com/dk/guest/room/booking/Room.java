package com.dk.guest.room.booking;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Room {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Integer roomId;
	String ownerId;
	String name;
	String floorSize;
	String numberOfBeds;
	String amenities;
	String address;
	Integer minBookingPeriod;
	Integer maxBookingPeriod;
	Double rentPerDay;

	public Integer getRoomId() {
		return roomId;
	}

	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
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