package com.wecodee.SpringBootPractice.admin.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "WA_APP_FUNCTIONS")
public class AppFunctions {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String appId;

	private String functionId;

	private String functionName;

	private String icon;

	private String parentFunctionId;

	private String url;

	private Boolean standalone;

	private String roleType;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getFunctionId() {
		return functionId;
	}

	public void setFunctionId(String functionId) {
		this.functionId = functionId;
	}

	public String getFunctionName() {
		return functionName;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getParentFunctionId() {
		return parentFunctionId;
	}

	public void setParentFunctionId(String parentFunctionId) {
		this.parentFunctionId = parentFunctionId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Boolean getStandalone() {
		return standalone;
	}

	public void setStandalone(Boolean standalone) {
		this.standalone = standalone;
	}

	public String getRoleType() {
		return roleType;
	}

	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}

	@Override
	public String toString() {
		return "AppFunctions [id=" + id + ", appId=" + appId + ", functionId=" + functionId + ", functionName="
				+ functionName + ", icon=" + icon + ", parentFunctionId=" + parentFunctionId + ", url=" + url
				+ ", standalone=" + standalone + ", roleType=" + roleType + "]";
	}

}
