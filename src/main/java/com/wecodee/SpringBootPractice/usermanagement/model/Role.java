package com.wecodee.SpringBootPractice.usermanagement.model;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.wecodee.SpringBootPractice.admin.model.AuditProperties;

@Entity
@Table(name = "WA_ROLE")
public class Role extends AuditProperties {

	@Id
	@Column(name = "ROLE_ID", length = 50)
	private String roleId;

	@Column(name = "APP_ID", length = 50)
	private String appId;

	@Column(name = "ROLE_DESCRIPTION")
	private String roleDescription;

	@Column(name = "ROLE_TYPE")
	private String roleType;

	@Column(name = "APPROVER_TYPE")
	private String approverType;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "ROLE_ID", referencedColumnName = "ROLE_ID")
	@OrderBy("id")
	private List<RoleFunctions> roleFunctions;

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

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

	public List<RoleFunctions> getRoleFunctions() {
		return roleFunctions;
	}

	public void setRoleFunctions(List<RoleFunctions> roleFunctions) {
		this.roleFunctions = roleFunctions;
	}

	@Override
	public String toString() {
		return "Role [roleId=" + roleId + ", appId=" + appId + ", roleDescription=" + roleDescription + ", roleType="
				+ roleType + ", approverType=" + approverType + ", roleFunctions=" + roleFunctions + "]";
	}

}
