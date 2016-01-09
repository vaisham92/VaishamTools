package org.vaisham.perf.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.vaisham.perf.autogen.Consolidatedreport;
import org.vaisham.perf.autogen.Projects;
import org.vaisham.perf.autogen.Scenarios;
import org.vaisham.perf.db.api.IGenericDAO;
import org.vaisham.perf.dto.ConsolidatedReportsDTO;
import org.vaisham.perf.dto.ConsolidatedReportsMetaDTO;
import org.vaisham.perf.serviceinterface.IConsolidateReportService;
import org.vaisham.perf.sql.SqlQuery;
import org.vaisham.perf.utils.factory.ILogger;
import org.vaisham.perf.utils.factory.LoggerManager;

@Component
public class ConsolidateReportService implements IConsolidateReportService {
	@Autowired
	@Qualifier("GlobalDAO")
	private IGenericDAO iGenericDAO;

	public static ILogger logger = LoggerManager.getLoggerFactory().getLogger(
			ConsolidateReportService.class.getName());

	@Transactional
	public Consolidatedreport updateConsolidatedReport(int projectId,
			int scenarioId, int year, String month, String averageResponseTime,
			String maximumThroughput, String transactionCount,
			String cpuUtilization) {
		if (areProjectAndScenarioValid(projectId, scenarioId)) {
			Consolidatedreport consolidatedReport;
			List<Object> queryList = new ArrayList<Object>();
			queryList.add(projectId);
			queryList.add(scenarioId);
			queryList.add(year);
			queryList.add(month);
			consolidatedReport = iGenericDAO
					.getEntity(
							SqlQuery.GET_CONSOLIDATED_REPORT_BY_PROJECT_ID_AND_SCENARIO_ID,
							queryList);
			consolidatedReport.setAverageResponseTime(averageResponseTime);
			consolidatedReport.setMaximumThroughput(maximumThroughput);
			consolidatedReport.setTransactionCount(transactionCount);
			consolidatedReport.setCpuUtilization(cpuUtilization);
			consolidatedReport = iGenericDAO.updateEntity(consolidatedReport);
			return consolidatedReport;
		}
		return null;
	}

	public List<ConsolidatedReportsMetaDTO> getConsolidatedMeta() {
		List<Object[]> data = iGenericDAO
				.getEntities(
						SqlQuery.GET_CONSOLIDATED_REPORTS_YEAR_AND_MONTH_DETAILS,
						null);
		List<ConsolidatedReportsMetaDTO> consolidatedReportsMetaList = new ArrayList<ConsolidatedReportsMetaDTO>();
		for (int i = 0; i < data.size(); i++) {
			Object[] reportData = data.get(i);
			ConsolidatedReportsMetaDTO consolidatedMeta = new ConsolidatedReportsMetaDTO();
			consolidatedMeta.setYear((Integer) reportData[0]);
			consolidatedMeta.setMonth((String) reportData[1]);
			consolidatedReportsMetaList.add(consolidatedMeta);
		}
		return consolidatedReportsMetaList;
	}

	@Transactional
	public Consolidatedreport getConsolicatedReport(int projectId,
			int scenarioId, int year, String month) {
		if (areProjectAndScenarioValid(projectId, scenarioId)) {
			List<Object> queryList = new ArrayList<Object>();
			queryList.add(projectId);
			queryList.add(scenarioId);
			queryList.add(year);
			queryList.add(month);
			Consolidatedreport consolidatedReport;
			consolidatedReport = iGenericDAO
					.getEntity(
							SqlQuery.GET_CONSOLIDATED_REPORT_BY_PROJECT_ID_AND_SCENARIO_ID,
							queryList);
			return consolidatedReport;
		}
		return null;
	}

	@Transactional
	public List<Consolidatedreport> getAllConsolidatedReports(int year, String month) {
		List<Object> queryList = new ArrayList<Object>();
		queryList.add(year);
		queryList.add(month);
		List<Consolidatedreport> consolidatedReports;
		consolidatedReports = iGenericDAO.getEntities(
				SqlQuery.GET_ALL_CONSOLIDATED_REPORTS, queryList);
		return consolidatedReports;
	}

	public List<ConsolidatedReportsDTO> getConsolidatedReportsWithNames(int year, String month) {
		List<Object> queryList = new ArrayList<Object>();
		queryList.add(year);
		queryList.add(month);
		List<Object[]> data = iGenericDAO
				.getEntities(
						SqlQuery.GET_ALL_CONSOLIDATE_REPORT_DATA_WITH_PROJECT_AND_SCENARIO_NAMES,
						queryList);
		List<ConsolidatedReportsDTO> consolidatedReportsDataList = new ArrayList<ConsolidatedReportsDTO>();
		for (int i = 0; i < data.size(); i++) {
			Object[] reportData = data.get(i);
			ConsolidatedReportsDTO consolidatedData = new ConsolidatedReportsDTO();
			consolidatedData.setProjectName((String) reportData[0]);
			consolidatedData.setScenarioName((String) reportData[1]);
			consolidatedData.setAverageResponseTime((String) reportData[2]);
			consolidatedData.setMaximumThroughput((String) reportData[3]);
			consolidatedData.setTransactionCount((String) reportData[4]);
			consolidatedData.setCpuUtilization((String) reportData[5]);
			consolidatedReportsDataList.add(consolidatedData);
		}
		return consolidatedReportsDataList;
	}

	private boolean areProjectAndScenarioValid(int projectId, int scenarioId) {
		Projects project;
		List<Object> queryList = new ArrayList<Object>();
		queryList.add(projectId);
		project = iGenericDAO.getEntity(SqlQuery.GET_PROJECT_BY_PROJECT_ID,
				queryList);
		if (project == null)
			return false;
		else {
			Scenarios scenario;
			queryList.clear();
			queryList.add(scenarioId);
			scenario = iGenericDAO.getEntity(
					SqlQuery.GET_SCENARIO_BY_SCENARIO_ID, queryList);
			if (project.getId() == scenario.getProjectId())
				return true;
			else
				return false;
		}
	}


}
