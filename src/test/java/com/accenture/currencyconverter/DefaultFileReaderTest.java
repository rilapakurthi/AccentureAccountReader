package com.accenture.currencyconverter;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.util.Map;

import org.junit.Ignore;
import org.junit.Test;

import com.accenture.accountreader.exceptions.InvalidCurrencyException;
import com.accenture.accountreader.io.DefaultFileReader;
import com.accenture.accountreader.model.AccountInfo;
import com.accenture.accountreader.model.AccountInfoContainer;
import com.accenture.accountreader.model.Currency;
import com.accenture.accountreader.service.AccountInfoContainerBuilder;
import com.accenture.accountreader.service.AverageCalculator;

public class DefaultFileReaderTest {
	private static DefaultFileReader defaultFileReader = new DefaultFileReader();
	private static AccountInfoContainerBuilder ccrBuilder= new AccountInfoContainerBuilder();
	private static AverageCalculator avgCalculator = new AverageCalculator();

	private static final String invalidInputFilePath = "src/testInvalid/resources/FILE.DAT";
	private static final String inputFilePath = "src/test/resources/FILE.DAT";
	private static final String fileDelimiter = "\\t";
	private static final String ZIMBABWE_DOLLAR = "ZWD"; 

	@Test
	public void testReadFileToAccountDetails() throws FileNotFoundException {
		Map<String, AccountInfo> accDetailsMap = defaultFileReader.readFileToAccountDetails(inputFilePath,fileDelimiter);
		assertTrue(accDetailsMap != null);
		System.out.println("Display AccountDetailsMap (data read from file): "+accDetailsMap);
	}

	@Test(expected = FileNotFoundException.class)
	public void testReadFileToAccountDetailsForInvalidFilePath() throws FileNotFoundException {
		Map<String, AccountInfo> accDetailsMap = defaultFileReader.readFileToAccountDetails(invalidInputFilePath,fileDelimiter);
	}

	@Test
	public void testBuildCountryCreditRatingMap() throws FileNotFoundException {
		Map<String, AccountInfo> accDetailsMap = defaultFileReader.readFileToAccountDetails(inputFilePath,fileDelimiter);
		Map<String, AccountInfoContainer> ccrMap = ccrBuilder.buildCountryCreditRatingMap(accDetailsMap);
		assertTrue(ccrMap != null);
		System.out.println("Display CountryCreditRatingMap (Data transformed for calculations): "+ccrMap);
	}

	@Test
	public void testBuildCountryCreditRatingMapForNullAccountDetailMap() throws FileNotFoundException {
		Map<String, AccountInfo> accDetailsMap = null;
		Map<String, AccountInfoContainer> ccrMap = ccrBuilder.buildCountryCreditRatingMap(accDetailsMap);
		assertTrue(ccrMap == null);
	}

	@Test
	public void testCurrencyContains() {
		assertTrue(Currency.contains(Currency.class, "USD"));
		assertTrue(Currency.contains(Currency.class, "GBP"));
		assertTrue(Currency.contains(Currency.class, "CHF"));
		assertFalse(Currency.contains(Currency.class, "INR"));
		assertFalse(Currency.contains(Currency.class, null));
	}
	
	@Test
	public void testCalculateAverageInEuro() throws FileNotFoundException, InvalidCurrencyException {
		Map<String, AccountInfo> accDetailsMap = defaultFileReader.readFileToAccountDetails(inputFilePath,fileDelimiter);
		Map<String, AccountInfoContainer> ccrMap = ccrBuilder.buildCountryCreditRatingMap(accDetailsMap);
		avgCalculator.calculateAverageInEuro(ccrMap);
		System.out.println("Display Results Calculating average in Euro(s)...");
		System.out.println(ccrMap);
	}
	
	@Test (expected=InvalidCurrencyException.class)
	public void testCalculateAverageInvalidCurrency() throws FileNotFoundException, InvalidCurrencyException {
		Map<String, AccountInfo> accDetailsMap = defaultFileReader.readFileToAccountDetails(inputFilePath,fileDelimiter);
		Map<String, AccountInfoContainer> ccrMap = ccrBuilder.buildCountryCreditRatingMap(accDetailsMap);
		avgCalculator.calculateAverage(ZIMBABWE_DOLLAR,ccrMap);
		System.out.println("Display Results Calculating average in ZIMBABWE DOLLAR(s)...");
		System.out.println(ccrMap);
	}
}
