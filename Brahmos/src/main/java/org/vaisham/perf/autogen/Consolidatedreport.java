package org.vaisham.perf.autogen;

import java.util.Date;

/**
 * 
 * @author KH1868
 *
 */
public class Consolidatedreport implements java.io.Serializable {

	private static final long serialVersionUID = -2843716108484791558L;
	private ConsolidatedreportId id;
	private String averageResponseTime;
	private String maximumThroughput;
	private String transactionCount;
	private String cpuUtilization;
	private Date lastModifiedDate;
	private Date createdDate;
	private boolean isDeleted;
	private String comments;

	public Consolidatedreport() {
	}

	public Consolidatedreport(ConsolidatedreportId id,
			String averageResponseTime, String maximumThroughput,
			String transactionCount, String cpuUtilization,
			Date lastModifiedDate, Date createdDate, boolean isDeleted,
			String comments) {
		this.id = id;
		this.averageResponseTime = averageResponseTime;
		this.maximumThroughput = maximumThroughput;
		this.transactionCount = transactionCount;
		this.cpuUtilization = cpuUtilization;
		this.lastModifiedDate = lastModifiedDate;
		this.createdDate = createdDate;
		this.isDeleted = isDeleted;
		this.comments = comments;
	}

	public ConsolidatedreportId getId() {
		return this.id;
	}

	public void setId(ConsolidatedreportId id) {
		this.id = id;
	}

	public String getAverageResponseTime() {
		return this.averageResponseTime;
	}

	public void setAverageResponseTime(String averageResponseTime) {
		this.averageResponseTime = averageResponseTime;
	}

	public String getMaximumThroughput() {
		return this.maximumThroughput;
	}

	public void setMaximumThroughput(String maximumThroughput) {
		this.maximumThroughput = maximumThroughput;
	}

	public String getTransactionCount() {
		return this.transactionCount;
	}

	public void setTransactionCount(String transactionCount) {
		this.transactionCount = transactionCount;
	}

	public String getCpuUtilization() {
		return this.cpuUtilization;
	}

	public void setCpuUtilization(String cpuUtilization) {
		this.cpuUtilization = cpuUtilization;
	}

	public Date getLastModifiedDate() {
		return this.lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public boolean isIsDeleted() {
		return this.isDeleted;
	}

	public void setIsDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

}
