package com.project.simplyfly.model;



import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="customer")
public class Customer {
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
private int id;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getContact() {
	return contact;
}
public void setContact(String contact) {
	this.contact = contact;
}
public User getUser() {
	return user;
}
public void setUser(User user) {
	this.user = user;
}
private String name;
private String contact;
@OneToOne(cascade = CascadeType.PERSIST)
@JoinColumn(name = "user_id")
private User user;




}
