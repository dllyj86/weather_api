package com.weather.api.weather_api.common.workflow.task;

import java.util.Map;

import com.weather.api.weather_api.common.constant.Constants;

/**
 * Interface of task
 * 
 * A task could be a service. It takes one step of the whole workflow.
 * 
 * @author Liuyuanjun
 *
 */
public abstract class Task {

	/**
	 * Run the task. 
	 * 
	 * @param inputData
	 * @param workflowData
	 * @return
	 */
	public abstract boolean run(final Map<String, Object> workflowInputData, final Map<String, Object> workflowOutputData) throws Exception;
	
	/**
	 * Get UI input data by default key.
	 * @param workflowInputData
	 * @return
	 */
	public Object getUIInputData(final Map<String, Object> workflowInputData) {
		
		return workflowInputData != null ? workflowInputData.get(Constants.UI_INPUT_DATA_KEY) : null;
	}
	
	/**
	 * Get query input data by default key.
	 * 
	 * Query input data is the query data for client for sending request to API.
	 * 
	 * @param workflowInputData
	 * @return
	 */
	public Object getQueryInputData(final Map<String, Object> workflowInputData) {
		
		return workflowInputData != null ? workflowInputData.get(Constants.QUERY_INPUT_DATA_KEY) : null;

	}
	
	/**
	 * Put query input data to workflowInputData by default key.
	 * @param queryData
	 * @param workflowInputData
	 */
	public void putQueryInputData(final Object queryData, final Map<String, Object> workflowInputData) {
		
		workflowInputData.put(Constants.QUERY_INPUT_DATA_KEY, queryData);
	}
}	
