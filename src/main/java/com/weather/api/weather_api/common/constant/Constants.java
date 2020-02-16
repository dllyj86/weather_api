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
	
	// Constants for weather response start
	public static final String SOURCE_UPDATED_TIME_FORMAT = "yyyy-MM-dd HH:mm";
	
	public static final String CURRENT_PART_KEY = "current";

	public static final String TARGET_UPDATED_TIME_FORMAT = "EEEE hh:mm a";
	
	public static final String LOCATION_PART_KEY = "location";
	
	public static final String NAME_PROP_KEY = "name";
	
	public static final String TIMEZONE_ID_PROP_KEY = "timezone_id";
	
	public static final String LOCALTIME_PROP_KEY = "localtime";
	
	public static final String WEATHER_CODE_PROP_KEY = "weather_code";

	public static final String TEMPERATURE_PROP_KEY = "temperature";
	
	public static final String WIND_SPEED_PROP_KEY = "wind_speed";
	
	public static final String KM_PER_HOUR_LABEL = "km/h";
	
	public static final String CELSIUS_SYMBOY_UNICODE_NUMBER = "2103";
	
	public static final int PARSE_INTEGER_RADIX = 16;
	// end
	
}
