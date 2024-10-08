package com.account.service;

import com.account.dto.CustomerDetailsDto;

public interface ICustomersService {

	public CustomerDetailsDto fetchCustomerDetails(String mobileNumber);

}
