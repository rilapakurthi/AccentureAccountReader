package com.accenture.accountreader.io;
import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.accenture.accountreader.model.AccountInfo;
import com.accenture.accountreader.model.Constants;

public class DefaultFileReader implements Constants {
	private static Logger log = LoggerFactory.getLogger(DefaultFileReader.class);
	private static final String logPrefix = "DefaultFileReader: ";
	
	/**
	 * method to reads the file and populate AccountDetailsMap
	 * 
	 * @param filePath
	 * @param delimiter
	 * @return
	 * @throws FileNotFoundException 
	 */
	public Map<String, AccountInfo> readFileToAccountDetails(String filePath,String delimiter) throws FileNotFoundException {
		File file = new File(filePath);
		String line;
		Map<String, AccountInfo> accountDetailsMap = null;

		try {
			Scanner scanner = new Scanner(file);
			scanner.useDelimiter(delimiter);
			accountDetailsMap = new HashMap();
			
			//... Discarding the 1st line, considering
			//... the 1st line is always header info
			if(scanner.hasNextLine())
				scanner.nextLine();

			while (scanner.hasNextLine()) {
				line = scanner.nextLine();
				String split[] = line.split(delimiter);
				
				AccountInfo accountInfo = new AccountInfo();
				populateAccountInfoToAccountMap(accountInfo, split);
				accountDetailsMap.put(accountInfo.getCompanyCode()+SEPARATOR+accountInfo.getAccountNumber(),accountInfo);
			}
		} catch (FileNotFoundException fException) {
			log.error(logPrefix, fException.getStackTrace());
			throw fException;
		}
		
		return accountDetailsMap;
	}

	/**
	 * @param accountDetailsMap
	 * @param split
	 * @return
	 */
	public AccountInfo populateAccountInfoToAccountMap(AccountInfo accountInfo, String[] split) {
		accountInfo.setCompanyCode(split[0]);
		accountInfo.setAccountNumber(split[1]);
		accountInfo.setCity(split[2]);
		accountInfo.setCountry(split[3]);
		accountInfo.setCreditRating(split[4]);
		accountInfo.setCurrency(split[5]);
		accountInfo.setAmount(new BigDecimal(split[6]));

		return accountInfo;
	}
}
