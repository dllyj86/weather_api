package com.weather.api.weather_api.common.client;

import java.io.IOException;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Http client to call weather api.
 * 
 * @author Liuyuanjun
 *
 */
@Component()
public class WeatherApiClient {

	@Value("${weather.api.connection.request.timeout}")
	private int CONNECTION_REQUEST_TIMEOUT;

	@Value("${weather.api.connect.timeout}")
	private int CONNECT_TIMEOUT;

	private static int SUCCESS_STATUS_CODE = 200;

	private HttpClient httpClient;

	final Logger weatherApiClientLogger = LogManager.getLogger();

	public WeatherApiClient() {
		this.httpClient = HttpClients.createDefault();
	}

	/**
	 * Get request
	 * 
	 * @param host
	 * @param path
	 * @param paramsMap
	 * @return
	 * @throws IOException
	 * @throws ClientProtocolException
	 * @throws HttpFailedStatusException
	 * @throws JSONException
	 * @throws Exception
	 */
	public String doGet(String url)
			throws ClientProtocolException, IOException, HttpFailedStatusException, JSONException {
		RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(CONNECTION_REQUEST_TIMEOUT)
				.setConnectTimeout(CONNECT_TIMEOUT).build();

		HttpGet httpGet = new HttpGet(url);
		httpGet.setConfig(requestConfig);
		httpGet.setHeader(HTTP.CONTENT_TYPE,
				ContentType.create(ContentType.APPLICATION_FORM_URLENCODED.getMimeType(), Consts.UTF_8).toString());

		this.weatherApiClientLogger.info("==========\n Start to call API。 URL is " + url);

		HttpResponse response = this.httpClient.execute(httpGet);

		this.weatherApiClientLogger.info("==========\n Got response form API");

		HttpEntity entity = response.getEntity();

		final int statusCode = response.getStatusLine().getStatusCode();
		if (SUCCESS_STATUS_CODE != statusCode) {
			EntityUtils.consume(entity);
			throw new HttpFailedStatusException("Request weather API failed. Status code: " + statusCode);
		}

		String bodyStr = this.getBodyStrFromEntity(entity);
		this.weatherApiClientLogger.debug("Response body: " + bodyStr);

		return bodyStr;
	}

	public String getBodyStrFromEntity(final HttpEntity entity) throws ParseException, IOException {
		String bodyStr = EntityUtils.toString(entity);
		EntityUtils.consume(entity);
		return bodyStr;
	}

	public HttpClient getApiClient() {
		return this.httpClient;
	}

}
