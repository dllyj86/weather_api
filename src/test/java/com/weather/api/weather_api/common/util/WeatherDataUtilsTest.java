package com.weather.api.weather_api.common.util;

import java.util.Locale;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import com.weather.api.weather_api.common.constant.Constants;

public class WeatherDataUtilsTest {

	@Test
	public void testGetLocalUpdatedTime() throws Exception {

		final String time = WeatherDataUtils.getLocalUpdatedTime("Australia/Sydney", "2020-02-16 23:10", Locale.ENGLISH,
				Constants.TARGET_UPDATED_TIME_FORMAT);

		Assert.assertEquals("Sunday 11:10 PM", time);
	}

	@Test
	public void testWrongTimezoneId() {

		try {
			final String time = WeatherDataUtils.getLocalUpdatedTime("Australia\\/Sydney", "2020-02-16 23:10",
					Locale.ENGLISH, Constants.TARGET_UPDATED_TIME_FORMAT);

		} catch (Exception e) {
			Assert.assertNotNull(e);
		}

	}

	@Test
	public void testGetCelsiusSymbol() {

		final String symbol = WeatherDataUtils.getCelsiusSymbol();

		Assert.assertEquals("℃", symbol);
	}
}
