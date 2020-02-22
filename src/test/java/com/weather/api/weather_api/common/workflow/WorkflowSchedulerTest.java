package com.weather.api.weather_api.common.workflow;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;

import com.weather.api.weather_api.BasicTest;
import com.weather.api.weather_api.common.workflow.task.Task;



/**
 * Run workflow to complete the processing requirement.
 * @author Liuyuanjun
 *
 */
public class WorkflowSchedulerTest extends BasicTest {
	
	
	WorkflowScheduler scheduler = new WorkflowScheduler();
	
	@Test
	public void testSuccessFlow() throws Exception {
		
		Map<String, Object> taskMap = this.getDataMap();
		List<Task> taskList = new ArrayList<Task>();
		taskList.add(new SuccessTaskForTest());
		taskMap.put("success", taskList);
		
		ReflectionTestUtils.setField(scheduler, "workflowStore", taskMap);
		
		final boolean status = scheduler.runWorkflow("success", null, null);
		
		Assert.assertTrue(status);
	}
	
	@Test
	public void testFailedFlow() throws Exception {
		
		Map<String, Object> taskMap = this.getDataMap();
		List<Task> taskList = new ArrayList<Task>();
		taskList.add(new FailedTaskForTest());
		taskMap.put("fail", taskList);
		
		ReflectionTestUtils.setField(scheduler, "workflowStore", taskMap);
		
		final boolean status = scheduler.runWorkflow("fail", null, null);
		
		Assert.assertTrue(!status);
	}
	
	@Test
	public void testExceptoinFlow() {
		
		try {
			Map<String, Object> taskMap = this.getDataMap();
			List<Task> taskList = new ArrayList<Task>();
			taskList.add(new ExceptionTaskForTest());
			taskMap.put("fail", taskList);
			
			ReflectionTestUtils.setField(scheduler, "workflowStore", taskMap);
			final boolean status = scheduler.runWorkflow("fail", null, null);
		} catch (Exception e) {
			Assert.assertNotNull(e);
		}
		
	}

	class SuccessTaskForTest extends Task {

		@Override
		public boolean run(Map<String, Object> workflowInputData, Map<String, Object> workflowOutputData) throws Exception {
			return true;
		}
		
	}
	
	class FailedTaskForTest extends Task {

		@Override
		public boolean run(Map<String, Object> workflowInputData, Map<String, Object> workflowOutputData) throws Exception {
			return false;
		}
		
	}
	
	class ExceptionTaskForTest extends Task {
		
		@Override
		public boolean run(Map<String, Object> workflowInputData, Map<String, Object> workflowOutputData) throws Exception {
			throw new Exception();
		}
	}
}

