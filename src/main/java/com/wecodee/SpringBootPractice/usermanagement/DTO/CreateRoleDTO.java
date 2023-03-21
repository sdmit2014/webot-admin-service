package com.wecodee.SpringBootPractice.usermanagement.DTO;

import java.util.List;

public class CreateRoleDTO {

	private String appId;

	private String roleDescription;

	private String roleId;

	private String roleType;

	private String approverType;

	private List<RoleFunctionMenuDTO> roleFunctions;

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getRoleDescription() {
		return roleDescription;
	}

	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleType() {
		return roleType;
	}

	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}

	public String getApproverType() {
		return approverType;
	}

	public void setApproverType(String approverType) {
		this.approverType = approverType;
	}

	public List<RoleFunctionMenuDTO> getRoleFunctions() {
		return roleFunctions;
	}

	public void setRoleFunctions(List<RoleFunctionMenuDTO> roleFunctions) {
		this.roleFunctions = roleFunctions;
	}

	@Override
	public String toString() {
		return "CreateRoleDTO [appId=" + appId + ", roleDescription=" + roleDescription + ", roleId=" + roleId
				+ ", roleType=" + roleType + ", approverType=" + approverType + ", roleFunctions=" + roleFunctions
				+ "]";
	}

}
