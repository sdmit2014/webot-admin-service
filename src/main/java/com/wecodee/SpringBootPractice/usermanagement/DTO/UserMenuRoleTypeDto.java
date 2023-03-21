package com.wecodee.SpringBootPractice.usermanagement.DTO;

import java.util.LinkedHashSet;
import java.util.Set;

public class UserMenuRoleTypeDto {

	private String roleType;

	private Set<UserMenuDTO> userMenuList = new LinkedHashSet<>();

	public String getRoleType() {
		return roleType;
	}

	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}

	public Set<UserMenuDTO> getUserMenuList() {
		return userMenuList;
	}

	public void setUserMenuList(Set<UserMenuDTO> userMenuList) {
		this.userMenuList = userMenuList;
	}

	@Override
	public String toString() {
		return "UserMenuRoleTypeDto [roleType=" + roleType + ", userMenuList=" + userMenuList + "]";
	}

}
