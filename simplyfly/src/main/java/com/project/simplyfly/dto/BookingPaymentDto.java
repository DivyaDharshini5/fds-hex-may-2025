package com.project.simplyfly.dto;

import java.util.List;

import com.project.simplyfly.enums.CouponType;
import com.project.simplyfly.enums.PaymentType;

public class BookingPaymentDto {
private List<Integer> passengerIds;
private CouponType couponType;
public List<Integer> getPassengerIds() {
	return passengerIds;
}
public void setPassengerIds(List<Integer> passengerIds) {
	this.passengerIds = passengerIds;
}
public CouponType getCouponType() {
	return couponType;
}
public void setCouponType(CouponType couponType) {
	this.couponType = couponType;
}
public PaymentType getPaymentType() {
	return paymentType;
}
public void setPaymentType(PaymentType paymentType) {
	this.paymentType = paymentType;
}
private PaymentType paymentType;
}
