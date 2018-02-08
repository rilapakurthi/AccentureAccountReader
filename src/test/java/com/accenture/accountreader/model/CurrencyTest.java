package com.accenture.accountreader.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class CurrencyTest {
	@Test
	public void testCurrencyContains() {
		assertTrue(Currency.contains(Currency.class, "USD"));
		assertTrue(Currency.contains(Currency.class, "GBP"));
		assertTrue(Currency.contains(Currency.class, "CHF"));
		assertFalse(Currency.contains(Currency.class, "INR"));
		assertFalse(Currency.contains(Currency.class, null));
	}
}
