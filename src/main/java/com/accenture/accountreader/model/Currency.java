package com.accenture.accountreader.model;
import java.math.BigDecimal;
import java.util.EnumSet;

public enum Currency implements Constants {

	GBP ("GBP","USD", new BigDecimal("1.654")),
	CHF ("CHF","USD", new BigDecimal("1.100")),
	EUR ("EUR","USD", new BigDecimal("1.350")),
	USD ("USD","USD", new BigDecimal("1.000"));

	private BigDecimal fxRate; //Used BigDecimal for more precise values 

	Currency(String foreignCurrency, String baseCurrency, BigDecimal fxRate) {
		this.fxRate = fxRate;
	}

	public void setFxRate(BigDecimal fxRate) {
		this.fxRate = fxRate;
	}

	public BigDecimal getFxRate() {
		return this.fxRate;
	}

	public BigDecimal getInverseFxRate() {
		return BigDecimal.ONE.divide(this.fxRate,DECIMAL_PRECISION ,BigDecimal.ROUND_HALF_UP);
	}

	public static <E extends Enum<E>> boolean contains(Class<E> _enumClass,
			String value) {
		try {
			return EnumSet.allOf(_enumClass)
					.contains(Enum.valueOf(_enumClass, value));
		} catch (Exception e) {
			return false;
		}
	}
}
