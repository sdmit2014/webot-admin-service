package com.wecodee.SpringBootPractice.admin.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wecodee.SpringBootPractice.admin.config.JwtRequestFilter;
import com.wecodee.SpringBootPractice.admin.constant.ResponseMessage;
import com.wecodee.SpringBootPractice.admin.model.AppFunctions;
import com.wecodee.SpringBootPractice.admin.repository.AppFunctionsRepository;
import com.wecodee.SpringBootPractice.admin.requestDTO.MenuDTO;
import com.wecodee.SpringBootPractice.admin.responseDTO.ApiResponse;
import com.wecodee.SpringBootPractice.usermanagement.DTO.UserMenuDTO;
import com.wecodee.SpringBootPractice.usermanagement.DTO.UserMenuRoleTypeDto;
import com.wecodee.SpringBootPractice.usermanagement.model.Role;
import com.wecodee.SpringBootPractice.usermanagement.model.RoleFunctions;
import com.wecodee.SpringBootPractice.usermanagement.model.UserRole;
import com.wecodee.SpringBootPractice.usermanagement.repository.RoleFunctionsRepository;
import com.wecodee.SpringBootPractice.usermanagement.repository.RoleRepository;
import com.wecodee.SpringBootPractice.usermanagement.repository.UserRolesRepository;

import net.minidev.json.JSONObject;
import netscape.javascript.JSObject;

@Service
public class FunctionService extends BaseService<AppFunctions> {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	private AppFunctionsRepository appFunctionsRepository;

	@Autowired
	private UserRolesRepository userRoleRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private RoleFunctionsRepository roleFunctionsRepository;

	public FunctionService(AppFunctionsRepository appFunctionsRepository) {
		super(appFunctionsRepository);
		this.appFunctionsRepository = appFunctionsRepository;
	}

	@Override
	public ApiResponse<List<AppFunctions>> getAll() {
		return ApiResponse.success(ResponseMessage.OPERATION_SUCCESS.getMessage(),
				appFunctionsRepository.getAllChildScreens());
	}

