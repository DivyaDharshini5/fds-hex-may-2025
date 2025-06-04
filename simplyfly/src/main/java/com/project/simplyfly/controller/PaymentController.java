package com.project.simplyfly.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.simplyfly.model.Booking;
import com.project.simplyfly.model.Payment;
import com.project.simplyfly.service.PaymentService;

@RestController
public class PaymentController {
	@Autowired
	private PaymentService paymentService;
	/**method:post
	 * param:bookingId,Request body payment
	**/
@PostMapping("/api/payment/add/{bookingId}")
private Payment MakePayment(@PathVariable int bookingId,@RequestBody Payment payment) {
	return paymentService.MakePayment(bookingId,payment);
	
}

/**method:get
 * param:customerId
**/
@GetMapping("/api/payment/get-one/{customerId}")
public  List<Payment> findByCustomerId(@PathVariable  int customerId){
	return paymentService.findByCustomerId(customerId);
}
@GetMapping("/api/payment/get-all")
public List<Payment> GetAll(){
	return paymentService.GetAll();
}

}
