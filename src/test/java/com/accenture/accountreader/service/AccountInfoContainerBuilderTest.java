package com.accenture.accountreader.service;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Test;

import com.accenture.accountreader.io.DefaultFileReader;
import com.accenture.accountreader.model.AccountInfo;
import com.accenture.accountreader.model.AccountInfoContainer;

public class AccountInfoContainerBuilderTest {
	private static DefaultFileReader defaultFileReader = new DefaultFileReader();
	private static AccountInfoContainerBuilder ccrBuilder= new AccountInfoContainerBuilder();

	private static final String inputFilePath = "src/test/resources/FILE.DAT";
	private static final String fileDelimiter = "\\t";

	@Test
	public void testBuildCountryCreditRatingMap() throws FileNotFoundException {
		Map<String, AccountInfo> accDetailsMap = defaultFileReader.readFileToAccountDetails(inputFilePath,fileDelimiter);
		Map<String, AccountInfoContainer> ccrMap = ccrBuilder.buildCountryCreditRatingMap(accDetailsMap);
		assertTrue(ccrMap != null);
		//displayCCRBuilderMap(ccrMap);
	}

	@Test
	public void testBuildCountryCreditRatingMapForNullAccountDetailMap() throws FileNotFoundException {
		Map<String, AccountInfo> accDetailsMap = null;
		Map<String, AccountInfoContainer> ccrMap = ccrBuilder.buildCountryCreditRatingMap(accDetailsMap);
		assertTrue(ccrMap == null);
	}

	private void displayCCRBuilderMap(Map<String, AccountInfoContainer> ccrBuilderMap) {
		System.out.println("Display CountryCreditRatingMap (Data transformed for calculations)... ");
		System.out.println("**********************************************************************");
		System.out.println("<Key : Value> <===> <Country/City_CreditRating : AccountInfoContainer>");
		System.out.println("**********************************************************************");
		for(Entry<String, AccountInfoContainer> entrySet  : ccrBuilderMap.entrySet()) {
			System.out.println(entrySet.getKey()+" : "+entrySet.getValue());
		}
	}
}
