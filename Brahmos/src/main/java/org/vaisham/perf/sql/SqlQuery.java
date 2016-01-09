package org.vaisham.perf.sql;

/**
 * This is the query class where you write queries to get data.
 * 
 * @author KH1868
 * 
 */
public class SqlQuery {

	// Projects Queries
	public static final String GET_ALL_PROJECTS = "SELECT P FROM Projects P";
	public static final String GET_PROJECT_BY_PROJECT_ID = "Select P from Projects P where P.id = ?";
	public static final String GET_ALL_PROJECT_NAMES = "Select P.name from Projects P";
	public static final String GET_PROJECT_NAMES_EXCEPT = "Select P.name from Projects P where P.id != ?";

	// Scenarios Queries
	public static final String GET_SCENARIOS_BY_PROJECT_ID = "Select S from Scenarios S where S.projectId = ?";
	public static final String GET_SCENARIO_BY_SCENARIO_ID = "Select S from Scenarios S where S.id = ?";
	public static final String GET_SCENARIO_NAMES_BY_PROJECT_ID = "Select S.name from Scenarios S where S.projectId = ?";
	public static final String GET_SCENARIO_BY_PROJECT_ID_AND_SCENARIO_ID = "Select S from Scenarios S where S.projectId = ? and S.id = ?";

	// Builds Queries
	public static final String GET_BUILDS_BY_SCENARIO_ID = "Select b from Builds b where b.id.scenarioId = ?";
	public static final String GET_BUILD_BY_BUILD_ID_AND_SCENARIO_ID = "Select b from Builds b where b.id.id = ? and b.id.scenarioId = ?";

	// ConsolidatedReport Queries
	public static final String GET_CONSOLIDATED_REPORTS_YEAR_AND_MONTH_DETAILS = "Select distinct c.id.year , c.id.month from Consolidatedreport c";
	public static final String GET_CONSOLIDATED_REPORT_BY_PROJECT_ID_AND_SCENARIO_ID = "Select c from Consolidatedreport c where c.id.projectId = ? and c.id.scenarioId = ? and c.id.year = ? and c.id.month like ?";
	public static final String GET_ALL_CONSOLIDATED_REPORTS = "Select c from Consolidatedreport c where c.id.year = ? and c.id.month = ?";
	public static final String GET_ALL_CONSOLIDATE_REPORT_DATA_WITH_PROJECT_AND_SCENARIO_NAMES = "select p.name, s.name, c.averageResponseTime, c.maximumThroughput, c.transactionCount, c.cpuUtilization from Projects p, Scenarios s, Consolidatedreport c where c.id.year = ? and c.id.month = ? and p.id = c.id.projectId and s.id = c.id.scenarioId";
}
