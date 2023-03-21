package com.wecodee.SpringBootPractice.usermanagement.DTO;

public class RoleFunctionDTO {

	private String functionDesc;

	private String functionId;

	private Boolean viewAccess;

	private Boolean createAccess;

	private Boolean approveAccess;

	private Boolean parentFunction;

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

	@Override
	public String toString() {
		return "RoleFunctionDTO [functionDesc=" + functionDesc + ", functionId=" + functionId + ", viewAccess="
				+ viewAccess + ", createAccess=" + createAccess + ", approveAccess=" + approveAccess
				+ ", parentFunction=" + parentFunction + "]";
	}

}
