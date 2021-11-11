package com.dk.guest.room.booking;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;


@RestController
public class AccountController {
	
	@Autowired
	AccountRepository accountRepository;
	
	@GetMapping("/accounts/{id}")
	public Account viewAccount(@PathVariable Integer id) throws IOException{
		System.out.println("Reading the Account id = " + id);
		
		Optional<Account> optional = accountRepository.findById(id);
		if(optional.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Account doesn't exist");
		}
		return optional.get();
	}
	
	@PostMapping("/accounts")
	public Account createAccount(@RequestBody Account account) throws IOException{
		System.out.println("***Creating the Account***");
		if(account.getAccountId() != null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Account Id should be empty to create an account");
		}
		accountRepository.save(account);
		return account;
	}
	
	@PutMapping("/accounts")
	public Account updateAccount(@RequestBody Account account) throws IOException{
		System.out.println("***updating the Account***");
		if(account.getAccountId() == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Account Id should be empty to update an existing account");
		}
		accountRepository.save(account);
		return account;
	}

	

}
