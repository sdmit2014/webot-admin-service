package com.wecodee.SpringBootPractice.usermanagement.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "WA_ROLE_FUNCTIONS")
public class RoleFunctions {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "ROLE_ID")
	private String roleId;

	@Column(name = "FUNCTION_ID")
	private String functionId;

	@Column(name = "FUNCTION_NAME")
	private String functionName;

	@Column(name = "VIEW_ACCESS")
	private Boolean viewAccess;

	@Column(name = "CREATE_ACCESS")
	private Boolean createAccess;

	@Column(name = "APPROVE_ACCESS")
	private Boolean approveAccess;

	@Column(name = "PARENT_FUNCTION")
	private Boolean parentFunction;

	@Column(name = "PARENT_FUNCTION_ID")
	private String parentFunctionId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
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

	public Boolean getViewAccess() {
		return viewAccess;
	}

	public void setViewAccess(Boolean viewAccess) {
		this.viewAccess = viewAccess;
	}

	public Boolean getCreateAccess() {
		return createAccess;
	}

	public void setCreateAccess(Boolean createAccess) {
		this.createAccess = createAccess;
	}

	public Boolean getApproveAccess() {
		return approveAccess;
	}

	public void setApproveAccess(Boolean approveAccess) {
		this.approveAccess = approveAccess;
	}

	public Boolean getParentFunction() {
		return parentFunction;
	}

	public void setParentFunction(Boolean parentFunction) {
		this.parentFunction = parentFunction;
	}

	public String getParentFunctionId() {
		return parentFunctionId;
	}

	public void setParentFunctionId(String parentFunctionId) {
		this.parentFunctionId = parentFunctionId;
	}

	@Override
	public String toString() {
		return "RoleFunctions [id=" + id + ", roleId=" + roleId + ", functionId=" + functionId + ", functionName="
				+ functionName + ", viewAccess=" + viewAccess + ", createAccess=" + createAccess + ", approveAccess="
				+ approveAccess + ", parentFunction=" + parentFunction + ", parentFunctionId=" + parentFunctionId + "]";
	}

}
