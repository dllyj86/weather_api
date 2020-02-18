package com.weather.api.weather_api.current;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.weather.api.weather_api.common.service.CityListService;
import com.weather.api.weather_api.current.model.CurrentWeatherModel;
import com.weather.api.weather_api.current.service.CurrentWeatherService;

/**
 * Controller for UI request
 * 
 * @author Liuyuanjun
 *
 */
public class CurrentWeatherControllerTest {

	final Logger currentCtrlLogger = LogManager.getLogger();

	@Autowired()
	private CurrentWeatherService service;
	
	@Autowired()
	private CityListService cityListService;

	@GetMapping("/api/weather/current")
	public ResponseEntity<?> currentWeather(@RequestParam(value = "city") String city) {

		this.currentCtrlLogger.info("==========\n Got request with path /api/weather/current . city value is " + city);
		
		ResponseEntity<?> response = null;

		// Check request parameter
		if (StringUtils.isEmpty(city)) {
			this.currentCtrlLogger.error("currentWeather controller got invalid query parameter - city", city);
			response = new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		} else {
			// Call service
			CurrentWeatherModel model = null;
			try {
				model = this.service.queryCurrentWeatherForCity(city);
			} catch (Exception e) {
				this.currentCtrlLogger.error("queryCurrentWeatherForCity throws exception.", e);
				response = new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}

			// Return data or error.
			if (model != null) {
				response = new ResponseEntity<CurrentWeatherModel>(model, HttpStatus.OK);
			} else {
				this.currentCtrlLogger.error("CurrentWeatherModel is null.");
				response = new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

		this.currentCtrlLogger.info("==========\\n Return response for path /api/weather/current");
		return response;
	}
	
	@GetMapping("/api/weather/citylist")
	public ResponseEntity<?> currentWeather() {

		this.currentCtrlLogger.info("==========\\n Got request with path /api/weather/citylist");
		
		final List<String> cityNameList = this.cityListService.getCityNameList();
		
		ResponseEntity<List<String>> response = new ResponseEntity<List<String>>(cityNameList, HttpStatus.OK);;
		
		this.currentCtrlLogger.info("==========\\n Return response for path /api/weather/citylist");
		return response;
	}
	
}
