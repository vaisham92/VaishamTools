package org.vaisham.perf.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.vaisham.perf.autogen.Projects;
import org.vaisham.perf.autogen.Scenarios;
import org.vaisham.perf.db.api.IGenericDAO;
import org.vaisham.perf.manage.exceptions.ScenarioNotFoundException;
import org.vaisham.perf.serviceinterface.IScenariosService;
import org.vaisham.perf.sql.SqlQuery;
import org.vaisham.perf.utils.factory.ILogger;
import org.vaisham.perf.utils.factory.LoggerManager;

@Component
public class ScenariosService implements IScenariosService {
	@Autowired
	@Qualifier("GlobalDAO")
	private IGenericDAO iGenericDAO;

	private List<String> scenarioNames;

	public static ILogger logger = LoggerManager.getLoggerFactory().getLogger(
			ScenariosService.class.getName());

	@Transactional
	public Scenarios createScenario(int projectId, String name,
			String description, String defaultBuildInfo, String comments) {
		if (doesProjectExist(projectId)) {
			if (doesScenarioNameExist(name, projectId))
				return null;
			else {
				Scenarios scenario = new Scenarios();
				scenario.setName(name);
				scenario.setProjectId(projectId);
				scenario.setDescription(description);
				scenario.setDefaultBuildInfo(defaultBuildInfo);
				scenario.setComments(comments);
				scenario = iGenericDAO.addUpdateEntity(scenario);
				scenarioNames.add(name);
				return scenario;
			}
		} else
			return null;
	}

	@Transactional
	public Scenarios updateScenario(int projectId, int scenarioId, String name,
			String description, String defaultBuildInfo, String comments) {
		List<Object> queryList = new ArrayList<Object>();
		queryList.add(projectId);
		queryList.add(scenarioId);
		Scenarios scenario = iGenericDAO.getEntity(
				SqlQuery.GET_SCENARIO_BY_PROJECT_ID_AND_SCENARIO_ID, queryList);
		if (scenario == null)
			return null;
		else {
			if (scenario.getName() != name) {
				if (!doesScenarioNameExist(name, projectId))
					scenario.setName(name);
				else
					return null;
			}
			scenario.setDescription(description);
			scenario.setDefaultBuildInfo(defaultBuildInfo);
			scenario.setComments(comments);
			return iGenericDAO.updateEntity(scenario);
		}
	}

	public List<Scenarios> getScenariosByProject(int projectId) {
		List<Object> queryList = new ArrayList<Object>();
		queryList.add(projectId);
		return iGenericDAO.getEntities(SqlQuery.GET_SCENARIOS_BY_PROJECT_ID,
				queryList);
	}

	@Transactional
	public Scenarios getScenario(int scenarioId) {
		List<Object> queryList = new ArrayList<Object>();
		queryList.add(scenarioId);
		return iGenericDAO.getEntity(SqlQuery.GET_SCENARIO_BY_SCENARIO_ID,
				queryList);
	}

	@Transactional
	public void deleteScenario(int projectId, int scenarioId) throws ScenarioNotFoundException {
		Scenarios scenario;
		List<Object> queryList = new ArrayList<Object>();
		queryList.add(scenarioId);
		scenario = iGenericDAO.getEntity(SqlQuery.GET_SCENARIO_BY_SCENARIO_ID,
				queryList);
		if (scenario == null)
			throw new ScenarioNotFoundException(projectId, scenarioId);
		else {
			iGenericDAO.deleteEntity(scenario);
		}
	}

	private boolean doesProjectExist(int projectId) {
		List<Object> queryList = new ArrayList<Object>();
		queryList.add(projectId);
		Projects project = iGenericDAO.getEntity(
				SqlQuery.GET_PROJECT_BY_PROJECT_ID, queryList);
		if (project != null)
			return true;
		else
			return false;
	}

	private boolean doesScenarioNameExist(String name, int projectId) {
		if (scenarioNames == null) {
			List<Object> queryList = new ArrayList<Object>();
			queryList.add(projectId);
			scenarioNames = iGenericDAO.getEntities(
					SqlQuery.GET_SCENARIO_NAMES_BY_PROJECT_ID, queryList);
		}
		if (scenarioNames.contains(name))
			return true;
		else
			return false;
	}
}
