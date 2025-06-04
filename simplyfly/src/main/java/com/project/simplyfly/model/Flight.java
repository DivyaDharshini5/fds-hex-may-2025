package com.project.simplyfly.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;

@Entity
public class Flight {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;
private String number;
private String origin;
private String destination;
private LocalDateTime departure_time;
private LocalDateTime arrival_time;
private double price_per_person;
@Column(name = "seats_available") 
private int seatsAvailable;


public int getId() {
	return id;
}


public void setId(int id) {
	this.id = id;
}


public String getNumber() {
	return number;
}


public void setNumber(String number) {
	this.number = number;
}


public String getOrigin() {
	return origin;
}


public void setOrigin(String origin) {
	this.origin = origin;
}


public String getDestination() {
	return destination;
}


public void setDestination(String destination) {
	this.destination = destination;
}


public LocalDateTime getDeparture_time() {
	return departure_time;
}


public void setDeparture_time(LocalDateTime departure_time) {
	this.departure_time = departure_time;
}


public LocalDateTime getArrival_time() {
	return arrival_time;
}


public void setArrival_time(LocalDateTime arrival_time) {
	this.arrival_time = arrival_time;
}


public double getPrice_per_person() {
	return price_per_person;
}


public void setPrice_per_person(double price_per_person) {
	this.price_per_person = price_per_person;
}


public int getSeatsAvailable() {
	return seatsAvailable;
}


public void setSeatsAvailable(int seatsAvailable) {
	this.seatsAvailable = seatsAvailable;
}


@PrePersist
//It may not be applicable for all flights
public void onCreate() {
    if (departure_time == null) {
        departure_time = LocalDateTime.now();
    }
    if (arrival_time == null) {
        arrival_time = departure_time.plusHours(2); 
    }
}

// still needs some fields
}
