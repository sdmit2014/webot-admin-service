package com.wecodee.SpringBootPractice.admin.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "BA_RECORD_AUDIT_TRAIL")
public class AuditTrail {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String recordId;

	private String functionId;

	private String userId;

	private String action;

	private Date dateTime;

	private String approvedBy;

	private Date approvedDate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRecordId() {
		return recordId;
	}

	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}

	public String getFunctionId() {
		return functionId;
	}

	public void setFunctionId(String functionId) {
		this.functionId = functionId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	public String getApprovedBy() {
		return approvedBy;
	}

	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}

	public Date getApprovedDate() {
		return approvedDate;
	}

	public void setApprovedDate(Date approvedDate) {
		this.approvedDate = approvedDate;
	}

	@Override
	public String toString() {
		return "AuditTrail [id=" + id + ", recordId=" + recordId + ", functionId=" + functionId + ", userId=" + userId
				+ ", action=" + action + ", dateTime=" + dateTime + ", approvedBy=" + approvedBy + ", approvedDate="
				+ approvedDate + "]";
	}

}
