package com.account.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;


@Schema(name="Accounts",description="Schema to hold Account information")
public class AccountsDto {

	@NotNull(message="AccountNumber can not be a null or empty")
	@Pattern(regexp = "(^$|\\d{10})", message = "Mobile number must be 10 digits")
	@Schema(description="Account Number of Eazy Bank accoun",example = "3454433243")
	private Long accountNumber;
	
	
	@NotNull(message="AccountType can not be a null or empty")
	@Schema(description="Account type of Eazy Bank account",example = "Saving")
	private String accountType;
	
	@NotNull(message="BranchAddress can not be a null or empty")
	@Schema(description="Eazy Bank branch address",example="123 NewYork")
	private String branchAddress;

	public Long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getBranchAddress() {
		return branchAddress;
	}

	public void setBranchAddress(String branchAddress) {
		this.branchAddress = branchAddress;
	}

	@Override
	public String toString() {
		return "AccountsDto [accountNumber=" + accountNumber + ", accountType=" + accountType + ", branchAddress="
				+ branchAddress + "]";
	}

	public AccountsDto(
			@NotNull(message = "AccountNumber can not be a null or empty") @Pattern(regexp = "(^$|\\d{10})", message = "Mobile number must be 10 digits") Long accountNumber,
			@NotNull(message = "AccountType can not be a null or empty") String accountType,
			@NotNull(message = "BranchAddress can not be a null or empty") String branchAddress) {
		super();
		this.accountNumber = accountNumber;
		this.accountType = accountType;
		this.branchAddress = branchAddress;
	}

	public AccountsDto() {
		super();
	}
	
	
	
	
}
