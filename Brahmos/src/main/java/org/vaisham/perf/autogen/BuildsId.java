package org.vaisham.perf.autogen;

/**
 * 
 * @author KH1868
 *
 */
public class BuildsId implements java.io.Serializable {

	private static final long serialVersionUID = -4877834997621441002L;
	private int id;
	private int scenarioId;

	public BuildsId() {
	}

	public BuildsId(int id, int scenarioId) {
		this.id = id;
		this.scenarioId = scenarioId;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getScenarioId() {
		return this.scenarioId;
	}

	public void setScenarioId(int scenarioId) {
		this.scenarioId = scenarioId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof BuildsId))
			return false;
		BuildsId castOther = (BuildsId) other;

		return (this.getId() == castOther.getId())
				&& (this.getScenarioId() == castOther.getScenarioId());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getId();
		result = 37 * result + this.getScenarioId();
		return result;
	}

}