	public ApiResponse<JSONObject> getFunctionAccessService() {
		String userId = JwtRequestFilter.loggedInUserId;
		JSONObject userRoleFunctionAccess = new JSONObject();
		JSONObject menuObject = new JSONObject();
		log.info(userId);
		Set<String> functionIds = new HashSet<String>();
		List<RoleFunctions> listOfRoleFunctions = new ArrayList<RoleFunctions>();
		List<UserMenuRoleTypeDto> userMenuRoleTypeDtoList = new ArrayList<>();
		List<Role> roleList = new ArrayList<>();
		List<Role> roles = new ArrayList<>();
		List<UserRole> userRoles = userRoleRepository.getByUserId(userId);

		for (UserRole userRole : userRoles) {
			functionIds.addAll(roleFunctionsRepository.getFunctionId(userRole.getRoleId()));
			listOfRoleFunctions.addAll(roleFunctionsRepository.getAllRoleFunctionsOrderById(userRole.getRoleId()));
			roleList.add(roleRepository.getByRoleId(userRole.getRoleId()));
		}

		log.info("roleScreenLinks->" + listOfRoleFunctions);
		for (int i = 0; i < listOfRoleFunctions.size() - 1; i++) {
			for (int j = i + 1; j < listOfRoleFunctions.size(); j++) {
				log.info(i + "i scr->" + listOfRoleFunctions.get(i).getFunctionId());
				log.info(j + "j scr->" + listOfRoleFunctions.get(j).getFunctionId());
				if (listOfRoleFunctions.get(i).getFunctionId().equals(listOfRoleFunctions.get(j).getFunctionId())) {
					System.out.println("i =" + listOfRoleFunctions.get(i).getFunctionId());
					System.out.println("j =" + listOfRoleFunctions.get(j).getFunctionId());
					listOfRoleFunctions.add(i, unionOfAccess(listOfRoleFunctions.get(i), listOfRoleFunctions.get(j)));
					listOfRoleFunctions.remove(i + 1);
					System.out.println("after removing i + 1 " + listOfRoleFunctions);
					listOfRoleFunctions.remove(j--);
					System.out.println("after removing j-- " + listOfRoleFunctions);
					log.info("Size" + listOfRoleFunctions.size());
				}
			}
		}
		for (int i = 0; i < listOfRoleFunctions.size(); i++) {
			userRoleFunctionAccess.put(listOfRoleFunctions.get(i).getFunctionId(), listOfRoleFunctions.get(i));
		}
		// User Menu logic
		List<String> roleTypeList = new ArrayList<>();
		for (Role role : roleList) {
			if (!roleTypeList.contains(role.getRoleType()))
				roleTypeList.add(role.getRoleType());
		}
		for (String roleType : roleTypeList) {
			Set<UserMenuDTO> userMenu = new LinkedHashSet<UserMenuDTO>();
			UserMenuRoleTypeDto userMenuRoleTypeDto = new UserMenuRoleTypeDto();
			List<AppFunctions> appFunctionsLists = appFunctionsRepository.getByRoleType(roleType).stream()
					.sorted(Comparator.comparing(AppFunctions::getId)).collect(Collectors.toList());
			log.info(" sorted data " + appFunctionsLists);
			List<AppFunctions> parentFunctionList = appFunctionsLists.stream().distinct()
					.filter(res -> res.getParentFunctionId().equals(res.getFunctionId())).collect(Collectors.toList());
			for (AppFunctions appFunctions : parentFunctionList) {
				log.info("parent->" + appFunctions.getParentFunctionId());
				UserMenuDTO parentFunctionMenu = new UserMenuDTO();
				log.info("parentScreen->" + appFunctions);
				List<AppFunctions> childFunctionList = appFunctionsLists.stream()
						.filter(res -> res.getParentFunctionId().equals(appFunctions.getParentFunctionId()))
						.collect(Collectors.toList());
				log.info("cIds->" + childFunctionList);
				Boolean functionAccess = false;
				List<MenuDTO> childFunctionMenuList = new ArrayList<>();
				for (AppFunctions childFunction : childFunctionList) {
					log.info("child->" + childFunction);
					if (childFunction.getFunctionId().equals(childFunction.getParentFunctionId())) {
						parentFunctionMenu.setId(childFunction.getId());
						parentFunctionMenu.setScreenId(childFunction.getFunctionId());
						parentFunctionMenu.setName(childFunction.getFunctionName());
						parentFunctionMenu.setUrl(childFunction.getUrl());
						parentFunctionMenu.setIcon(childFunction.getIcon());
					}
					if (functionIds.contains(childFunction.getFunctionId())) {
						functionAccess = true;
						if (!childFunction.getFunctionId().equals(childFunction.getParentFunctionId())) {
							MenuDTO childFunctionMenu = new MenuDTO();
							childFunctionMenu.setScreenId(childFunction.getFunctionId());
							childFunctionMenu.setName(childFunction.getFunctionName());
							childFunctionMenu.setUrl(childFunction.getUrl());
							childFunctionMenu.setIcon(childFunction.getIcon());
							childFunctionMenuList.add(childFunctionMenu);
						}
					} else {
						functionAccess = false;
					}
					if (functionAccess) {
						if (!childFunctionMenuList.isEmpty()) {
							parentFunctionMenu.setChildren(childFunctionMenuList);
						}
						userMenu.add(parentFunctionMenu);
					}
				}
			}
			userMenuRoleTypeDto.setRoleType(roleType);
			userMenuRoleTypeDto.setUserMenuList(userMenu);
			userMenuRoleTypeDtoList.add(userMenuRoleTypeDto);
			log.info("User Menu >> " + userMenu);
		}
		menuObject.put("userMenu", userMenuRoleTypeDtoList);
		menuObject.put("userRoleFunctionAccess", userRoleFunctionAccess);
		return ApiResponse.success(ResponseMessage.FETCH_SUCCESS.getMessage(), menuObject);

	}

	public RoleFunctions unionOfAccess(RoleFunctions oldRoleScreenLink, RoleFunctions newRoleScreenLink) {
		if (oldRoleScreenLink.getApproveAccess() || newRoleScreenLink.getApproveAccess()) {
			newRoleScreenLink.setApproveAccess(true);
		}
		if (oldRoleScreenLink.getCreateAccess() || newRoleScreenLink.getCreateAccess()) {
			newRoleScreenLink.setCreateAccess(true);
		}
		if (oldRoleScreenLink.getViewAccess() || newRoleScreenLink.getViewAccess()) {
			newRoleScreenLink.setViewAccess(true);
		}
		return newRoleScreenLink;
	}

}
