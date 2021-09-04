package com.iktpreobuka.dataaccess.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iktpreobuka.dataaccess.entities.AddressEntity;
@Service
public class AddressDAOImpl implements AddressDao{
	@PersistenceContext
	private EntityManager em;
	
	
	@Override
public List<AddressEntity> findAddressByUsername(String name){
		//TODO napravi HQL upit pronalazak adrese ali samo takve na kojoj zivi user sa ovim datim usernamom
		// select* from AddressEntity a LEFT JOIN UserEntity u on a.id=u.address where u.name=:name;
		String sql=" SELECT a from AddressEntity a LEFT JOIN fetch a.users u WHERE u.name=:name";
		//TODO pozovemo HQL upit
		Query query= em.createQuery(sql);
		query.setParameter("name", name);
		//TODO obraditi vracene podatke i odraditi return
		List<AddressEntity> retVals= query.getResultList();
		return retVals;
	}
}
