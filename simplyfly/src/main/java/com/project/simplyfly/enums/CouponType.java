package com.project.simplyfly.enums;

public enum CouponType {
	SUMMER_SALE(15),
    NEW_USER(20),
    BLACK_FRIDAY(25),
    DIWALI(10);
	private int value;
    public int getValue() {
		return value;
	}
	CouponType(int value) {
		this.value=value;
	}

	
	
}
