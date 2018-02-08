package com.accenture.accountreader.model;
import java.math.BigDecimal;

public class AccountInfo {

	private String companyCode;
	private String accountNumber;
	private String city;
	private String country;
	private String creditRating;
	private String currency;
	private BigDecimal amount;
	
	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	
	public String getAccountNumber() {
		return accountNumber;
	}
	
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getCountry() {
		return country;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}
	
	public String getCreditRating() {
		return creditRating;
	}
	
	public void setCreditRating(String creditRating) {
		this.creditRating = creditRating;
	}
	
	public String getCurrency() {
		return currency;
	}
	
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
	public BigDecimal getAmount() {
		return amount;
	}
	
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	@Override
	public String toString() {
		return "\n \t \t \t AccountInfo {\n"
				+ "\t \t \t \t companyCode = " + companyCode + ",\n"
				+ "\t \t \t \t accountNumber = " + accountNumber + ",\n"
				+ "\t \t \t \t city = " + city + ",\n"
				+ "\t \t \t \t country = "	+ country + ",\n"
				+ "\t \t \t \t creditRating = " + creditRating + ",\n"
				+ "\t \t \t \t currency = " + currency + ",\n"
				+ "\t \t \t \t amount = " + amount + "\n" 
				+ "\t \t \t }\n";
	}
}
