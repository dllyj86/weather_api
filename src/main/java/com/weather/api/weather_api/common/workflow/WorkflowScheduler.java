package com.weather.api.weather_api.common.workflow;

import java.util.List;
import java.util.Map;

import com.weather.api.weather_api.common.workflow.task.Task;

/**
 * Run workflow to complete the processing requirement.
 * @author Liuyuanjun
 *
 */
public class WorkflowScheduler {

	/**
	 * Keep all workflow.
	 * Each workflow contains at least 1 task. 
	 * A task is a service.
	 * 
	 * command pattern
	 */
	private Map<String, List<Task>> workflowStore;
	
	/**
	 * Run a workflow
	 * @param workflowName	name of workflow
	 * @param workflowInputData	inputData of the workflow.
	 * @param workflowOutputData	keep data of workflow. The data will be shared within tasks. 
	 * 	The final result data will be in this object as well.
	 * @return	success or failure result
	 * @throws Exception exception is from scheduler or task
	 */
	public boolean runWorkflow(final String workflowName, final Map<String, Object> workflowInputData,
			final Map<String, Object> workflowOutputData) throws Exception {

		boolean status = false;

		final List<Task> workflow = this.workflowStore.get(workflowName);

		if (workflow != null) {
			for (Task task : workflow) {
				status = task.run(workflowInputData, workflowOutputData);
				// If task status is false, break workflow and return false.
				if (!status) {
					break;
				}
			}
		} else {
			// If there is no matched workflow, throw error.
			throw new Exception("No workflow matched for workflow name: " + workflowName);
		}

		return status;
	}

	public Map<String, List<Task>> getWorkflowStore() {
		return workflowStore;
	}

	public void setWorkflowStore(Map<String, List<Task>> workflowStore) {
		this.workflowStore = workflowStore;
	}
	
	
}
