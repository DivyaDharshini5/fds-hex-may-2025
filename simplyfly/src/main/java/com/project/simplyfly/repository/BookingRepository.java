package com.project.simplyfly.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.simplyfly.enums.Status;
import com.project.simplyfly.model.Booking;

public interface BookingRepository extends JpaRepository<Booking,Integer>{
	List<Booking> findByStatus(Status status);
//	@Query("select b from booking b where b.customer.id=?1,b.flight.id=?2"
//			+ "and b.coupon.id=?3 ")
//	Optional<Booking> getUsingJPQL(int customerId, int flightId, int couponId);
//    
    @Query("select b from Booking b where b.customer.id=?1")
	Booking GetByCustomerID(int customerId);
	


}
