package com.weather.api.weather_api.current;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.weather.api.weather_api.common.service.CityListService;
import com.weather.api.weather_api.common.workflow.WorkflowScheduler;
import com.weather.api.weather_api.current.model.CurrentWeatherModel;
import com.weather.api.weather_api.current.service.CurrentWeatherService;

/**
 * Controller for UI request
 * 
 * @author Liuyuanjun
 *
 */
@SpringBootTest
@AutoConfigureMockMvc
public class CurrentWeatherControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Mock
	private CityListService cityListService;
	
	@Mock
	private CurrentWeatherService service;
	
	@Test
	public void shouldReturnCityList() throws Exception {

		List<String> nameList = new ArrayList<String>();
		Mockito.when(cityListService.getCityNameList()).thenReturn(nameList);
		
		this.mockMvc.perform(get("/api/weather/citylist")).andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$[0]").value("Sydney"));
	}

	@Test
	public void shouldReturnWeatherForCity() throws Exception {

		CurrentWeatherModel model = new CurrentWeatherModel();
		model.setCity("Sydney");
		Mockito.when(service.queryCurrentWeatherForCity(Mockito.anyString())).thenReturn(model);
		this.mockMvc.perform(get("/api/weather/current").param("city", "Sydney"))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$.city").value("Sydney"));
	}
	

}
