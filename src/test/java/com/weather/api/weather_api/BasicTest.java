package com.weather.api.weather_api;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.mockito.MockitoAnnotations;

public class BasicTest {
	
	public final static String mockJson = "{\"request\":{\"unit\":\"m\",\"query\":\"Sydney, Australia\",\"language\":\"en\",\"type\":\"City\"},"
			+ "\"current\":{\"weather_descriptions\":[\"Partly cloudy\"],\"observation_time\":\"12:10 PM\",\"wind_degree\":200,\"visibility\":10,"
			+ "\"weather_icons\":[\"https://assets.weatherstack.com/images/wsymbols01_png_64/wsymbol_0004_black_low_cloud.png\"],"
			+ "\"feelslike\":25,\"is_day\":\"no\",\"wind_dir\":\"SSW\",\"pressure\":1015,\"cloudcover\":75,\"precip\":0,\"uv_index\":1,"
			+ "\"temperature\":22,\"humidity\":88,\"wind_speed\":9,\"weather_code\":116},\"location\":{\"localtime\":\"2020-02-16 23:10\","
			+ "\"utc_offset\":\"11.0\",\"country\":\"Australia\",\"localtime_epoch\":1581894600,\"name\":\"Sydney\",\"timezone_id\":\"Australia/Sydney\","
			+ "\"lon\":\"151.217\",\"region\":\"New South Wales\",\"lat\":\"-33.883\"}}";

	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}
	
	public Map<String, Object> getDataMap(){
		return new HashMap<String, Object>();
	}
	
}
