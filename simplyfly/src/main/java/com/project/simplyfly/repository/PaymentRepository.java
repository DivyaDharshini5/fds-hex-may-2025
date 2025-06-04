package com.project.simplyfly.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.simplyfly.model.Passenger;
import com.project.simplyfly.model.Payment;

public interface PaymentRepository extends JpaRepository<Payment,Integer>{
	 @Query("select p from Payment p where p.booking.customer.id=?1")
		List<Payment> findByCustomerId(int customerId);

	
		
}
