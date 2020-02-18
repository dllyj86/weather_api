package com.weather.api.weather_api.common.util;

import java.util.Map;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


/**
 * Load condition codes xml file.
 * Keep the condition and code mapping data.
 * @author Liuyuanjun
 *
 */
@SpringBootTest
public class ConditionCodesXmlLoaderTest {

	@Test
	public void testGetCodesMap() {
		
		Map<String, String> codeMap = ConditionCodesXmlLoader.getConditionCodesMap();
		
		Assert.assertNotNull(codeMap);
		Assert.assertTrue(!codeMap.entrySet().isEmpty());
	}
}
