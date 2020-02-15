package com.weather.api.weather_api.current.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.weather.api.weather_api.common.constant.Constants;
import com.weather.api.weather_api.common.workflow.WorkflowScheduler;
import com.weather.api.weather_api.current.model.CurrentWeatherModel;

/**
 * Top level service for getting current weather.
 * 
 * @author Liuyuanjun
 *
 */
@Component()
public class CurrentWeatherService {

	@Autowired()
	private WorkflowScheduler workflowScheduler;

	/**
	 * Query current weather for city.
	 * 
	 * @param city
	 *            name of city
	 * @return
	 * @throws Exception
	 */
	public CurrentWeatherModel queryCurrentWeatherForCity(final String city) throws Exception {

		final Map<String, Object> workflowInputData = new HashMap<String, Object>();
		workflowInputData.put(Constants.UI_INPUT_DATA_KEY, city);

		final Map<String, Object> workflowOutputData = new HashMap<String, Object>();

		boolean status = this.workflowScheduler.runWorkflow(Constants.WORKFLOW_QUERY_CURRENT_WEATHER_FOR_CITY,
				workflowInputData, workflowOutputData);

		if (!status) {
			throw new Exception("Query current weather for city did not get valid data.");
		}

		CurrentWeatherModel model = (CurrentWeatherModel) workflowOutputData.get(Constants.OUTPUT_DATA_KEY);
		return model;
	}
}
