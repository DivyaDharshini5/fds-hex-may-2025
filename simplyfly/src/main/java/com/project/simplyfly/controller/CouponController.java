package com.project.simplyfly.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.simplyfly.model.Coupon;
import com.project.simplyfly.service.CouponService;
@RestController
public class CouponController {
	@Autowired
	private CouponService couponService;
	@PostMapping("/api/coupon/add")
	public Coupon AddCoupon(@RequestBody Coupon coupon) {
		return couponService.AddCoupon(coupon);
	}
}
