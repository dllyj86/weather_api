package com.weather.api.weather_api.current.service;

import java.util.Locale;
import java.util.Map;

import org.json.JSONObject;

import com.weather.api.weather_api.common.constant.Constants;
import com.weather.api.weather_api.common.util.ConditionCodesXmlLoader;
import com.weather.api.weather_api.common.util.WeatherDataUtils;
import com.weather.api.weather_api.common.workflow.task.Task;
import com.weather.api.weather_api.current.model.CurrentWeatherModel;

/**
 * Converter for current weather response.
 * 
 * @author Liuyuanjun
 *
 */
public class CurrentWeatherResponseConverter extends Task {

	@Override
	public boolean run(Map<String, Object> workflowInputData, Map<String, Object> workflowOutputData) throws Exception {

		JSONObject resBody = (JSONObject) workflowOutputData.get(Constants.CURRENT_WEATHER_FOR_CITY_RESPONSE_BODY);

		CurrentWeatherModel model = new CurrentWeatherModel();

		// Get location part
		JSONObject locationPart = resBody.getJSONObject(Constants.LOCATION_PART_KEY);
		// set city name
		model.setCity(locationPart.optString(Constants.NAME_PROP_KEY));

		// format updated time and keep it in model
		String timezoneId = locationPart.optString(Constants.TIMEZONE_ID_PROP_KEY);
		String localTime = locationPart.optString(Constants.LOCALTIME_PROP_KEY);

		final String updatedTime = WeatherDataUtils.getLocalUpdatedTime(timezoneId, localTime, Locale.ENGLISH,
				Constants.TARGET_UPDATED_TIME_FORMAT);
		model.setUpdatedTime(updatedTime);

		// Get current data part
		JSONObject currentPart = resBody.getJSONObject(Constants.CURRENT_PART_KEY);
		// Get weather code and get related description
		String weatherCode = currentPart.optString(Constants.WEATHER_CODE_PROP_KEY);
		Map<String, String> conditionCodesMap = ConditionCodesXmlLoader.getConditionCodesMap();
		model.setWeather(conditionCodesMap.get(weatherCode));
		// temperature

		model.setTemperature(currentPart.optString(Constants.TEMPERATURE_PROP_KEY) + WeatherDataUtils.getCelsiusSymbol());
		// wind speed
		model.setWindSpeed(currentPart.optString(Constants.WIND_SPEED_PROP_KEY) + Constants.KM_PER_HOUR_LABEL);

		workflowOutputData.put(Constants.OUTPUT_DATA_KEY, model);

		return true;
	}

}
