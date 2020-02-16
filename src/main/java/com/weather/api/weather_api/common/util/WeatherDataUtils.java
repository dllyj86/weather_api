package com.weather.api.weather_api.common.util;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import com.weather.api.weather_api.common.constant.Constants;

public class WeatherDataUtils {

	/**
	 * Get local updated time
	 * @param timezoneId	timezone id
	 * @param localTime	source local time
	 * @param localLanguage	local language
	 * @param targetFormat	target format for updated time
	 * @return	formatted updated time
	 */
	public static String getLocalUpdatedTime(final String timezoneId, final String localTime,
			final Locale localLanguage, final String targetFormat) throws DateTimeException {

		LocalDateTime.now(ZoneId.of(timezoneId));
		// Get LocalDataTime for String updated time
		LocalDateTime local = LocalDateTime.parse(localTime,
				DateTimeFormatter.ofPattern(Constants.SOURCE_UPDATED_TIME_FORMAT));
		// Format the time to target format
		String updatedTime = local.format(DateTimeFormatter.ofPattern(targetFormat, localLanguage));

		return updatedTime;
	}

	/**
	 * Get celsius symbol
	 * @return
	 */
	public static String getCelsiusSymbol() {
		char celsiusSymbol = (char) Integer.parseInt(Constants.CELSIUS_SYMBOY_UNICODE_NUMBER,
				Constants.PARSE_INTEGER_RADIX);
		return String.valueOf(celsiusSymbol);
	}

}
