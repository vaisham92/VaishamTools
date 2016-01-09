package org.vaisham.perf.manage.exceptions;

public class ScenarioNotFoundException extends Exception {

	private static final long serialVersionUID = 6245337283790374124L;
	private int projectId;
	private int scenarioId;

	public ScenarioNotFoundException(int projectId, int scenarioId) {
		super();
		this.projectId = projectId;
		this.scenarioId = scenarioId;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public int getScenarioId() {
		return scenarioId;
	}

	public void setScenarioId(int scenarioId) {
		this.scenarioId = scenarioId;
	}

	@Override
	public String getMessage() {
		return "Exception: Scenario not found with projectId: "
				+ this.projectId + " and scenarioId: " + this.scenarioId;
	}
}
