package com.iktpreobuka.dataaccess.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.iktpreobuka.dataaccess.entities.AddressEntity;



public interface AddressRepository extends CrudRepository<AddressEntity, Integer>{

/*	public AddressEntity findByCity(String city);
	public AddressEntity findByCountry(String country);
	public List<AddressEntity> findByName(String name);*/
}
