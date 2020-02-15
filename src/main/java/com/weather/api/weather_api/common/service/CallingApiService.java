package com.weather.api.weather_api.common.service;

import java.io.IOException;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import com.weather.api.weather_api.common.client.WeatherApiClient;
import com.weather.api.weather_api.common.workflow.task.Task;

public class CallingApiService extends Task {
	
	private WeatherApiClient apiClient;
	
	private String weatherApiHost = "http://api.weatherstack.com";
	
	private String currentWeatherPath = "/current?access_key=208727fd8af3b34c1febf14e7ff52faa";

	@Override
	public boolean run(final Map<String, Object> workflowInputData, final Map<String, Object> workflowOutputData) throws IOException {
		
		
		final Map<String, String> queryParamMap = (Map<String, String>) this.getQueryInputData(workflowInputData);
		
		final String url = this.getRequestUrl(weatherApiHost, currentWeatherPath, queryParamMap);
		
		final HttpResponse response = this.apiClient.doGet(url);
		
		final String responseEntity = EntityUtils.toString(response.getEntity());
		
		System.out.println("responseEntity: " + responseEntity);
		
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
                params.append("&" + entry.getKey() + "=" + entry.getValue());
            }
            String paramConnector = "?";
            if (!host.contains(paramConnector) && !path.contains(paramConnector)) {
                reqUrl.append(paramConnector);
                reqUrl.append(params.toString().substring(1));
            } else {
                reqUrl.append(params.toString());
            }
        }

        return reqUrl.toString();
    }
}
