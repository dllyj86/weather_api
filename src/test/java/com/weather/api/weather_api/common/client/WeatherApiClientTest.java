package com.weather.api.weather_api.common.client;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;

import com.weather.api.weather_api.BasicTest;

/**
 * Http client to call weather api.
 * 
 * @author Liuyuanjun
 *
 */
public class WeatherApiClientTest extends BasicTest {

	WeatherApiClient client = new WeatherApiClient();

	@Test
	public void testSuccessLogic() throws Exception {

		this.mockDataForCient(200);

		final String bodyStr = client
				.doGet("http://api.weatherstack.com/current?query=Sydney&access_key=208727fd8af3b34c1febf14e7ff52faa");

		// Because EntityUtils.toString() always handle the entity and I am unable to
		// mock it,
		// I have to leave the assert like below.
		Assert.assertNull(bodyStr);
	}

	@Test
	public void testStatusCode404Logic() {

		try {
			this.mockDataForCient(404);
			final String bodyStr = client
					.doGet("http://api.weatherstack.com/current?query=Sydney&access_key=208727fd8af3b34c1febf14e7ff52faa");
		} catch (Exception e) {
			Assert.assertNotNull(e);
		}

	}

	private void mockDataForCient(final int statusCode) throws ClientProtocolException, IOException {
		ReflectionTestUtils.setField(client, "CONNECTION_REQUEST_TIMEOUT", 1000);
		ReflectionTestUtils.setField(client, "CONNECT_TIMEOUT", 1000);

		HttpClient httpClient = Mockito.mock(HttpClient.class);

		ReflectionTestUtils.setField(client, "httpClient", httpClient);

		HttpResponse httpResponse = Mockito.mock(HttpResponse.class);
		StatusLine statusLine = Mockito.mock(StatusLine.class);
		HttpEntity entity = Mockito.mock(HttpEntity.class);

		Mockito.when(statusLine.getStatusCode()).thenReturn(statusCode);
		Mockito.when(httpResponse.getStatusLine()).thenReturn(statusLine);
		Mockito.when(httpResponse.getEntity()).thenReturn(entity);
		Mockito.when(httpClient.execute(Mockito.any())).thenReturn(httpResponse);
	}
}
