package com.account.mapper;

import com.account.dto.CustomerDto;
import com.account.entity.Customer;

public class CustomerMappper {
	
	private CustomerMappper(){
		//add the private constructor to restrict the initialization
	}

	public static CustomerDto mapToCustomerDto(CustomerDto customerDto,Customer customer) {
		customerDto.setName(customer.getName());
		customerDto.setMobileNumber(customer.getMobileNumber());
		customerDto.setEmail(customer.getEmail());
		return customerDto;
	}
	
	public static Customer mapToCustomer(CustomerDto customerDto,Customer customer) {
		customer.setName(customerDto.getName());
		customer.setEmail(customerDto.getEmail());
		customer.setMobileNumber(customerDto.getMobileNumber());
		return customer;
	}
	
	
	
}
