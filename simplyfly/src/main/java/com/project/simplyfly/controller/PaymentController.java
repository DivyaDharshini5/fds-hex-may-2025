package com.project.simplyfly.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.simplyfly.dto.PaymentDto;
import com.project.simplyfly.model.Payment;
import com.project.simplyfly.service.PaymentService;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {
	@Autowired
	private PaymentService paymentService;
	
	/****method : post 
	param:principal for logged in user,
	*confirmation for makeing payment*/
@PostMapping("/process")
public ResponseEntity<List<PaymentDto>> processPayment(Principal principal,
@RequestParam(defaultValue = "true") boolean confirm) {
	List<PaymentDto> list = paymentService.processPayment(principal, confirm);
	return ResponseEntity.ok(list);
	    }
/*method:get
*param:principal for getting all payments of logged in user
*
*/
@GetMapping("/get")
public ResponseEntity<List<PaymentDto>> getPayments(Principal principal) {
    List<PaymentDto> list = paymentService.getPayments(principal);
    return ResponseEntity.ok(list);
}
/*method:delete
 * param:principal ,
 * booking id
 * 
 * */
@DeleteMapping("/cancel/{bookingId}")
public ResponseEntity<?> cancelBooking(
        @PathVariable int bookingId,
        Principal principal) {
    paymentService.cancelBooking(bookingId, principal);
    return ResponseEntity.status(HttpStatus.OK).body("Cancellation Initiated");
}
/*method:post
 * param:bookingId
 * */
@PostMapping("/refund/{bookingId}")
public ResponseEntity<?> refundBooking(@PathVariable int bookingId) {
    paymentService.refundPayment(bookingId);
    return ResponseEntity.status(HttpStatus.OK).body("Refund Processed");
}


}
