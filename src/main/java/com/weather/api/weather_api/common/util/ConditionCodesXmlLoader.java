package com.weather.api.weather_api.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.core.io.ClassPathResource;

/**
 * Load condition codes xml file.
 * Keep the condition and code mapping data.
 * @author Liuyuanjun
 *
 */
public class ConditionCodesXmlLoader {

	private static final Logger conditionCodesLoaderLogger = LogManager.getLogger(); 
	
	private static String weatherConditionCodesFileName = "weather-condition-codes.xml";
	
	private static final String rootElement = "//codes";
	
	private static final String codeElement = "code";
	
	private static final String descriptionElement = "description";
	
	/**
	 * Keeps mapping data of condition code and condition description.
	 */
	private static Map<String, String> conditionCodesMap = null;

	/**
	 * The mapping data for weather condition code and condition description.
	 * @return Map of condition code and condition description.
	 */
	public static final Map<String, String> getConditionCodesMap() {

		if (ConditionCodesXmlLoader.conditionCodesMap == null) {
			ConditionCodesXmlLoader.conditionCodesMap = ConditionCodesXmlLoader.loadConditionCodesFile();
		}

		return ConditionCodesXmlLoader.conditionCodesMap;
	}

	/**
	 * Load file and map the data.
	 * @return
	 */
	private static final Map<String, String> loadConditionCodesFile() {

		Document document = null;
		InputStream inputStream = null;
		try {
			ClassPathResource resource = new ClassPathResource(weatherConditionCodesFileName);
			inputStream = resource.getInputStream();
			
			SAXReader reader = new SAXReader();
			
			document = reader.read(inputStream);
		} catch (IOException | DocumentException e) {
			conditionCodesLoaderLogger.error("weather-condition-codes.xml file didn't exist.", e);
			return null;
		} finally {
			try {
				inputStream.close();
			} catch (IOException e) {
				conditionCodesLoaderLogger.error("Close input stream of weather-condition-codes.xml failed", e);
			}
		}

		Map<String, String> conditionCodes = new HashMap<String, String>();

		Element element = (Element) document.selectSingleNode(rootElement);

		List<Element> conditionList = element.elements();

		for (Element condition : conditionList) {
			Element code = condition.element(codeElement);
			Element description = condition.element(descriptionElement);
			conditionCodes.put(code.getTextTrim(), description.getTextTrim());
		}

		return conditionCodes;
	}
}
