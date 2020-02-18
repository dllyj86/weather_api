package com.weather.api.weather_api.common.service;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;

import com.weather.api.weather_api.BasicTest;
import com.weather.api.weather_api.common.client.WeatherApiClient;
import com.weather.api.weather_api.common.constant.Constants;

@SpringBootTest
public class CallingApiServiceTest extends BasicTest {
	
	@Mock
	private WeatherApiClient apiClient;
	
	@InjectMocks
	CallingApiService service = new CallingApiService();
	
	@Test
	public void testCallingApiServiceMainLogic() throws Exception {
		
		Mockito.when(apiClient.doGet("http://api.weatherstack.com/current?query=Sydney&access_key=208727fd8af3b34c1febf14e7ff52faa")).thenReturn(BasicTest.mockJson);
		
		Map input = this.getDataMap();
		Map output = this.getDataMap();
		
		Map param = this.getDataMap();
		param.put("query", "Sydney");
		input.put(Constants.QUERY_INPUT_DATA_KEY, param);
		
		this.setPrivateProperties(service);
		
		final boolean status = service.run(input, output);
		
		Assert.assertTrue(status);
		Assert.assertNotNull(output.get(Constants.CURRENT_WEATHER_FOR_CITY_RESPONSE_BODY));
	}
	
	@Test
	public void testGetRequestUrl() throws Exception {
		
		Map<String, String> param = new HashMap<String, String>();
		param.put("query", "Sydney");
		this.setPrivateProperties(service);
		Object invoke = ReflectionTestUtils.invokeMethod(service, "getRequestUrl", "http://api.weatherstack.com", "/current", param);
		
		Assert.assertEquals("http://api.weatherstack.com/current?query=Sydney&access_key=208727fd8af3b34c1febf14e7ff52faa", invoke);
	}
	
	@Test
	public void testAppendAccessKey() throws Exception {
		
		this.setPrivateProperties(service);
		StringBuilder sb = new StringBuilder();
		sb.append("http://api.weatherstack.com/current?query=Sydney");
		final String url = ReflectionTestUtils.invokeMethod(service, "appendApiAccessKeyInUrl", sb).toString();
		
		Assert.assertEquals("http://api.weatherstack.com/current?query=Sydney&access_key=208727fd8af3b34c1febf14e7ff52faa", url);

	}
	
	private void setPrivateProperties(Object service) {
		ReflectionTestUtils.setField(service, "weatherApiHost", "http://api.weatherstack.com");
		ReflectionTestUtils.setField(service, "currentWeatherPath", "/current");
		ReflectionTestUtils.setField(service, "apiAccessKey", "208727fd8af3b34c1febf14e7ff52faa");
	}
	
}
