package com.project.simplyfly.dto;

import com.project.simplyfly.enums.CouponType;

public class CouponDto {
	 private int id;
	    public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public CouponType getCouponName() {
		return couponName;
	}
	public void setCouponName(CouponType couponType) {
		this.couponName = couponType;
	}
		private CouponType couponName;
}
