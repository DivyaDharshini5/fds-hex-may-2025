package com.project.simplyfly.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.simplyfly.model.Booking;
import com.project.simplyfly.model.Coupon;

public interface CouponRepository extends JpaRepository<Coupon,Integer>{


}
