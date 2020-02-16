package com.weather.api.weather_api.current.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.weather.api.weather_api.BasicTest;
import com.weather.api.weather_api.common.workflow.WorkflowScheduler;
import com.weather.api.weather_api.current.model.CurrentWeatherModel;

/**
 * Top level service for getting current weather.
 * 
 * @author Liuyuanjun
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class CurrentWeatherServiceTest extends BasicTest {

	@InjectMocks
	private CurrentWeatherService service = new CurrentWeatherService();

	@Mock
	private WorkflowScheduler workflowScheduler;

	/**
	 * Should not throw exception.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testQueryCurrentWeatherForCity() throws Exception {

		Mockito.when(workflowScheduler.runWorkflow(Mockito.anyString(), Mockito.anyMap(), Mockito.anyMap()))
				.thenReturn(true);

		CurrentWeatherModel model = service.queryCurrentWeatherForCity("Sydney");

		Assert.assertNull(model);

	}

	/**
	 * Should not throw exception.
	 * 
	 * @throws Exception
	 */
	@Test(expected = Exception.class)
	public void testQueryCurrentWeatherForCityFailed() throws Exception {

		Mockito.when(workflowScheduler.runWorkflow(Mockito.anyString(), Mockito.anyMap(), Mockito.anyMap()))
				.thenReturn(false);
		
		CurrentWeatherModel model = service.queryCurrentWeatherForCity("Sydney");

	}

}
