package com.weather.api.weather_api.common.client;

public class HttpFailedStatusException extends Exception {

	public HttpFailedStatusException(final String message) {
		super(message);
	}
}
