package com.weather.api.weather_api.common.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.util.ResourceUtils;

/**
 * Load condition codes xml file.
 * Keep the condition and code mapping data.
 * @author Liuyuanjun
 *
 */
public class ConditionCodesXmlLoader {

	private static final Logger conditionCodesLoaderLogger = LogManager.getLogger(); 
	
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

		File file = null;
		try {
			file = ResourceUtils.getFile("classpath:weather-condition-codes.xml");
		} catch (FileNotFoundException e) {
			conditionCodesLoaderLogger.error("weather-condition-codes.xml file didn't exist.", e);
			return null;
		}

		SAXReader reader = new SAXReader();
		Document document = null;

		try {
			document = reader.read(file);
		} catch (DocumentException e) {
			conditionCodesLoaderLogger.error("Reading weather-condition-codes.xml occured error", e);
			return null;
		}

		Map<String, String> conditionCodes = new HashMap<String, String>();

		Element element = (Element) document.selectSingleNode("//codes");

		List<Element> conditionList = element.elements();

		for (Element condition : conditionList) {
			Element code = condition.element("code");
			Element description = condition.element("description");
			conditionCodes.put(code.getTextTrim(), description.getTextTrim());
		}

		return conditionCodes;
	}
}
