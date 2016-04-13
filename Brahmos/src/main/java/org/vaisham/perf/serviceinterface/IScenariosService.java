package org.vaisham.perf.serviceinterface;

import java.util.List;

import org.vaisham.perf.autogen.Scenarios;
import org.vaisham.perf.manage.exceptions.ScenarioNotFoundException;

public interface IScenariosService {

	public Scenarios createScenario(int projectId, String name,
			String description, String defaultBuildInfo, String comments);

	public Scenarios updateScenario(int projectId, int scenarioId, String name,
			String description, String defaultBuildInfo, String comments);

	public List<Scenarios> getScenariosByProject(int projectId);

	public Scenarios getScenario(int scenarioId);

	public void deleteScenario(int projectId, int scenarioId) throws ScenarioNotFoundException;

}
