package com.accenture.accountreader.service;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Before;
import org.junit.Test;

import com.accenture.accountreader.exceptions.InvalidCurrencyException;
import com.accenture.accountreader.io.DefaultFileReader;
import com.accenture.accountreader.model.AccountInfo;
import com.accenture.accountreader.model.AccountInfoContainer;

public class AverageCalculatorTest {
	private static DefaultFileReader defaultFileReader = new DefaultFileReader();
	private static AccountInfoContainerBuilder ccrBuilder= new AccountInfoContainerBuilder();
	private static AverageCalculator avgCalculator = new AverageCalculator();

	private static final String inputFilePath1 = "src/test/resources/FILE.DAT";
	private static final String inputFilePath2 = "src/test/resources/TEST_INPUT_2.DAT";

	private static final String fileDelimiter = "\\t";
	private static final String ZIMBABWE_DOLLAR = "ZWD"; 

	private static final String displayMessage = "Displaying results with average in Euro(s) for ";
	private static final String logMessage1 = displayMessage + inputFilePath1+" ... ";
	private static final String logMessage2 = displayMessage + inputFilePath1+" ... ";

	@Test
	public void testCalculateAverageInEuroForInputFile1() throws FileNotFoundException, InvalidCurrencyException {
		Map<String, AccountInfo> accDetailsMap = defaultFileReader.readFileToAccountDetails(inputFilePath1,fileDelimiter);
		Map<String, AccountInfoContainer> ccrMap = ccrBuilder.buildCountryCreditRatingMap(accDetailsMap);
		avgCalculator.calculateAverageInEuro(ccrMap);
		System.out.println("**********************************************************************");
		System.out.println(logMessage1);
		System.out.println("**********************************************************************");
		displayResult(ccrMap);
	}
	
	@Test
	public void testCalculateAverageInEuroForInputFile2() throws FileNotFoundException, InvalidCurrencyException {
		Map<String, AccountInfo> accDetailsMap = defaultFileReader.readFileToAccountDetails(inputFilePath2,fileDelimiter);
		Map<String, AccountInfoContainer> ccrMap = ccrBuilder.buildCountryCreditRatingMap(accDetailsMap);
		avgCalculator.calculateAverageInEuro(ccrMap);
		System.out.println("**********************************************************************");
		System.out.println(logMessage2);
		System.out.println("**********************************************************************");
		displayResult(ccrMap);
	}
	
	@Test (expected=InvalidCurrencyException.class)
	public void testCalculateAverageInvalidCurrency() throws FileNotFoundException, InvalidCurrencyException {
		Map<String, AccountInfo> accDetailsMap = defaultFileReader.readFileToAccountDetails(inputFilePath1,fileDelimiter);
		Map<String, AccountInfoContainer> ccrMap = ccrBuilder.buildCountryCreditRatingMap(accDetailsMap);
		avgCalculator.calculateAverage(ZIMBABWE_DOLLAR,ccrMap);
		System.out.println("Display Results Calculating average in ZIMBABWE DOLLAR(s)...");
		System.out.println(ccrMap);
	}
	
	private void displayResult(Map<String, AccountInfoContainer> ccrMap) {
		System.out.println("----------------------------------------------------------------------");
		System.out.println("<Key : Value> <===> <Country/City_CreditRating : AccountInfoContainer>");
		System.out.println("----------------------------------------------------------------------");
		for(Entry<String, AccountInfoContainer> entrySet  : ccrMap.entrySet()) {
			System.out.println(entrySet.getKey()+" : "+entrySet.getValue());
		}
	}
}
