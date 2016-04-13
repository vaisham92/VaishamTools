package org.vaisham.perf.autogen;

/**
 * 
 * @author KH1868
 *
 */
public class ConsolidatedreportId implements java.io.Serializable {

	private static final long serialVersionUID = -8424991549602644093L;
	private int projectId;
	private int scenarioId;
	private int year;
	private String month;

	public ConsolidatedreportId() {
	}

	public ConsolidatedreportId(int projectId, int scenarioId, int year,
			String month) {
		this.projectId = projectId;
		this.scenarioId = scenarioId;
		this.year = year;
		this.month = month;
	}

	public int getProjectId() {
		return this.projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public int getScenarioId() {
		return this.scenarioId;
	}

	public void setScenarioId(int scenarioId) {
		this.scenarioId = scenarioId;
	}

	public int getYear() {
		return this.year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getMonth() {
		return this.month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ConsolidatedreportId))
			return false;
		ConsolidatedreportId castOther = (ConsolidatedreportId) other;

		return (this.getProjectId() == castOther.getProjectId())
				&& (this.getScenarioId() == castOther.getScenarioId())
				&& (this.getYear() == castOther.getYear())
				&& ((this.getMonth() == castOther.getMonth()) || (this
						.getMonth() != null && castOther.getMonth() != null && this
						.getMonth().equals(castOther.getMonth())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getProjectId();
		result = 37 * result + this.getScenarioId();
		result = 37 * result + this.getYear();
		result = 37 * result
				+ (getMonth() == null ? 0 : this.getMonth().hashCode());
		return result;
	}

}
