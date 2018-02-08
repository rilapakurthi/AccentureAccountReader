package com.accenture.accountreader.model;

import java.math.BigDecimal;
import java.util.List;

public class AccountInfoContainer {
	
	List<AccountInfo> accInfoList;
	BigDecimal average;
	String avgInCurrency;
	
	public List<AccountInfo> getAccInfoList() {
		return accInfoList;
	}
	public void setAccInfoList(List<AccountInfo> accInfoList) {
		this.accInfoList = accInfoList;
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
		return "AccountInfoContainer {\n"
				+ "\t \t average = " + average + ",\n"
				+ "\t \t avgInCurrency = " + avgInCurrency + ",\n "
				+ "\t \t accInfoList = " + accInfoList + "\n \t"
				+ "} \n \t \t";
	}
}
