package org.vaisham.perf.serviceinterface;

import java.util.List;

import org.vaisham.perf.autogen.Consolidatedreport;
import org.vaisham.perf.dto.ConsolidatedReportsDTO;
import org.vaisham.perf.dto.ConsolidatedReportsMetaDTO;

public interface IConsolidateReportService {
	public Consolidatedreport updateConsolidatedReport(int projectId,
			int scenarioId, int year, String month, String averageResponseTime,
			String maximumThroughput, String transactionCount,
			String cpuUtilization);

	public List<ConsolidatedReportsMetaDTO> getConsolidatedMeta();

	public Consolidatedreport getConsolicatedReport(int projectId,
			int scenarioId, int year, String month);

	public List<Consolidatedreport> getAllConsolidatedReports(int year,
			String month);

	public List<ConsolidatedReportsDTO> getConsolidatedReportsWithNames(
			int year, String month);
}
