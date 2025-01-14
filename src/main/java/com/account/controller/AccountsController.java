package com.account.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.account.constants.AccountsConstants;
import com.account.dto.CustomerDto;
import com.account.dto.ErrorResponse;
import com.account.dto.ResponseDto;
import com.account.service.IAccountService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;

@RestController
@RequestMapping(path = "/api", produces = { MediaType.APPLICATION_JSON_VALUE })
@Validated
@Tag(name = "CRUD REST APIs for Accounts in EazyBank", description = "CRUD REST APIs in EazyBank to CREATE, UPDATE, FETCH AND DELETE account details")
public class AccountsController {

	private IAccountService accountService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AccountsController.class);

	@Autowired
	public AccountsController(IAccountService accountService) {
		this.accountService = accountService;
		LOGGER.info("AccountsController initialized successfully");
	}

	@Operation(summary = "Create Account REST API", description = "REST API to create new Customer &  Account inside EazyBank")

	@ApiResponse(responseCode = "201", description = "HTTP Status CREATED")
	@ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
	@PostMapping("/create")
	public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto) {
		accountService.createAccount(customerDto);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ResponseDto(AccountsConstants.STATUS_201, AccountsConstants.MESSAGE_201));
	}

	@Operation(summary = "Fetch Account Details REST API", description = "REST API to fetch Customer &  Account details based on a mobile number")

	@ApiResponse(responseCode = "201", description = "HTTP Status CREATED")
	@ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))

	@GetMapping("/fetch")
	public ResponseEntity<CustomerDto> fetchAccountDetails(
			@RequestParam("mobileNumber") 
			@Pattern(regexp = "(^$|\\d{10})", message = "Mobile number must be 10 digits") 
			String mobileNumber
			) {
		CustomerDto customerDto = accountService.fetchAccount(mobileNumber);
		return ResponseEntity.status(HttpStatus.OK).body(customerDto);

	}

	@Operation(summary = "Update Account Details REST API", description = "REST API to update Customer &  Account details based on a account number")

	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "HTTP Status OK"),
			@ApiResponse(responseCode = "417", description = "Expectation Failed"

			),
			@ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorResponse.class))

			)

	}

	)

	@PutMapping("/update")
	public ResponseEntity<ResponseDto> updateAccountDetails(@Valid @RequestBody CustomerDto customerDto) {
		boolean isUpdated = accountService.updateAccount(customerDto);
		if (isUpdated) {
			return ResponseEntity.ok(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));

		} else {
			return ResponseEntity
					.ok(new ResponseDto(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417_UPDATE));
		}
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<ResponseDto> deleteAccoutDetails(@RequestParam("mobileNumber") 
	@Pattern(regexp = "(^$|\\d{10})", message = "Mobile number must be 10 digits")
	String mobileNumber){
		
		boolean isDeleted = accountService.deleteAccount(mobileNumber);
		if(isDeleted) {
			return ResponseEntity
					.ok(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
			
		}
		else {
			return ResponseEntity
					.ok(new ResponseDto(AccountsConstants.STATUS_417,AccountsConstants.MESSAGE_417_DELETE));
		}
		
	}

}
