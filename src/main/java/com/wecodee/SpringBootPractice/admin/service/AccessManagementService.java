package com.wecodee.SpringBootPractice.admin.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wecodee.SpringBootPractice.admin.constant.EnumData;
import com.wecodee.SpringBootPractice.usermanagement.model.RoleFunctions;
import com.wecodee.SpringBootPractice.usermanagement.model.UserRole;
import com.wecodee.SpringBootPractice.usermanagement.repository.RoleFunctionsRepository;
import com.wecodee.SpringBootPractice.usermanagement.repository.UserRolesRepository;

@Service
public class AccessManagementService {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserRolesRepository userRolesRepository;

	@Autowired
	private RoleFunctionsRepository roleFunctionsRepository;

	public boolean checkUserAccess(String userId, String functionId, String action) {
		try {
			List<UserRole> listOfUserRoles = userRolesRepository.getByUserId(userId);
			for (UserRole userRole : listOfUserRoles) {
				RoleFunctions roleFunctions = roleFunctionsRepository.getByRoleIdAndFunctionId(userRole.getUserId(),
						functionId);
				if (action.equals(EnumData.CREATE_ACCESS.getName()) && roleFunctions != null
						&& roleFunctions.getCreateAccess()) {
					return true;
				} else if (action.equals(EnumData.APPROVE_ACCESS.getName()) && roleFunctions != null
						&& roleFunctions.getApproveAccess()) {
					return true;
				} else if (action.equals(EnumData.VIEW_ACCESS.getName()) && roleFunctions != null
						&& roleFunctions.getViewAccess()) {
					return true;
				}
			}
			return false;
		} catch (Exception e) {
			log.error("In checkUserAccess exception", e);
			return false;
		}
	}
	
	public boolean checkApprover() {
		return false;
		
	}

}
