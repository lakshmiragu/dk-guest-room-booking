package com.dk.guest.room.booking.data.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.dk.guest.room.booking.data.model.Account;

public interface AccountRepository extends CrudRepository<Account, Long> {

	Optional<Account> findByAccountIdAndType(Long accountId, String type);

}
