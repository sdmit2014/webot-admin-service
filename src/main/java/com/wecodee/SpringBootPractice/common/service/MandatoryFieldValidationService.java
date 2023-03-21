package com.wecodee.SpringBootPractice.common.service;

import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.wecodee.SpringBootPractice.appconfiguration.model.EmailConfig;
import com.wecodee.SpringBootPractice.usermanagement.model.Role;
import com.wecodee.SpringBootPractice.usermanagement.model.User;

@Service
public class MandatoryFieldValidationService {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	public Map<Boolean, String> validateUserFields(User user) {
		Map<Boolean, String> map = new HashedMap<Boolean, String>();
		if (user.getEmailId() == null || user.getEmailId().isBlank()) {
			map.put(false, "User Mail Id is Mandatory");
		} else if (user.getUserId() == null || user.getUserId().isBlank()) {
			map.put(false, "User Id is Mandatory");
		} else if (user.getPassword() == null || user.getPassword().isBlank()) {
			map.put(false, "User Password  is Mandatory");
		} else if (user.getFirstName() == null || user.getFirstName().isBlank()) {
			map.put(false, "User First Name is Mandatory");
		} else if (user.getLastName() == null || user.getLastName().isBlank()) {
			map.put(false, "User Last Name is Mandatory");
		} else if (user.getPhoneNumber() == null || user.getPhoneNumber().isBlank()) {
			map.put(false, "User PhoneNumber is Mandatory");
		} else if (user.getCountryCode() == null || user.getCountryCode().isBlank()) {
			map.put(false, "User CountryCode is Mandatory");
		} else if (user.getUserRoles() == null || user.getUserRoles().size() == 0) {
			map.put(false, "User Role is Mandatory");
		}
		return map;
	}

	public Map<Boolean, String> validateRoleFields(Role role) {
		Map<Boolean, String> map = new HashedMap<Boolean, String>();
		if (role.getRoleId() == null || role.getRoleId().isBlank()) {
			map.put(false, "Role Id is Mandatory");
		} else if (role.getRoleType() == null || role.getRoleType().isBlank()) {
			map.put(false, "Role Type is Mandatory");
		} else if (role.getRoleDescription() == null || role.getRoleDescription().isBlank()) {
			map.put(false, "Role Description is Mandatory");
		} else if (role.getAppId() == null || role.getAppId().isBlank()) {
			map.put(false, "Role App Id is Mandatory");
		} else if (role.getRoleFunctions() == null || role.getRoleFunctions().size() == 0) {
			map.put(false, "Role Function is Mandatory");
		}
		return map;
	}

	public Map<Boolean, String> validateEmailConfigFields(EmailConfig emailConfig) {
		Map<Boolean, String> map = new HashedMap<Boolean, String>();
		if (emailConfig.getHost() == null || emailConfig.getHost().isBlank()) {
			map.put(false, "Mail Server Name is mandatory");
		} else if (emailConfig.getPort() == null || emailConfig.getPort() == 0) {
			map.put(false, "SMTP Port is mandatory");
		} else if (emailConfig.getEmailId() == null || emailConfig.getEmailId().isBlank()) {
			map.put(false, "Mail Server User is mandatory");
		} else if (emailConfig.getPassword() == null || emailConfig.getPassword().isBlank()) {
			map.put(false, "Mail Server Password is mandatory");
		} else if (emailConfig.getFromEmail() == null || emailConfig.getFromEmail().isBlank()) {
			map.put(false, "From Email is mandatory");
		}
		return map;
	}

	// ATM Details
//		public Map<Boolean, String> validateAtmDetailsFields(AtmDetails atmDetails) {
//			Map<Boolean, String> map = new HashedMap<Boolean, String>();
//			if (atmDetails.getAtmCode() == null || atmDetails.getAtmCode().isBlank()) {
//				map.put(false, "ATM Code is Mandatory");
//			} else if (atmDetails.getAtmName() == null || atmDetails.getAtmName().isBlank()) {
//				map.put(false, "ATM Name is Mandatory");
//			} else if (atmDetails.getAddress() == null || atmDetails.getAddress().isBlank()) {
//				map.put(false, "ATM Address is Mandatory");
//			} else if (atmDetails.getArea() == null || atmDetails.getArea().isBlank()) {
//				map.put(false, "Area is Mandatory");
//			} else if (atmDetails.getTown() == null || atmDetails.getTown().isBlank()) {
//				map.put(false, "Town is Mandatory");
//			} else if (atmDetails.getRegion() == null || atmDetails.getRegion().isBlank()) {
//				map.put(false, "Region is Mandatory");
//			} else if (atmDetails.getLongx() == null || atmDetails.getLongx().isBlank()) {
//				map.put(false, "Longx is Mandatory");
//			} else if (atmDetails.getLaty() == null || atmDetails.getLaty().isBlank()) {
//				map.put(false, "Laty is Mandatory");
//			}
//			return map;
//		}

	// Branch Details
//		public Map<Boolean, String> validateBranchDetailsFields(BranchDetails branchDetails) {
//			Map<Boolean, String> map = new HashedMap<Boolean, String>();
//			if (branchDetails.getBranchName() == null || branchDetails.getBranchName().isBlank()) {
//				map.put(false, "Branch Name is Mandatory");
//			} else if (branchDetails.getBranchCode() == null || branchDetails.getBranchCode().isBlank()) {
//				map.put(false, "Branch Code is Mandatory");
//			} else if (branchDetails.getBranchTelephoneNumber() == null
//					|| branchDetails.getBranchTelephoneNumber().isBlank()) {
//				map.put(false, "Branch TelephoneNumber is Mandatory");
//			} else if (branchDetails.getAddress() == null || branchDetails.getAddress().isBlank()) {
//				map.put(false, "Address is Mandatory");
//			} else if (branchDetails.getArea() == null || branchDetails.getArea().isBlank()) {
//				map.put(false, "Area is Mandatory");
//			} else if (branchDetails.getTown() == null || branchDetails.getTown().isBlank()) {
//				map.put(false, "Town is Mandatory");
//			} else if (branchDetails.getRegion() == null || branchDetails.getRegion().isBlank()) {
//				map.put(false, "Region is Mandatory");
//			} else if (branchDetails.getSunday() == null || branchDetails.getSunday().isBlank()) {
//				map.put(false, "Sunday is Mandatory");
//			} else if (branchDetails.getMonday() == null || branchDetails.getMonday().isBlank()) {
//				map.put(false, "Monday is Mandatory");
//			} else if (branchDetails.getTuesday() == null || branchDetails.getTuesday().isBlank()) {
//				map.put(false, "Tuesday is Mandatory");
//			} else if (branchDetails.getWednesday() == null || branchDetails.getWednesday().isBlank()) {
//				map.put(false, "Wednesday is Mandatory");
//			} else if (branchDetails.getThursday() == null || branchDetails.getThursday().isBlank()) {
//				map.put(false, "Thursday is Mandatory");
//			} else if (branchDetails.getFriday() == null || branchDetails.getFriday().isBlank()) {
//				map.put(false, "Friday is Mandatory");
//			} else if (branchDetails.getSaturday() == null || branchDetails.getSaturday().isBlank()) {
//				map.put(false, "Saturday is Mandatory");
//			}
//			return map;
//		}

}
