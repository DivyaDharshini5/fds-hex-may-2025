package com.project.simplyfly.dto;

import java.time.LocalDate;

import com.project.simplyfly.enums.PaymentType;

public class PaymentDto {
	private int id;
    public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public LocalDate getPayment_date() {
		return payment_date;
	}
	public void setPayment_date(LocalDate payment_date) {
		this.payment_date = payment_date;
	}
	public double getAmount_paid() {
		return amount_paid;
	}
	public void setAmount_paid(double amount_paid) {
		this.amount_paid = amount_paid;
	}
	public String getTransaction_id() {
		return transaction_id;
	}
	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public PaymentType getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
	}
	public BookingDto getBooking() {
		return booking;
	}
	public void setBooking(BookingDto booking) {
		this.booking = booking;
	}
	private LocalDate payment_date;
    private double amount_paid;
    private String transaction_id;
    private String status;
    private PaymentType paymentType;
    private BookingDto booking;
}
