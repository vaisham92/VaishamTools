package org.vaisham.perf.autogen;

import java.util.Date;

/**
 * 
 * @author KH1868
 *
 */
public class Notifications implements java.io.Serializable {

	private static final long serialVersionUID = -3743433855330982167L;
	private Integer id;
	private String description;
	private String type;
	private Date createdDate;
	private boolean isDeleted;
	private String comments;

	public Notifications() {
	}

	public Notifications(String description, String type, Date createdDate,
			boolean isDeleted, String comments) {
		this.description = description;
		this.type = type;
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

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
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
