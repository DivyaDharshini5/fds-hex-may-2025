package com.project.simplyfly.dto;

import java.time.LocalDateTime;

public class FlightDto {
	private int id;
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
	public Double getPrice_per_person() {
		return price_per_person;
	}
	public void setPrice_per_person(Double price_per_person) {
		this.price_per_person = price_per_person;
	}
	public int getSeatsAvailable() {
		return seatsAvailable;
	}
	public void setSeatsAvailable(int seatsAvailable) {
		this.seatsAvailable = seatsAvailable;
	}
	private String number;
    private String origin;
    private String destination;
    private LocalDateTime departure_time;
    private LocalDateTime arrival_time;
    private Double price_per_person;
    private int seatsAvailable;
}
