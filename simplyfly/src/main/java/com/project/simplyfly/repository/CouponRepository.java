package com.project.simplyfly.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.simplyfly.enums.CouponType;
import com.project.simplyfly.model.Coupon;

public interface CouponRepository extends JpaRepository<Coupon,Integer>{
    @Query("select c from Coupon c where c.CouponName=?1")
	Coupon findByCouponName(CouponType CouponName);


}
