package com.weather.api.weather_api.current.service;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import com.weather.api.weather_api.BasicTest;
import com.weather.api.weather_api.common.constant.Constants;
import com.weather.api.weather_api.current.model.CurrentWeatherModel;


/**
 * Converter for current weather response.
 * 
 * @author Liuyuanjun
 *
 */
public class CurrentWeatherResponseConverterTest {

	@Test
	public void testCurrentWeatherResponseConverter() throws Exception {

		CurrentWeatherResponseConverter converter = new CurrentWeatherResponseConverter();

		Map<String, Object> workflowInputData = new HashMap<String, Object>();
		Map<String, Object> workflowOutputData = new HashMap<String, Object>();

		workflowOutputData.put(Constants.CURRENT_WEATHER_FOR_CITY_RESPONSE_BODY, new JSONObject(BasicTest.mockJson));
		
		final boolean status = converter.run(workflowInputData, workflowOutputData);
		Assert.assertEquals(true, status);
		
		CurrentWeatherModel model = (CurrentWeatherModel) workflowOutputData.get(Constants.OUTPUT_DATA_KEY);
		Assert.assertEquals(model.getCity(), "Sydney");
		Assert.assertEquals(model.getTemperature(), "22℃");
		Assert.assertEquals(model.getUpdatedTime(), "Sunday 11:10 PM");
		Assert.assertEquals(model.getWeather(), "Partly Cloudy");
		Assert.assertEquals(model.getWindSpeed(), "9km/h");
	}
	
	@Test
	public void testCurrentWeatherResponseConverterWithEmptyJSONObject() throws Exception {

		CurrentWeatherResponseConverter converter = new CurrentWeatherResponseConverter();

		Map<String, Object> workflowInputData = new HashMap<String, Object>();
		Map<String, Object> workflowOutputData = new HashMap<String, Object>();

//		workflowOutputData.put(Constants.CURRENT_WEATHER_FOR_CITY_RESPONSE_BODY, new JSONObject(mockJson));
		
		final boolean status = converter.run(workflowInputData, workflowOutputData);
		Assert.assertEquals(false, status);
	}

}
