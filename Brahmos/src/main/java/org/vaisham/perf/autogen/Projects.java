package org.vaisham.perf.autogen;

import java.util.Date;

/**
 * 
 * @author KH1868
 *
 */
public class Projects implements java.io.Serializable {

	private static final long serialVersionUID = 4750978943649166298L;
	private Integer id;
	private String name;
	private String description;
	private Date lastModifiedDate;
	private Date createdDate;
	private boolean isDeleted;
	private String comments;

	public Projects() {
	}

	public Projects(String name, String description, Date lastModifiedDate,
			Date createdDate, boolean isDeleted, String comments) {
		this.name = name;
		this.description = description;
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
