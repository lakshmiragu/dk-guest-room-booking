package com.dk.guest.room.booking.data.repository;

import org.springframework.data.repository.CrudRepository;

import com.dk.guest.room.booking.data.model.Account;

public interface AccountRepository extends CrudRepository<Account, Integer> {

}
