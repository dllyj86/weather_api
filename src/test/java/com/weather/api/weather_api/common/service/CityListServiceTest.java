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
public class CityListServiceTest {

	@Autowired()
	@Qualifier("cityNameList")
	private List<String> cityList;
	
	public List<String> getCityNameList(){
		
		return this.cityList;
	}

}
