package com.iktpreobuka.dataaccess.entities;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;
@Entity
public class UserEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
protected Integer Id;
protected String name;
protected String email;
@Column(name = "date_of_birth")
@JsonFormat(shape = JsonFormat.Shape.STRING,pattern="dd-mm-yyyy")
private LocalDate dateOfBirth;

public UserEntity(Integer id, String name, String email, LocalDate dateOfBirth) {
	super();
	Id = id;
	this.name = name;
	this.email = email;
	this.dateOfBirth = dateOfBirth;
}
@ManyToOne(cascade=CascadeType.REFRESH,fetch = FetchType.LAZY)
@JoinColumn(name="address")
private AddressEntity address;

public AddressEntity getAddress() {
	return address;
}
public void setAddress(AddressEntity address) {
	this.address = address;
}
public UserEntity(){}
public LocalDate getDateOfBirth() {
	return dateOfBirth;
}
public void setDateOfBirth(LocalDate dateOfBirth) {
	this.dateOfBirth = dateOfBirth;
}
public Integer getId() {
	return Id;
}
public void setId(Integer id) {
	Id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}

}
