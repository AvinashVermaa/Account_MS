package com.account.dto;

import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "accounts")
public class AccountsContactInfoDto {

	private String message;
	private Map<String, String> contactDetails;
	private List<String> oncallsupport;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Map<String, String> getContactDetails() {
		return contactDetails;
	}

	public void setContactDetails(Map<String, String> contactDetails) {
		this.contactDetails = contactDetails;
	}

	public List<String> getOncallsupport() {
		return oncallsupport;
	}

	public void setOncallsupport(List<String> oncallsupport) {
		this.oncallsupport = oncallsupport;
	}

	@Override
	public String toString() {
		return "AccountsContactInfoDto [message=" + message + ", contactDetails=" + contactDetails + ", oncallsupport="
				+ oncallsupport + "]";
	}

	public AccountsContactInfoDto(String message, Map<String, String> contactDetails, List<String> oncallsupport) {
		super();
		this.message = message;
		this.contactDetails = contactDetails;
		this.oncallsupport = oncallsupport;
	}

	public AccountsContactInfoDto() {
		super();
		// TODO Auto-generated constructor stub
	}

}
