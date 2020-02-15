package com.weather.api.weather_api.common.client;

import java.io.IOException;
import java.util.Map;

import org.apache.http.Consts;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HTTP;

/**
 * Http client to call weather api.
 * @author Liuyuanjun
 *
 */
public class WeatherApiClient {
	
	private HttpClient httpClient;
	
	public WeatherApiClient() {
		this.httpClient = HttpClients.createDefault();
	}

	/**
	 * Get request
	 * @param host
	 * @param path
	 * @param paramsMap
	 * @return
	 * @throws IOException
	 */
	public HttpResponse doGet(String url) throws IOException {
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectionRequestTimeout(10000)
                .setConnectTimeout(5000)
                .build();
        HttpGet httpGet = new HttpGet(url);
        httpGet.setConfig(requestConfig);
        httpGet.setHeader(HTTP.CONTENT_TYPE,ContentType.create(ContentType.APPLICATION_FORM_URLENCODED
                .getMimeType(),Consts.UTF_8).toString());

        return this.httpClient.execute(httpGet);
    }
	
	public HttpClient getApiClient() {
		return this.httpClient;
	}
	
}
