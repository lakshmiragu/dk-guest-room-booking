package com.dk.guest.room.booking.data.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.dk.guest.room.booking.data.model.Account;

public interface AccountRepository extends CrudRepository<Account, Integer> {

	Optional<Account> findByAccountIdAndType(Integer accountId, String type);

}
