# dk-guest-room-booking
 Guest Room Booking Application

Techonologies and Tools used:
Java 8
SpringBoot 2.5.2
Spring FrameWork
MySQL 8 Database

 Spring Tool Suite IDE / Eclipse
 MySQL Workbench 
 Postman

 1. create database schema in MySql
 	CREATE SCHEMA 'guest_room_booking_schema' ;
 	(Note: please refer to /dk-guest-room-booking/sql-script/guest_room_booking.sql)
 	
 2. Start dk-guest-room-booking application as a SpringBoot App
 	As the property spring.jpa.hibernate.ddl-auto=update is configured in application.properties,
 	tables and sequences get created during application startup.
 	
 3. Import following file into Postman.
  /dk-guest-room-booking/postman/GuestRoomBookingApplication.postman_collection.json 	
  
Executing Happy-Path using Postman Collection:

1. Create Owner
2. Create Customer
3. Create Room
4. Create Booking

By submitting above requests one by one from Postman would
	create an Owner (ownerId=1),
	create a Customer (customerId=2), 
	create a Room for owner 1 (roomId=1, ownerId=1)
	and
	create a booking for customer 2 and room 1 for the date 2021-12-13



Note:
Following endpoints are available:
1. Create Owner
POST: http://localhost:8080/owners

2. View Owner
GET: http://localhost:8080/owners/{id}

3. Create Customer
POST: http://localhost:8080/customers

4. View Customer
GET: http://localhost:8080/customers/{id}

5. Create Room
POST: http://localhost:8080/rooms

6. View Room
GET: http://localhost:8080/rooms/{id}

7. Edit Room
PUT: http://localhost:8080/rooms

8. Delete Room
DELETE: http://localhost:8080/rooms/{id}

9. View Room Availability
GET: http://localhost:8080/rooms/{id}/datesbooked

10. View All Room
GET: http://localhost:8080/rooms

11. Upload Photo
POST: http://localhost:8080/rooms/{id}/uploadphoto

12. Create Booking
POST: http://localhost:8080/bookings

13. View Booking
GET: http://localhost:8080/bookings/{id}








