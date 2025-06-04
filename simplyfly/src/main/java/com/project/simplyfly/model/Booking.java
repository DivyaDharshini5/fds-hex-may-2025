package com.project.simplyfly.model;

import java.time.LocalDate;

import com.project.simplyfly.enums.Status;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="booking")
public class Booking {
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
private int id;
@Column(name="booking_date")
private LocalDate booking_date;
@Enumerated(EnumType.STRING)
private Status status;
@Column(name="total_price")
private double total_price;
@ManyToOne
private Customer customer;
@ManyToOne
private Flight flight;
@ManyToOne
private Coupon coupon;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public LocalDate getBooking_date() {
	return booking_date;
}
public void setBooking_date(LocalDate booking_date) {
	this.booking_date = booking_date;
}

public Status getStatus() {
	return status;
}
public void setStatus(Status status) {
	this.status = status;
}
public double getTotal_price() {
	return total_price;
}
public void setTotal_price(double total_price) {
	this.total_price = total_price;
}
public Customer getCustomer() {
	return customer;
}
public void setCustomer(Customer customer) {
	this.customer = customer;
}
public Flight getFlight() {
	return flight;
}
public void setFlight(Flight flight) {
	this.flight = flight;
}
public Coupon getCoupon() {
	return coupon;
}
public void setCoupon(Coupon coupon) {
	this.coupon = coupon;
}

}
