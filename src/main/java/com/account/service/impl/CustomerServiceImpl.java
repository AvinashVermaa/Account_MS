package com.account.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.account.dto.AccountsDto;
import com.account.dto.CardsDto;
import com.account.dto.CustomerDetailsDto;
import com.account.dto.LoansDto;
import com.account.entity.Accounts;
import com.account.entity.Customer;
import com.account.exception.ResourceNotFoundException;
import com.account.mapper.AccountsMapper;
import com.account.mapper.CustomerMappper;
import com.account.repository.AccountsRepository;
import com.account.repository.CustomerRepository;
import com.account.service.ICustomersService;
import com.account.service.client.CardsFeignClient;
import com.account.service.client.LoansFeignClient;

@Service
public class CustomerServiceImpl implements ICustomersService{
	
	private AccountsRepository accountsRepo;
	private CustomerRepository customerRepo;
	private CardsFeignClient cardsFeign;
	private LoansFeignClient loansFeignClient;
	
	@Autowired
	public CustomerServiceImpl(AccountsRepository accountsRepo,CustomerRepository customerRepo,
			CardsFeignClient cardsFeign,LoansFeignClient loansFeignClient) {
		this.accountsRepo = accountsRepo;
		this.customerRepo = customerRepo;
		this.cardsFeign = cardsFeign;
		this.loansFeignClient = loansFeignClient;
	}

	@Override
	public CustomerDetailsDto fetchCustomerDetails(String mobileNumber) {
		Customer customer = customerRepo.findByMobileNumber(mobileNumber)
		.orElseThrow(()-> new ResourceNotFoundException("Customers", "mobileNumber", mobileNumber));
		
		Accounts accounts = accountsRepo.findByCustomerId(customer.getCustomerId())
		.orElseThrow(()-> new ResourceNotFoundException("Accounts", "customerId", customer.getCustomerId().toString()));
		
		ResponseEntity<CardsDto> cardsDto = cardsFeign.fetchCardDetails(mobileNumber);
		
		ResponseEntity<LoansDto> loansDto = loansFeignClient.fetchLoanDetails(mobileNumber);
		
		CustomerDetailsDto customerDetailsDto = CustomerMappper.mapToCustomerDetailsDto(customer, new CustomerDetailsDto());
		
		AccountsDto accountsDto = AccountsMapper.mapToAccountsDto(accounts, new AccountsDto());
		
		customerDetailsDto.setLoansDto(loansDto.getBody());
		customerDetailsDto.setAccountsDto(accountsDto);
		customerDetailsDto.setCardsDto(cardsDto.getBody());
		
		return customerDetailsDto;
	}

}
