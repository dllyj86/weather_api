package com.weather.api.weather_api.current.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Map;

import org.json.JSONObject;

import com.weather.api.weather_api.common.constant.Constants;
import com.weather.api.weather_api.common.util.ConditionCodesXmlLoader;
import com.weather.api.weather_api.common.workflow.task.Task;
import com.weather.api.weather_api.current.model.CurrentWeatherModel;

/**
 * Converter for current weather response.
 * @author Liuyuanjun
 *
 */
public class CurrentWeatherResponseConverter extends Task {
	
	private static final String CURRENT = "current";

	@Override
	public boolean run(Map<String, Object> workflowInputData, Map<String, Object> workflowOutputData) throws Exception {
		
		JSONObject resBody = (JSONObject) workflowOutputData.get(Constants.CURRENT_WEATHER_FOR_CITY_RESPONSE_BODY);
		
		CurrentWeatherModel model = new CurrentWeatherModel();
		
		// Get location part
		JSONObject locationPart = resBody.getJSONObject("location");
		// set city name
		model.setCity(locationPart.optString("name"));
		// format updated time and keep it in model
		String timezoneId = locationPart.optString("timezone_id");
		LocalDateTime.now(ZoneId.of(timezoneId));
		String localTime = locationPart.optString("localtime");
		// Get LocalDataTime for String updated time
		LocalDateTime local = LocalDateTime.parse(localTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
		// Format the time to target format
		String updatedTime = local.format(DateTimeFormatter.ofPattern("EEEE hh:mm a", Locale.ENGLISH));
		
		model.setUpdatedTime(updatedTime);
		
		// Get current data part
		JSONObject currentPart = resBody.getJSONObject(CURRENT);
		// Get weather code and get related description
		String weatherCode = currentPart.optString("weather_code");
		Map<String, String> conditionCodesMap = ConditionCodesXmlLoader.getConditionCodesMap();
		model.setWeather(conditionCodesMap.get(weatherCode));
		// temperature
		char celsiusSymbol = (char) Integer.parseInt("2103", 16);   
		model.setTemperature(currentPart.optString("temperature") + String.valueOf(celsiusSymbol));
		// wind speed
		model.setWindSpeed(currentPart.optString("wind_speed") + "km/h");
		
		workflowOutputData.put(Constants.OUTPUT_DATA_KEY, model);
		
		return true;
	}
	
}
