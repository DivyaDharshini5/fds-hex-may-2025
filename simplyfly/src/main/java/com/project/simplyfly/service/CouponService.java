package com.project.simplyfly.service;

import java.time.LocalDate;
import java.util.EnumSet;

import org.springframework.stereotype.Service;

import com.project.simplyfly.enums.CouponType;
import com.project.simplyfly.model.Coupon;
import com.project.simplyfly.repository.CouponRepository;

@Service
public class CouponService {
	private CouponRepository couponRepository;
public CouponService(CouponRepository couponRepository) {
		this.couponRepository = couponRepository;
	}
	public Coupon AddCoupon(Coupon coupon) {
		if (!EnumSet.allOf(CouponType.class).contains(coupon.getCouponName())) {
	        throw new IllegalArgumentException("Invalid coupon type!");
	    }
	
	coupon.setStart_date(LocalDate.now());
	coupon.setEnd_date(LocalDate.now().plusDays(15));
	coupon.setIs_active(true);

		return couponRepository.save(coupon);
	}

}
