package com.ecom.Service;

import java.util.List;

import com.ecom.Dao.CustomerDao;
import com.ecom.Dao.Impl.CustomerDaoImpl;
import com.ecom.exception.InvalidIdException;
import com.ecom.exception.InvalidInputException;
import com.ecom.model.Customer;

public class CustomerService {
	CustomerDao customerdao =new  CustomerDaoImpl();
	public void insert(Customer customer) throws InvalidInputException  {
if(customer==null )
	throw new RuntimeException("Customer cannot be null");
if(customer.getId()<0)
	throw new RuntimeException("InvalidIDException:Customer id cannot be negative");
		customerdao.insert(customer);
}
	public List<Customer> getAll(){
		return customerdao.getAll();
	}
	public Customer getById(int id) throws InvalidIdException{
		return customerdao.getByID(id);
		
	}
	
}
