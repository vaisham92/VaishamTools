package org.vaisham.perf.autogen;

import java.util.Date;

/**
 * 
 * @author KH1868
 *
 */
public class Scenarios implements java.io.Serializable {

	private static final long serialVersionUID = -2796430545547227552L;
	private Integer id;
	private int projectId;
	private String name;
	private String description;
	private String defaultBuildInfo;
	private Date lastModifiedDate;
	private Date createdDate;
	private boolean isDeleted;
	private String comments;

	public Scenarios() {
	}

	public Scenarios(int projectId, String name, String description,
			String defaultBuildInfo, Date lastModifiedDate, Date createdDate,
			boolean isDeleted, String comments) {
		this.projectId = projectId;
		this.name = name;
		this.description = description;
		this.defaultBuildInfo = defaultBuildInfo;
		this.lastModifiedDate = lastModifiedDate;
		this.createdDate = createdDate;
		this.isDeleted = isDeleted;
		this.comments = comments;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getProjectId() {
		return this.projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDefaultBuildInfo() {
		return this.defaultBuildInfo;
	}

	public void setDefaultBuildInfo(String defaultBuildInfo) {
		this.defaultBuildInfo = defaultBuildInfo;
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
