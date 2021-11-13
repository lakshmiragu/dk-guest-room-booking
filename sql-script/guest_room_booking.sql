CREATE SCHEMA 'guest_room_booking_schema' ;

CREATE TABLE 'account' (
  'account_id' bigint NOT NULL,
  'email' varchar(255) DEFAULT NULL,
  'full_name' varchar(255) DEFAULT NULL,
  'mobile_number' varchar(255) DEFAULT NULL,
  'type' varchar(255) DEFAULT NULL,
  PRIMARY KEY ('account_id')
) ;

CREATE TABLE 'booking' (
  'booking_id' bigint NOT NULL,
  'customer_id' bigint DEFAULT NULL,
  'date_of_booking' date DEFAULT NULL,
  'room_id' bigint DEFAULT NULL,
  PRIMARY KEY ('booking_id')
) ;

CREATE TABLE 'room' (
  'room_id' bigint NOT NULL,
  'address' varchar(255) DEFAULT NULL,
  'amenities' varchar(255) DEFAULT NULL,
  'floor_size' varchar(255) DEFAULT NULL,
  'max_booking_period' int DEFAULT NULL,
  'min_booking_period' int DEFAULT NULL,
  'name' varchar(255) DEFAULT NULL,
  'number_of_beds' varchar(255) DEFAULT NULL,
  'owner_id' bigint DEFAULT NULL,
  'rent_per_day' double DEFAULT NULL,
  PRIMARY KEY ('room_id')
) ;

CREATE TABLE 'account_sequence' (
  'next_val' bigint DEFAULT NULL
) ;

CREATE TABLE 'booking_sequence' (
  'next_val' bigint DEFAULT NULL
) ;

CREATE TABLE 'room_sequence' (
  'next_val' bigint DEFAULT NULL
) ;