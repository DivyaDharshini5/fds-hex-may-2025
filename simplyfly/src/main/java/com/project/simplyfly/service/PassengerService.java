package com.project.simplyfly.service;

import java.security.Principal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.project.simplyfly.dto.CustomerDto;
import com.project.simplyfly.dto.PassengerDto;
import com.project.simplyfly.exception.ResourceNotFoundException;
import com.project.simplyfly.model.Customer;
import com.project.simplyfly.model.Passenger;
import com.project.simplyfly.repository.BookingRepository;
import com.project.simplyfly.repository.PassengerRepository;
import com.project.simplyfly.repository.SeatRepository;

@Service
public class PassengerService {
private PassengerRepository passengerRepository;
private CustomerService customerService;
private BookingRepository bookingRepository;
private  SeatRepository seatRepository;

	
	public PassengerService(PassengerRepository passengerRepository, CustomerService customerService,
		BookingRepository bookingRepository, SeatRepository seatRepository) {
	super();
	this.passengerRepository = passengerRepository;
	this.customerService = customerService;
	this.bookingRepository = bookingRepository;
	this.seatRepository = seatRepository;
}
	public PassengerDto AddPassenger(PassengerDto passengerdto,Principal principal) {
		//get username of loggedin customer
		String username=principal.getName();
		//fetch the customer based on username
		Customer customer=customerService.getCustomerByUsername(username);
		 if (customer == null) {
	            throw new ResourceNotFoundException("Customer not found ");
	        }
		 Passenger passenger = new Passenger();
		  passenger.setFull_name(passengerdto.getFull_name());
		    passenger.setAge(passengerdto.getAge());
		    passenger.setGender(passengerdto.getGender());
		    passenger.setPassport_no(passengerdto.getPassport_no());
		  //attach customer to passenger
		 passenger.setCustomer(customer);
		 passengerRepository.save(passenger);
		 CustomerDto customerDto = new CustomerDto();
		    customerDto.setId(customer.getId());
		    customerDto.setName(customer.getName());
		    customerDto.setContact(customer.getContact());
		    PassengerDto responseDto = new PassengerDto();
		    responseDto.setId(passenger.getId());
		    responseDto.setFull_name(passenger.getFull_name());
		    responseDto.setAge(passenger.getAge());
		    responseDto.setGender(passenger.getGender());
		    responseDto.setPassport_no(passenger.getPassport_no());
		    responseDto.setCustomer(customerDto);
		    return responseDto;
	}
	public Passenger UpdatePassenger(Passenger updatedpassenger, Principal principal) {
		//get username of logged in user
		String username = principal.getName();
		Passenger passenger= passengerRepository.getByPassengerUsername(username);
		if(updatedpassenger.getFull_name()!=null) {
			passenger.setFull_name(updatedpassenger.getFull_name());
		}
		if(updatedpassenger.getGender()!=null) {
			passenger.setGender(updatedpassenger.getGender());
		}
		if(updatedpassenger.getAge()!=0 && updatedpassenger.getAge()<0) {
			passenger.setAge(updatedpassenger.getAge());
		}
		if(updatedpassenger.getPassport_no()!=null) {
			passenger.setPassport_no(updatedpassenger.getPassport_no());
		}
		
		return passengerRepository.save(passenger);
	}
	public void DeletePassenger(Principal principal) {
		String username = principal.getName();
		List<Passenger> passenger= passengerRepository.getByUsername(username);
		passengerRepository.deleteAll(passenger);
	}
}
