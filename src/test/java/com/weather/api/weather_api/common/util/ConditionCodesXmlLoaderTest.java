package com.weather.api.weather_api.common.util;

import java.util.Map;

import org.junit.Assert;
import org.junit.Test;


/**
 * Load condition codes xml file.
 * Keep the condition and code mapping data.
 * @author Liuyuanjun
 *
 */
public class ConditionCodesXmlLoaderTest {

	@Test
	public void testGetCodesMap() {
		
		Map<String, String> codeMap = ConditionCodesXmlLoader.getConditionCodesMap();
		
		Assert.assertNotNull(codeMap);
		Assert.assertTrue(!codeMap.entrySet().isEmpty());
	}
}
