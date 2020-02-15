package com.weather.api.weather_api.common.constant;

public class Constants {

	public static String QUESTION_MARK = "?";

	public static String EQUAL_SIGN = "=";

	public static String AMPERSAND = "&";

	/**
	 * Key for UI input data. Service needs this key to get data and makeup query
	 * data.
	 */
	public static String UI_INPUT_DATA_KEY = "UI_INPUT_DATA";

	/**
	 * Key for query data. Client service needs this data and loop the data of query
	 * data.
	 */
	public static String QUERY_INPUT_DATA_KEY = "QUERY_INPUT_DATA";

	/**
	 * Key of output data in workflowOutputData
	 */
	public static String OUTPUT_DATA_KEY = "OUTPUT_DATA";
	
	public static String API_PARAM_ACCESS_KEY_NAME = "access_key";

	/**
	 * Key of workflow
	 */
	public static String WORKFLOW_QUERY_CURRENT_WEATHER_FOR_CITY = "queryCurrentWeatherForCity";

	public static String API_PARAM_KEY_CITY = "query";
	
	public static String CURRENT_WEATHER_FOR_CITY_RESPONSE_BODY = "CURRENT_WEATHER_FOR_CITY_RESPONSE_BODY";
	
}
