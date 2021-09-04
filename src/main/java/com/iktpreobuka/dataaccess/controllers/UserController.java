package com.iktpreobuka.dataaccess.controllers;

import java.time.LocalDate;
import java.util.List;

import javax.naming.directory.AttributeInUseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iktpreobuka.dataaccess.entities.AddressEntity;
import com.iktpreobuka.dataaccess.entities.UserEntity;
import com.iktpreobuka.dataaccess.repositories.AddressRepository;
import com.iktpreobuka.dataaccess.repositories.UserRepository;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
	@Autowired
	private UserRepository userrepository;
	@Autowired
	private AddressRepository addressRepository;

	@RequestMapping(method = RequestMethod.POST)
	public UserEntity createUser(@RequestParam String name, @RequestParam String email, LocalDate dateOfBirth) {
		UserEntity user = new UserEntity();
		user.setEmail(email);
		user.setName(name);
		user.setDateOfBirth(dateOfBirth);
		UserEntity retUser = userrepository.save(user);
		return retUser;
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<UserEntity> getAll() {
		return (List<UserEntity>) userrepository.findAll();
	}

	@RequestMapping(method = RequestMethod.PUT, path = "/{id}/address")
	public UserEntity addAddress(@PathVariable Integer id, @RequestParam Integer addressId) {
		UserEntity user = userrepository.findById(id).get();
		AddressEntity address = addressRepository.findById(addressId).get();
		user.setAddress(address);
		return userrepository.save(user);
	}

/*@GetMapping("/by-email")
	public UserEntity findByEmail(@RequestParam String email) {
		return userrepository.findByEmail(email);*/
	}

	/*@GetMapping("/by-name")
	public UserEntity findByName(@RequestParam String name) {
		return userrepository.findByName(name);
	}
*/
//	@GetMapping("/by-dob")
//	public List<UserEntity> findByDateOfBirth(@RequestParam @DateTimeFormat(iso = ISO.DATE) LocalDate date) {
//		return userrepository.findByDateOfBirthOrderByNameAsc(date);
//	}
/*	@GetMapping("/by-name-first-letter")
	public List<UserEntity> findAllByFirstLetterOfName(@RequestParam String firstLetter) {
		return userrepository.findAllByFirstLetterOfName(firstLetter);
	}
	@GetMapping("/by-name-starts-with")
	public List<UserEntity> findByNameStartsWith(@RequestParam String firstLetter) {
		return ((UserRepository) userrepository).findByNameStartsWith(firstLetter);
	}
	//@RequestMapping(method = RequestMethod.GET, value = "/city")
//	public List<UserEntity> livingCity(@RequestParam String city) {
	//	AddressEntity address = addressRepository.findByCity(city);
//
	//	return getAll();
//	}

}*/
