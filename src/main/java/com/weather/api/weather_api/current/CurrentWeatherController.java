package com.weather.api.weather_api.current;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.weather.api.weather_api.current.model.CurrentWeatherModel;
import com.weather.api.weather_api.current.service.CurrentWeatherService;

/**
 * Controller for UI request
 * 
 * @author Liuyuanjun
 *
 */
@RestController
public class CurrentWeatherController {

	final Logger currentCtrlLogger = LogManager.getLogger();

	private CurrentWeatherService service;

	@GetMapping("/weather/current")
	public ResponseEntity<?> currentWeather(@RequestParam(value = "city") String city) {

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

		return response;
	}
}
