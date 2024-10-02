package com.account.service.impl;

import java.util.Optional;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.account.constants.AccountsConstants;
import com.account.dto.AccountsDto;
import com.account.dto.CustomerDto;
import com.account.entity.Accounts;
import com.account.entity.Customer;
import com.account.exception.CustomerAlreadyExistsException;
import com.account.exception.ResourceNotFoundException;
import com.account.mapper.AccountsMapper;
import com.account.mapper.CustomerMappper;
import com.account.repository.AccountsRepository;
import com.account.repository.CustomerRepository;
import com.account.service.IAccountService;

@Service
public class AccountServiceImpl implements IAccountService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AccountServiceImpl.class);
	
	private AccountsRepository accountsRepo;
	private CustomerRepository customerRepo;
	
	private static final Random random = new Random();
	
	@Autowired
	public AccountServiceImpl(AccountsRepository accountsRepo,CustomerRepository customerRepo) {
		this.accountsRepo = accountsRepo;
		this.customerRepo = customerRepo;
		LOGGER.info("Initialization of AccountServiceImpl was completed...");
	}

	@Override
	public void createAccount(CustomerDto customerDto) {
		Customer customer = CustomerMappper.mapToCustomer(customerDto, new Customer());
		Optional<Customer> optionalCustomer = customerRepo.findByMobileNumber(customerDto.getMobileNumber());
		if(optionalCustomer.isPresent()) {
			throw new CustomerAlreadyExistsException("Customer already registered with given mobileNumber "+customerDto.getMobileNumber());
			
		}
		System.out.println("3");
		Customer savedCustomer = customerRepo.save(customer);
		System.out.println("2");
		accountsRepo.save(createNewAccount(savedCustomer));
		
	}
	
	private Accounts createNewAccount(Customer customer) {
		Accounts accounts = new Accounts();
		accounts.setCustomerId(customer.getCustomerId());
		long randomAccountNumber = 1000000000L + random.nextInt(900000000);
		accounts.setAccountNumber(randomAccountNumber);
		accounts.setAccountType(AccountsConstants.SAVINGS);
		accounts.setBranchAddress(AccountsConstants.ADDRESS);
		return accounts;
	}

	@Override
	public CustomerDto fetchAccount(String mobileNumber) {
		Customer customer = customerRepo.findByMobileNumber(mobileNumber)
		.orElseThrow(()->new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));
		
		Accounts account = accountsRepo.findByCustomerId(customer.getCustomerId())
		.orElseThrow(()->new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString()));
		
		CustomerDto customerDto = CustomerMappper.mapToCustomerDto(new CustomerDto(), customer);
		customerDto.setAccountsDto(AccountsMapper.mapToAccountsDto(account, new AccountsDto()));
		
		return customerDto;
	}

	@Override
	public boolean updateAccount(CustomerDto customerDto) {
		boolean isUpdated = false;
		AccountsDto accountsDto = customerDto.getAccountsDto();
		Accounts accounts = accountsRepo.findById(accountsDto.getAccountNumber())
		.orElseThrow(()->new ResourceNotFoundException("Accounts", "accountNumber", accountsDto.getAccountNumber().toString()));
		
		AccountsMapper.mapToAccounts(accountsDto, accounts);
		accountsRepo.save(accounts);
		
		Customer customer = customerRepo.findById(accounts.getCustomerId())
		.orElseThrow(()->new ResourceNotFoundException("Customer", "CustomerId", accounts.getCustomerId().toString()));
		
		CustomerMappper.mapToCustomer(customerDto, customer);
		customerRepo.save(customer);
		
		isUpdated = true;
		
		return isUpdated;
	}

	@Override
	public boolean deleteAccount(String mobileNumber) {
		Customer customer = customerRepo.findByMobileNumber(mobileNumber).orElseThrow(()->
			new ResourceNotFoundException("customer","mobileNumber", mobileNumber));
		
		accountsRepo.deleteByCustomerId(customer.getCustomerId());
		customerRepo.deleteById(customer.getCustomerId());
		
		return true;
	}

}
