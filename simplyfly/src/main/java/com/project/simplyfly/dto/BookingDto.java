package com.project.simplyfly.dto;

import java.time.LocalDate;

import com.project.simplyfly.enums.Status;

public class BookingDto {
    private int id;
    private LocalDate booking_date;
    private Status status;
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
	public void setStatus(Status string) {
		this.status = string;
	}
	public Double getTotal_price() {
		return total_price;
	}
	public void setTotal_price(Double total_price) {
		this.total_price = total_price;
	}
	public CustomerDto getCustomer() {
		return customer;
	}
	public void setCustomer(CustomerDto customer) {
		this.customer = customer;
	}
	public PassengerDto getPassenger() {
		return passenger;
	}
	public void setPassenger(PassengerDto passenger) {
		this.passenger = passenger;
	}
	public FlightDto getFlight() {
		return flight;
	}
	public void setFlight(FlightDto flight) {
		this.flight = flight;
	}
	public CouponDto getCoupon() {
		return coupon;
	}
	public void setCoupon(CouponDto coupon) {
		this.coupon = coupon;
	}
	private Double total_price;

    private CustomerDto customer;
    private PassengerDto passenger;
    private FlightDto flight;
    private CouponDto coupon;
}

