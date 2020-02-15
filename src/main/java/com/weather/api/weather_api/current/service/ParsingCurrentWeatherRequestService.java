package com.weather.api.weather_api.current.service;

import java.util.HashMap;
import java.util.Map;

import com.weather.api.weather_api.common.constant.Constants;
import com.weather.api.weather_api.common.workflow.task.Task;

/**
 * Parse request data for current weather.
 * Convert UI request data to query data.
 * 
 * @author Liuyuanjun
 *
 */
public class ParsingCurrentWeatherRequestService extends Task {

	@Override
	public boolean run(Map<String, Object> workflowInputData, Map<String, Object> workflowOutputData) {

		final String city = (String) this.getUIInputData(workflowInputData);
		
		final Map<String, String> queryParamMap = new HashMap<String, String>();
		
		queryParamMap.put(Constants.API_PARAM_KEY_CITY, city);
		
		this.putQueryInputData(queryParamMap, workflowInputData);
		
		return true;
	}

}
