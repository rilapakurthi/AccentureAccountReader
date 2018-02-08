package com.accenture.accountreader.io;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Before;
import org.junit.Test;

import com.accenture.accountreader.model.AccountInfo;
import com.accenture.accountreader.service.AccountInfoContainerBuilder;
import com.accenture.accountreader.service.AverageCalculator;

public class DefaultFileReaderTest {
	private static DefaultFileReader defaultFileReader = new DefaultFileReader();

	private static final String invalidInputFilePath = "src/testInvalid/resources/FILE.DAT";
	private static final String inputFilePath = "src/test/resources/FILE.DAT";
	private static final String fileDelimiter = "\\t";

	@Test
	public void testReadFileToAccountDetails() throws FileNotFoundException {
		Map<String, AccountInfo> accDetailsMap = defaultFileReader.readFileToAccountDetails(inputFilePath,fileDelimiter);
		assertTrue(accDetailsMap != null);
		//displayAccountDetails(accDetailsMap);
	}

	@Test(expected = FileNotFoundException.class)
	public void testReadFileToAccountDetailsForInvalidFilePath() throws FileNotFoundException {
		Map<String, AccountInfo> accDetailsMap = defaultFileReader.readFileToAccountDetails(invalidInputFilePath,fileDelimiter);
	}
	
	private void displayAccountDetails(Map<String, AccountInfo> accDetailsMap) {
		System.out.println("Display AccountDetailsMap (data read from file)...");
		System.out.println("<CompanyCode_AccountNumber : AccountInfo>");

		for(Entry<String, AccountInfo> entrySet  : accDetailsMap.entrySet()) {
			System.out.println(entrySet.getKey()+" : "+entrySet.getValue());
		}
	}
}
