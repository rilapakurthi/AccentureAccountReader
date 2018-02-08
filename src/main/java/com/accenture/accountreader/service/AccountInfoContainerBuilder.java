package com.accenture.accountreader.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.accenture.accountreader.model.AccountInfo;
import com.accenture.accountreader.model.AccountInfoContainer;
import com.accenture.accountreader.model.Constants;
import com.accenture.accountreader.model.Constants.BuilderConstants;

public class AccountInfoContainerBuilder implements Constants {

	private static Logger log = LoggerFactory.getLogger(AccountInfoContainerBuilder.class);
	private static final String CCRB_PREFIX = "CountryCreditRatingBuilder: ";

	/**
	 * Builds an aggregation level map of Country+CreditRating from Account level detail map (i.e., CompanyCode+AccountNumber Map)
	 * 
	 * @param accountDetailsMap
	 * @return
	 */
	public Map<String, AccountInfoContainer> buildCountryCreditRatingMap(Map<String, AccountInfo> accountDetailsMap) {
		Map<String, AccountInfoContainer> countryCreditRatingMap = null;
		AccountInfoContainer accInfoContainer = null;
		
		if (accountDetailsMap != null) {
			countryCreditRatingMap = new HashMap();

			for(Entry<String, AccountInfo>  entry : accountDetailsMap.entrySet()) {
				AccountInfo  accInfo = entry.getValue();

				String newKey = buildKeyForCountryCreditRatingMap(accInfo);
				List<AccountInfo> accInfoList = null;
				if (countryCreditRatingMap.containsKey(newKey)) {
					accInfoContainer = countryCreditRatingMap.get(newKey);
					accInfoList = accInfoContainer.getAccInfo();
				} else {
					accInfoContainer = new AccountInfoContainer();
					accInfoList = new ArrayList<AccountInfo>();
					accInfoContainer.setAccInfo(accInfoList);
					countryCreditRatingMap.put(newKey, accInfoContainer);
				}
				accInfoList.add(accInfo);
				//countryCreditRatingMap.put(newKey, accInfoContainer);
			}
		}

		return countryCreditRatingMap;
	}

	/**
	 * Building Key using Country/City + CreditRating
	 * 
	 * @param accInfo
	 * @return
	 */
	private String buildKeyForCountryCreditRatingMap(AccountInfo accInfo) {
		String keyPrefix = accInfo.getCountry().toUpperCase();
		String keySuffix = accInfo.getCreditRating().toUpperCase();

		if(StringUtils.isBlank(keyPrefix)) {
			keyPrefix = accInfo.getCity().toUpperCase();
			if (StringUtils.isBlank(keyPrefix)) {
				log.warn(CCRB_PREFIX + "Account Country and City not available, hence defaulting [Country/City = WORLD]");
				keyPrefix = BuilderConstants.DEFAULT_COUNTRY;
			}
		}

		if(StringUtils.isBlank(keySuffix)) {
			log.warn(CCRB_PREFIX + "Account Rating cannot be null hence defaulting [Credit Rating = NAC] (i.e., not available currently");
			keySuffix = BuilderConstants.DEFAULT_CREDIT_RATING;
		}

		return (keyPrefix + SEPARATOR + keySuffix); 
	}
}
