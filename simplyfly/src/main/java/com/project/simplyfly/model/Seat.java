package com.project.simplyfly.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.project.simplyfly.enums.SeatType;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

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
public Passenger getPassenger() {
	return passenger;
}
public void setPassenger(Passenger passenger) {
	this.passenger = passenger;
}
public SeatType getSeatType() {
	return seatType;
}
public void setSeatType(SeatType seatType) {
	this.seatType = seatType;
}
public Boolean getIsReserved() {
	return isReserved;
}
public void setIsReserved(Boolean isReserved) {
	this.isReserved = isReserved;
}
public Flight getFlight() {
	return flight;
}
public void setFlight(Flight flight) {
	this.flight = flight;
}
@OneToOne
@JoinColumn(name = "passenger_id")
@JsonBackReference
private Passenger passenger;

private SeatType seatType;
private Boolean isReserved=false;
@ManyToOne
@JoinColumn(name="flight_id")
private Flight flight; 

}
