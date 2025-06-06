package com.project.simplyfly.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="passenger")
public class Passenger {
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
private int id;
@Column(name="full_name")
private String full_name;
private int age;
private String gender;

public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getFull_name() {
	return full_name;
}
public void setFull_name(String full_name) {
	this.full_name = full_name;
}
public int getAge() {
	return age;
}
public void setAge(int age) {
	this.age = age;
}
public String getGender() {
	return gender;
}
public void setGender(String gender) {
	this.gender = gender;
}
public String getPassport_no() {
	return passport_no;
}
public void setPassport_no(String passport_no) {
	this.passport_no = passport_no;
}
public Customer getCustomer() {
	return customer;
}
public void setCustomer(Customer customer) {
	this.customer = customer;
}
@Column(name="passport_no")
private String passport_no;
@ManyToOne
@JoinColumn(name="customer_id")
private Customer customer;
}
