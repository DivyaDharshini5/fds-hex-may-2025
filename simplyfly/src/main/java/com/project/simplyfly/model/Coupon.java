package com.project.simplyfly.model;

import java.time.LocalDate;

import com.project.simplyfly.enums.CouponType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Coupon {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name="coupon_name")
	@Enumerated(EnumType.STRING)
	private CouponType CouponName;
	@Column(name="start_date")
	private LocalDate start_date;
	@Column(name="end_date")
	private LocalDate end_date;
	@Column(name="is_active",columnDefinition = "TINYINT(1)")
	private boolean Is_active;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public CouponType getCouponName() {
		return CouponName;
	}
	public void setCouponName(CouponType couponName) {
		CouponName = couponName;
	}
	public LocalDate getStart_date() {
		return start_date;
	}
	public void setStart_date(LocalDate start_date) {
		this.start_date = start_date;
	}
	public LocalDate getEnd_date() {
		return end_date;
	}
	public void setEnd_date(LocalDate end_date) {
		this.end_date = end_date;
	}
	public boolean isIs_active() {
		return Is_active;
	}
	public void setIs_active(boolean is_active) {
		Is_active = is_active;
	}

}
