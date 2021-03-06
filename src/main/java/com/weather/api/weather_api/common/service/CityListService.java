package com.weather.api.weather_api.common.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Get name list of city
 * @author Liuyuanjun
 *
 */
@Component
public class CityListService {

	/**
	 * City list comes from workflow-config.xml
	 * If you want to add new city, please edit the xml.
	 */
	@Autowired()
	@Qualifier("cityNameList")
	private List<String> cityList;
	
	public List<String> getCityNameList(){
		
		return this.cityList;
	}

}
