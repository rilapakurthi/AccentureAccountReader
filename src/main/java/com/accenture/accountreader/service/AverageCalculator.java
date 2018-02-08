package com.accenture.accountreader.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.accenture.accountreader.exceptions.InvalidCurrencyException;
import com.accenture.accountreader.model.AccountInfo;
import com.accenture.accountreader.model.AccountInfoContainer;
import com.accenture.accountreader.model.Constants;
import com.accenture.accountreader.model.Currency;

public class AverageCalculator implements Constants {
	private static final Logger log = LoggerFactory.getLogger(AverageCalculator.class);
	private static final String AC_PREFIX = "AverageCalculator: ";
	
	/**
	 * @param currency
	 * @param ccrMap
	 * @return
	 * @throws InvalidCurrencyException
	 */
	public Map<String,AccountInfoContainer> calculateAverage(String currency, Map<String,AccountInfoContainer> ccrMap) throws InvalidCurrencyException {

		if(Currency.contains(Currency.class, currency)) {
			if (ccrMap != null) {
				calculateAverageInUsd(ccrMap);
				for(Entry<String, AccountInfoContainer> entrySet : ccrMap.entrySet()) {
					AccountInfoContainer accInfoContainer = entrySet.getValue();
					accInfoContainer.setAverage(accInfoContainer.getAverage().multiply((Currency.valueOf(currency).getInverseFxRate())));
					accInfoContainer.setAvgInCurrency(currency);
				}
			} else {
				log.error(AC_PREFIX+" cannot calculate average as input (Country+CreditRating Map) is null !!!");
			}
		} else {
			InvalidCurrencyException icException = new InvalidCurrencyException("Cannot calculate average in currency="+currency);
			log.error(AC_PREFIX+"Cannot calculate average due to exception=", icException);
			throw icException;
		}
		return ccrMap;
	}

	/**
	 * @param ccrMap
	 * @return
	 */
	public Map<String,AccountInfoContainer> calculateAverageInUsd(Map<String,AccountInfoContainer> ccrMap) {
		BigDecimal sum = BigDecimal.ZERO.setScale(DECIMAL_PRECISION, BigDecimal.ROUND_HALF_UP);

		for(Entry<String,AccountInfoContainer> entrySet : ccrMap.entrySet()) {
			sum = BigDecimal.ZERO;
			AccountInfoContainer accInfoContainer = entrySet.getValue();
			List<AccountInfo> accInfoList = accInfoContainer.getAccInfo();
			for(AccountInfo accInfo: accInfoList) {
				String accInfoCcy = accInfo.getCurrency().toUpperCase();
				if(!StringUtils.equals(accInfoCcy, Currency.USD.toString())) {
					sum = sum.add(Currency.valueOf(accInfoCcy).getFxRate().multiply(accInfo.getAmount()).setScale(DECIMAL_PRECISION, BigDecimal.ROUND_HALF_UP));
				} else {
					sum = sum.add(accInfo.getAmount());
				}
			}
			accInfoContainer.setAverage(sum.divide(BigDecimal.valueOf(accInfoList.size()), DECIMAL_PRECISION, BigDecimal.ROUND_HALF_EVEN));	
			accInfoContainer.setAvgInCurrency(Currency.USD.toString());
		}
		return ccrMap;
	}

	/**
	 * @param ccrMap
	 * @return
	 * @throws InvalidCurrencyException
	 */
	public Map<String,AccountInfoContainer> calculateAverageInEuro(Map<String,AccountInfoContainer> ccrMap) throws InvalidCurrencyException {
		return calculateAverage(Currency.EUR.toString(),ccrMap);
	}
}