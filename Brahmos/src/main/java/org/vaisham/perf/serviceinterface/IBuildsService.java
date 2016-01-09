package org.vaisham.perf.serviceinterface;

import java.util.List;

import org.vaisham.perf.autogen.Builds;
import org.vaisham.perf.dto.BuildsDTO;

public interface IBuildsService {

	public Builds createBuild(int id, int scenarioId, String description,
			byte[] buildInfo, String comments);

	public Builds updateBuild(int id, int scenarioId, String description,
			byte[] buildInfo, String comments);

	public Builds getBuild(int id, int scenarioId);

	public List<BuildsDTO> getBuildsByScenarioId(int scenarioId);

	public void deleteBuild(int id, int scenarioId);
}
