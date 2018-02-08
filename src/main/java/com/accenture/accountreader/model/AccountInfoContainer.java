package com.accenture.accountreader.model;

import java.math.BigDecimal;
import java.util.List;

public class AccountInfoContainer {
	
	List<AccountInfo> accInfo;
	BigDecimal average;
	String avgInCurrency;
	
	public List<AccountInfo> getAccInfo() {
		return accInfo;
	}
	public void setAccInfo(List<AccountInfo> accInfo) {
		this.accInfo = accInfo;
	}
	public BigDecimal getAverage() {
		return average;
	}
	public void setAverage(BigDecimal average) {
		this.average = average;
	}
	public String getAvgInCurrency() {
		return avgInCurrency;
	}
	public void setAvgInCurrency(String avgInCurrency) {
		this.avgInCurrency = avgInCurrency;
	}
	@Override
	public String toString() {
		return "AccountInfoContainer [accInfo=" + accInfo + ", average=" + average + ", avgInCurrency=" + avgInCurrency
				+ "]";
	}
}
