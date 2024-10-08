package com.account.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Schema(name = "CustomerDetails", 
description = "Schema to hold Customer, Account, Cards and Loans information	")
public class CustomerDetailsDto {

	@Schema(
				name = "name",
				description = "Name of the customer",
				example = "Eazy Bytes"
			)
	
	@NotEmpty(message = "Name can not be a null or empty")
	@Size(min = 5, max = 30, message = "The length of the customer name should be between 5 and 30")
	private String name;
	
	@Schema(
				description = "Email address of the customer",
				example = "tutor@eazybytes.com"
			)
	@Email(message = "Email address should be a valid value")
	@NotNull(message = "Email address can not be a null or empty")
	private String email;
	
	
	@Schema(
				description = "Mobile Number of the customer",
				example = "9345432123"
			)
	
	@NotEmpty(message = "mobile number can not be a null or empty")
	@Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
	private String mobileNumber;
	
	
	@Schema(
			 description = "Account details of the Customer"
			)
	private AccountsDto accountsDto;
	
	@Schema(description = "Cards details of the Customer")
	private CardsDto cardsDto;
	
	@Schema(description = "Loans details of the Customer")
	private LoansDto loansDto;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public AccountsDto getAccountsDto() {
		return accountsDto;
	}

	public void setAccountsDto(AccountsDto accountsDto) {
		this.accountsDto = accountsDto;
	}

	public CardsDto getCardsDto() {
		return cardsDto;
	}

	public void setCardsDto(CardsDto cardsDto) {
		this.cardsDto = cardsDto;
	}

	public LoansDto getLoansDto() {
		return loansDto;
	}

	public void setLoansDto(LoansDto loansDto) {
		this.loansDto = loansDto;
	}

	@Override
	public String toString() {
		return "CustomerDetailsDto [name=" + name + ", email=" + email + ", mobileNumber=" + mobileNumber
				+ ", accountsDto=" + accountsDto + ", cardsDto=" + cardsDto + ", loansDto=" + loansDto + "]";
	}

	public CustomerDetailsDto(String name, String email, String mobileNumber, AccountsDto accountsDto,
			CardsDto cardsDto, LoansDto loansDto) {
		super();
		this.name = name;
		this.email = email;
		this.mobileNumber = mobileNumber;
		this.accountsDto = accountsDto;
		this.cardsDto = cardsDto;
		this.loansDto = loansDto;
	}

	public CustomerDetailsDto() {
		super();
	}

}