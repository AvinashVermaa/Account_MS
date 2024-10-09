package com.account.service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.account.dto.LoansDto;

@FeignClient("loans")
public interface LoansFeignClient {

	@GetMapping(value = "api/fetch",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansDto> fetchLoanDetails(@RequestHeader("eazybank-correlation-id")
    String correlationId,
    @RequestParam("mobileNumber") String mobileNumber);
}
