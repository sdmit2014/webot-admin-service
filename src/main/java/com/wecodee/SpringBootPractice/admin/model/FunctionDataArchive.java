package com.wecodee.SpringBootPractice.admin.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "BA_FUNCTION_DATA_ARCHIVE")
public class FunctionDataArchive {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String functionId;

	private String pkey;

	private String action;

	private String functionJsonData;

	private String status;

	private String createdBy;

	private Date createdDate;

	private int recordVersion;

	private String approvedBy;

	private Date approvedDate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFunctionId() {
		return functionId;
	}

	public void setFunctionId(String functionId) {
		this.functionId = functionId;
	}

	public String getPkey() {
		return pkey;
	}

	public void setPkey(String pkey) {
		this.pkey = pkey;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getFunctionJsonData() {
		return functionJsonData;
	}

	public void setFunctionJsonData(String functionJsonData) {
		this.functionJsonData = functionJsonData;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public int getRecordVersion() {
		return recordVersion;
	}

	public void setRecordVersion(int recordVersion) {
		this.recordVersion = recordVersion;
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
		return "FunctionDataArchive [id=" + id + ", functionId=" + functionId + ", pkey=" + pkey + ", action=" + action
				+ ", functionJsonData=" + functionJsonData + ", status=" + status + ", createdBy=" + createdBy
				+ ", createdDate=" + createdDate + ", recordVersion=" + recordVersion + ", approvedBy=" + approvedBy
				+ ", approvedDate=" + approvedDate + "]";
	}

}
