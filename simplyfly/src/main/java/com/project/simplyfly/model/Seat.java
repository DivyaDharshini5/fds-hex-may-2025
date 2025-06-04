package com.project.simplyfly.model;

import com.project.simplyfly.enums.SeatType;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Seat {
@Id	
@GeneratedValue(strategy=GenerationType.IDENTITY)
private int id;
private String seatNumber;

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getSeatNumber() {
	return seatNumber;
}

public void setSeatNumber(String seatNumber) {
	this.seatNumber = seatNumber;
}

public SeatType getSeatType() {
	return seatType;
}

public void setSeatType(SeatType seatType) {
	this.seatType = seatType;
}

public Boolean getIs_reserved() {
	return is_reserved;
}

public void setIs_reserved(Boolean is_reserved) {
	this.is_reserved = is_reserved;
}

public Flight getFlight() {
	return flight;
}

public void setFlight(Flight flight) {
	this.flight = flight;
}

public Passenger getPassenger() {
	return passenger;
}

public void setPassenger(Passenger passenger) {
	this.passenger = passenger;
}

private SeatType seatType;
private Boolean is_reserved=false;
@ManyToOne
@JoinColumn(name="flight_id")
private Flight flight; 

@ManyToOne
@JoinColumn(name="passenger_id")
private Passenger passenger;
}
