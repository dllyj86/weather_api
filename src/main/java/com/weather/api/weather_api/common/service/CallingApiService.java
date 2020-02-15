package com.weather.api.weather_api.common.service;

import java.io.IOException;
import java.util.Map;

import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.weather.api.weather_api.common.client.HttpFailedStatusException;
import com.weather.api.weather_api.common.client.WeatherApiClient;
import com.weather.api.weather_api.common.constant.Constants;
import com.weather.api.weather_api.common.workflow.task.Task;

public class CallingApiService extends Task {
	
	@Autowired()
	private WeatherApiClient apiClient;
	
	@Value("${weather.api.host}")
	private String weatherApiHost;
	
	@Value("${weather.api.current.path}")
	private String currentWeatherPath;
	
	@Value("${weather.api.access.key}")
	private String apiAccessKey;
	

	@Override
	public boolean run(final Map<String, Object> workflowInputData, final Map<String, Object> workflowOutputData) throws IOException, HttpFailedStatusException, JSONException {
		
		final Map<String, String> queryParamMap = (Map<String, String>) this.getQueryInputData(workflowInputData);
		
		final String url = this.getRequestUrl(weatherApiHost, currentWeatherPath, queryParamMap);
		
		final JSONObject body = this.apiClient.doGet(url);
		
		workflowOutputData.put(Constants.CURRENT_WEATHER_FOR_CITY_RESPONSE_BODY, body);
		
		return true;
	}

	/**
	 * Makeup request url
	 * @param host
	 * @param path
	 * @param paramsMap
	 * @return
	 */
	private String getRequestUrl(String host, String path, Map<String, String> paramsMap) {
		
        StringBuilder reqUrl = new StringBuilder(host).append(path);
        if (paramsMap != null && !paramsMap.isEmpty()) {
            StringBuilder params = new StringBuilder();
            for (Map.Entry<String, String> entry : paramsMap.entrySet()) {
                params.append(Constants.AMPERSAND + entry.getKey() + Constants.EQUAL_SIGN + entry.getValue());
            }
            if (!host.contains(Constants.QUESTION_MARK) && !path.contains(Constants.QUESTION_MARK)) {
                reqUrl.append(Constants.QUESTION_MARK);
                reqUrl.append(params.toString().substring(1));
            } else {
                reqUrl.append(params.toString());
            }
        }
        
        this.appendApiAccessKeyInUrl(reqUrl);

        return reqUrl.toString();
    }
	
	/**
	 * Append api access key in url
	 * @param url
	 * @return
	 */
	private StringBuilder appendApiAccessKeyInUrl(StringBuilder reqUrl) {
		return reqUrl.append(Constants.AMPERSAND + Constants.API_PARAM_ACCESS_KEY_NAME + Constants.EQUAL_SIGN + apiAccessKey);
	}
}
