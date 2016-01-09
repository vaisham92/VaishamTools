package org.vaisham.perf.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.vaisham.perf.autogen.Builds;
import org.vaisham.perf.autogen.BuildsId;
import org.vaisham.perf.autogen.Scenarios;
import org.vaisham.perf.db.api.IGenericDAO;
import org.vaisham.perf.dto.BuildsDTO;
import org.vaisham.perf.serviceinterface.IBuildsService;
import org.vaisham.perf.sql.SqlQuery;
import org.vaisham.perf.utils.factory.ILogger;
import org.vaisham.perf.utils.factory.LoggerManager;

@Component
public class BuildsService implements IBuildsService {
	@Autowired
	@Qualifier("GlobalDAO")
	private IGenericDAO iGenericDAO;

	public static ILogger logger = LoggerManager.getLoggerFactory().getLogger(
			BuildsService.class.getName());

	public Builds createBuild(int id, int scenarioId, String description,
			byte[] buildInfo, String comments) {
		if (doesScenarioExist(scenarioId)) {
			Builds build = new Builds();
			BuildsId buildId = new BuildsId();
			buildId.setId(id);
			buildId.setScenarioId(scenarioId);
			build.setId(buildId);

			build.setBuildInfo(buildInfo);
			build.setDescription(description);
			build.setComments(comments);
			build = iGenericDAO.addUpdateEntity(build);
			return build;
		}
		return null;
	}

	public Builds updateBuild(int id, int scenarioId, String description,
			byte[] buildInfo, String comments) {
		return null;
	}
	public Builds getBuild(int id, int scenarioId) {
		if(doesScenarioExist(scenarioId)) {
			Builds build;
			List<Object> queryList = new ArrayList<Object>();
			queryList.add(id);
			queryList.add(scenarioId);
			build = iGenericDAO.getEntity(SqlQuery.GET_BUILD_BY_BUILD_ID_AND_SCENARIO_ID, queryList);
			return build;
		}
		return null;
	}

	public List<BuildsDTO> getBuildsByScenarioId(int scenarioId) {
		if(doesScenarioExist(scenarioId)) {
			List<Builds> builds;
			List<Object> queryList = new ArrayList<Object>();
			queryList.add(scenarioId);
			builds = iGenericDAO.getEntities(SqlQuery.GET_BUILDS_BY_SCENARIO_ID, queryList);
			List<BuildsDTO> buildDTOList = new ArrayList<BuildsDTO>();
			for(Builds build: builds) {
				BuildsDTO buildsDTO = new BuildsDTO(build);
				buildDTOList.add(buildsDTO);
			}
			return buildDTOList;
		}
		return null;
	}

	public void deleteBuild(int id, int scenarioId) {
	}

	private boolean doesScenarioExist(int scenarioId) {
		List<Object> queryList = new ArrayList<Object>();
		queryList.add(scenarioId);
		Scenarios scenario = iGenericDAO.getEntity(
				SqlQuery.GET_SCENARIO_BY_SCENARIO_ID, queryList);
		if (scenario != null)
			return true;
		else
			return false;
	}

}
