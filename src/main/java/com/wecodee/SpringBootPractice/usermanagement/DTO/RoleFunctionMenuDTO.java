package com.wecodee.SpringBootPractice.usermanagement.DTO;

import java.util.List;

public class RoleFunctionMenuDTO {

	private String functionDesc;

	private String functionId;

	private Boolean viewAccess;

	private Boolean createAccess;

	private Boolean approveAccess;

	private Boolean parentFunction;

	private List<ChildRoleFunctionsDTO> childRoleFunctionsList;

	public String getFunctionDesc() {
		return functionDesc;
	}

	public void setFunctionDesc(String functionDesc) {
		this.functionDesc = functionDesc;
	}

	public String getFunctionId() {
		return functionId;
	}

	public void setFunctionId(String functionId) {
		this.functionId = functionId;
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

	public List<ChildRoleFunctionsDTO> getChildRoleFunctionsList() {
		return childRoleFunctionsList;
	}

	public void setChildRoleFunctionsList(List<ChildRoleFunctionsDTO> childRoleFunctionsList) {
		this.childRoleFunctionsList = childRoleFunctionsList;
	}

	@Override
	public String toString() {
		return "RoleFunctionMenuDTO [functionDesc=" + functionDesc + ", functionId=" + functionId + ", viewAccess="
				+ viewAccess + ", createAccess=" + createAccess + ", approveAccess=" + approveAccess
				+ ", parentFunction=" + parentFunction + ", childRoleFunctionsList=" + childRoleFunctionsList + "]";
	}

}
