package com.account.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;


@Schema(name="Customer",description="Schema to hold Customer and Account information")
public class CustomerDto {

	@NotNull(message="Name can not be a null or empty")
	@Schema(description="Name of the customer",example="Avinash")
	@Size(min = 5,max = 30,message="The length of the customer name should be between 5 and 30")
	private String name;
	
	@NotNull(message="Email address can not be a null or empty")
	@Schema(description="Email address of the customer",example="avinash@gmail.com")
	@Email(message = "Email address should be a valid value")
	private String email;
	
	@NotNull(message="Mobile Number can not be a null or empty")
	@Pattern(regexp = "(^$|\\d{10})", message = "Mobile number must be 10 digits")
	@Schema(description="Mobile Number of the customer",example="4848484932")
	private String mobileNumber;
	
	@Schema(description="Account details of the Customer")
	private AccountsDto accountsDto;

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

	@Override
	public String toString() {
		return "CustomerDto [name=" + name + ", email=" + email + ", mobileNumber=" + mobileNumber + ", accountsDto="
				+ accountsDto + "]";
	}

	public CustomerDto(
			@NotEmpty(message = "Name can not be a null or empty") @Size(min = 5, max = 30, message = "The length of the customer name should be between 5 and 30") String name,
			@NotEmpty(message = "Email address can not be a null or empty") @Email(message = "Email address should be a valid value") String email,
			@NotEmpty(message = "Mobile Number can not be a null or empty") @Pattern(regexp = "(^$|\\d{10})", message = "Mobile number must be 10 digits") String mobileNumber,
			AccountsDto accountsDto) {
		super();
		this.name = name;
		this.email = email;
		this.mobileNumber = mobileNumber;
		this.accountsDto = accountsDto;
	}

	public CustomerDto() {
		super();
	}
	
	
	

}
