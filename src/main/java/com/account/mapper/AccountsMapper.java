package com.account.mapper;

import com.account.dto.AccountsDto;
import com.account.entity.Accounts;

public class AccountsMapper {
	
	private AccountsMapper() {
		//add the private constructor to restrict the initialization
	}

	public static AccountsDto mapToAccountsDto(Accounts accounts, AccountsDto accountsDto) {
		accountsDto.setAccountNumber(accounts.getAccountNumber());
		accountsDto.setAccountType(accounts.getAccountType());
		accountsDto.setBranchAddress(accounts.getBranchAddress());
		return accountsDto;
	}

	public static Accounts mapToAccounts(AccountsDto accountsDto, Accounts accounts) {
		accounts.setAccountNumber(accountsDto.getAccountNumber());
		accounts.setAccountType(accountsDto.getAccountType());
		accounts.setBranchAddress(accountsDto.getBranchAddress());
		return accounts;
	}
}
