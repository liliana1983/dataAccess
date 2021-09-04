package com.iktpreobuka.dataaccess.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iktpreobuka.dataaccess.entities.AddressEntity;
import com.iktpreobuka.dataaccess.repositories.AddressRepository;
import com.iktpreobuka.dataaccess.services.AddressDao;

@RestController
@RequestMapping("/api/v1/addresses")
public class AddressController {
	@Autowired
	private AddressRepository addressRepository;
	@Autowired
	private AddressDao addressService;
	@RequestMapping(method = RequestMethod.POST)
	public AddressEntity createAddress(@RequestParam String street, @RequestParam String country,
			@RequestParam String city) {
		AddressEntity address = new AddressEntity();
		address.setCity(city);
		address.setCountry(country);
		address.setStreet(street);
		
		return addressRepository.save(address);

	}
	@GetMapping()
	public Iterable<AddressEntity> getAll(){
		return addressRepository.findAll();
	}
	@RequestMapping(method=RequestMethod.GET,path="/user/{name}")
	public List<AddressEntity> findAddressByUsername(@PathVariable String name){
		return addressService.findAddressByUsername(name);
	}
}
