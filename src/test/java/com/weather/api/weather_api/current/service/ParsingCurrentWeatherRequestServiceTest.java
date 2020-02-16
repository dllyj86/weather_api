package com.weather.api.weather_api.current.service;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import com.weather.api.weather_api.BasicTest;
import com.weather.api.weather_api.common.constant.Constants;


/**
 * Parse request data for current weather.
 * Convert UI request data to query data.
 * 
 * @author Liuyuanjun
 *
 */
public class ParsingCurrentWeatherRequestServiceTest extends BasicTest  {

	@Test
	public void testParsingLogic() {
		
		ParsingCurrentWeatherRequestService service = new ParsingCurrentWeatherRequestService();
		Map input = this.getDataMap();
		input.put(Constants.UI_INPUT_DATA_KEY, "Sydney");
		Map output = this.getDataMap();
		final boolean status = service.run(input, output);
		Assert.assertTrue(status);
		
		Map param = (Map) input.get(Constants.QUERY_INPUT_DATA_KEY);
		final String city = (String) param.get(Constants.API_PARAM_KEY_CITY);
		Assert.assertEquals(city, "Sydney");
	}
	
}
