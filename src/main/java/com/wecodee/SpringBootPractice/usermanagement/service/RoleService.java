package com.wecodee.SpringBootPractice.usermanagement.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wecodee.SpringBootPractice.admin.config.MakerConfig;
import com.wecodee.SpringBootPractice.admin.constant.EnumData;
import com.wecodee.SpringBootPractice.admin.constant.Function;
import com.wecodee.SpringBootPractice.admin.constant.ResponseMessage;
import com.wecodee.SpringBootPractice.admin.constant.ValidationMessage;
import com.wecodee.SpringBootPractice.admin.model.AppFunctions;
import com.wecodee.SpringBootPractice.admin.model.FunctionData;
import com.wecodee.SpringBootPractice.admin.model.FunctionDataArchive;
import com.wecodee.SpringBootPractice.admin.model.LookupValues;
import com.wecodee.SpringBootPractice.admin.repository.AppFunctionsRepository;
import com.wecodee.SpringBootPractice.admin.repository.FunctionDataArchiveRepository;
import com.wecodee.SpringBootPractice.admin.repository.FunctionDataRepository;
import com.wecodee.SpringBootPractice.admin.requestDTO.SearchFilterDTO;
import com.wecodee.SpringBootPractice.admin.responseDTO.ApiResponse;
import com.wecodee.SpringBootPractice.admin.service.AccessManagementService;
import com.wecodee.SpringBootPractice.admin.service.AuditTrailService;
import com.wecodee.SpringBootPractice.admin.service.BaseService;
import com.wecodee.SpringBootPractice.admin.util.Helper;
import com.wecodee.SpringBootPractice.common.service.CommonService;
import com.wecodee.SpringBootPractice.common.service.MandatoryFieldValidationService;
import com.wecodee.SpringBootPractice.common.utils.ListUtils;
import com.wecodee.SpringBootPractice.usermanagement.DTO.CreateRoleDTO;
import com.wecodee.SpringBootPractice.usermanagement.DTO.RoleFunctionMenuDTO;
import com.wecodee.SpringBootPractice.usermanagement.model.Role;
import com.wecodee.SpringBootPractice.usermanagement.model.RoleFunctions;
import com.wecodee.SpringBootPractice.usermanagement.repository.RoleFunctionsRepository;
import com.wecodee.SpringBootPractice.usermanagement.repository.RoleRepository;

import net.minidev.json.JSONObject;

@Service
public class RoleService extends BaseService<Role> {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	private RoleRepository roleRepository;

	@Autowired
	private EntityManager entityManager;
	@Autowired
	private RoleFunctionsRepository roleFunctionsRepository;
	@Autowired
	private FunctionDataRepository functionDataRepository;
	@Autowired
	private FunctionDataArchiveRepository functionDataArchiveRepository;
	@Autowired
	private CommonService commonService;
	@Autowired
	private AccessManagementService accessManagementService;
	@Autowired
	private MakerConfig makerConfig;
	@Autowired
	private MandatoryFieldValidationService mandatoryFieldValidationService;
	@Autowired
	private AppFunctionsRepository appFunctionsRepository;
	@Autowired
	private AuditTrailService auditTrailService;

	public RoleService(RoleRepository roleRepository) {
		super(roleRepository);
		this.roleRepository = roleRepository;
	}

	@Override
	public String getFunctionName() {
		return Function.ROLE.getName();
	}

	@Override
	public ApiResponse<JSONObject> getUnapprovedRecords(SearchFilterDTO<Role> filterDTO) {
		log.info("In role service getUnapprovedRecords method is executing");
		try {
			ObjectMapper mapper = new ObjectMapper();
			JSONObject jsonObject = new JSONObject();
			log.info("unapprove filter");
			Role role = filterDTO.getFilterFieldValues();
			List<Role> finalList = new ArrayList<Role>();
			List<String> ids = new ArrayList<String>();
			List<FunctionData> functionDataList = functionDataRepository.findByFunctionId(Function.ROLE.getName());
			for (FunctionData functionData : functionDataList) {
				if (functionData.getStatus().equals(EnumData.INACTIVE.getName())) {
					Role data = mapper.readValue(functionData.getFunctionJsonData(), Role.class);
					ids.add(data.getRoleId());
					finalList.add(data);
					log.info("data->" + data);
				}
			}
			List<Role> filteredList = new ArrayList<Role>();
			filteredList = finalList;
			if (role.getRoleId() != null && !role.getRoleId().isBlank()) {
				filteredList = filteredList.stream()
						.filter(c -> c.getRoleId().toLowerCase().contains(role.getRoleId().toLowerCase()))
						.collect(Collectors.toList());
			}
			return ApiResponse.success(ResponseMessage.ROLE_UNAPPROVED_RECORDS.getMessage(),
					getPaginatedList(filteredList, filterDTO.getPageNumber(), filterDTO.getPageSize(),
							filterDTO.getSortBy(), filterDTO.getDirection()));
		} catch (Exception e) {
			log.error("In getUnapprovedRecords exception", e);
			return ApiResponse.failure(ResponseMessage.OPERATION_FAILED.getMessage());
		}
	}

	private JSONObject getPaginatedList(List<Role> sourceList, int pageNumber, int pageSize, String sortBy,
			String direction) {
		log.info("In RoleService, getPaginatedList is executing");
		try {
			if (sortBy != null && !sortBy.isBlank()) {
				ListUtils.sort(sourceList, sortBy, direction);
			}
			return ListUtils.getPaginatedList(sourceList, pageNumber, pageSize);
		} catch (Exception e) {
			log.info("In Exception", e);
			return null;
		}
	}

	@Override
	public ApiResponse<JSONObject> getApprovedRecords(SearchFilterDTO<Role> filterDTO) {
		log.info("In role service getApprovedRecords method is executing");
		try {
			log.info("approve filter");
			log.info("unapprove filter");
			JSONObject jsonObject = new JSONObject();
			Role roles = filterDTO.getFilterFieldValues();

			CriteriaBuilder builder = entityManager.getCriteriaBuilder();
			CriteriaQuery<Role> criteria = builder.createQuery(Role.class);
			Root<Role> root = criteria.from(Role.class);
			List<Predicate> predicates = new ArrayList<Predicate>();

			log.info("Message " + filterDTO.getPageNumber());
			log.info("Size " + filterDTO.getPageSize());

			Pageable pageable = PageRequest.of(filterDTO.getPageNumber(), filterDTO.getPageSize(),
					Sort.by("roleId").ascending());

			if (roles.getRoleId() != null && !roles.getRoleId().isBlank()) {
				predicates.add(
						builder.like(builder.lower(root.get("roleId")), "%" + roles.getRoleId().toLowerCase() + "%"));
			}

			predicates.add(builder.equal(root.get("pendingApproval"), false));
			criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));

			// sorting logic
			if (Objects.nonNull(filterDTO.getSortBy()) && Objects.nonNull(filterDTO.getDirection())
					&& !filterDTO.getSortBy().isEmpty() && !filterDTO.getDirection().isEmpty()) {
				criteria = ListUtils.setOrders(filterDTO.getSortBy(), filterDTO.getDirection(), criteria, root,
						builder);
			}

			// This query fetches the records as per the Page Limit
			List<Role> result = entityManager.createQuery(criteria).setFirstResult((int) pageable.getOffset())
					.setMaxResults(pageable.getPageSize()).getResultList();

			// Create Count Query
			CriteriaQuery<Long> countQuery = builder.createQuery(Long.class);
			Root<Role> countRoot = countQuery.from(Role.class);
			countQuery.select(builder.count(countRoot))
					.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));

			// Fetches the count of all records as per given criteria
			Long count = entityManager.createQuery(countQuery).getSingleResult();

			log.info("count->" + count);
			log.info("size->" + result.size());
			Page<Role> pageData = new PageImpl<>(result, pageable, count);

			jsonObject.put("currentPage", pageData.getNumber());
			jsonObject.put("totalItems", pageData.getTotalElements());
			jsonObject.put("totalPages", pageData.getTotalPages());
			jsonObject.put("items", pageData.getContent());
			log.info("" + pageData.getContent().size());
			if (pageData.getContent().size() == 0)
				return ApiResponse.success(ResponseMessage.NO_RECORDS_FOUND.getMessage(), jsonObject);
			else
				return ApiResponse.success(ResponseMessage.ROLE_APPROVED_RECORDS.getMessage(), jsonObject);
		} catch (Exception e) {
			log.error("In getApprovedRecords exception", e);
			return ApiResponse.failure(ResponseMessage.OPERATION_FAILED.getMessage());
		}
	}

	@Override
	public ApiResponse<Object> deleteById(String roleId) {
		log.info("In role service deleteById method is executing");
		try {
			if (!accessManagementService.checkUserAccess(Helper.getActiveUser(), Function.ROLE.getName(),
					EnumData.CREATE_ACCESS.getName())) {
				return ApiResponse.failure(ValidationMessage.ACCESS_NOT_AVAILABLE_FOR_DELETE_ROLE.getMessage());
			}
			FunctionData functionData = functionDataRepository.getByFunctionIdAndPkey(Function.ROLE.getName(), roleId);
			if (!functionData.getCreatedBy().equals(Helper.getActiveUser())) {
				return ApiResponse.failure(ResponseMessage.FAIL_TO_DELETE.getMessage());
			}
			int deletedRows = functionDataRepository.deleteByScreenIdAndPkey(Function.ROLE.getName(), roleId);
			auditTrailService.setAuditTrail(Helper.getActiveUser(), roleId, Function.ROLE.getName(),
					EnumData.DELETE_ACTION.getName());
			if (deletedRows != 0)
				return ApiResponse.success(ResponseMessage.ROLE_DELETED.getMessage(), functionData);
			else {
				return ApiResponse.failure(ResponseMessage.DELETE_FAILED.getMessage());
			}
		} catch (Exception e) {
			log.error("In deleteById exception", e);
			return ApiResponse.failure(ResponseMessage.OPERATION_FAILED.getMessage());
		}
	}

	@Override
	public ApiResponse<Role> get(String roleId) {

		log.info("In role service get method is executing");
		try {
			if (!accessManagementService.checkUserAccess(Helper.getActiveUser(), Function.ROLE.getName(),
					EnumData.VIEW_ACCESS.getName())) {
				return ApiResponse.failure(ValidationMessage.ACCESS_NOT_AVAILABLE_FOR_VIEW_ROLE.getMessage());
			}
			log.info(roleId);
			ObjectMapper mapper = new ObjectMapper();
			Role role = new Role();
			FunctionData functionDataAction = functionDataRepository.getByFunctionIdAndPkey(Function.ROLE.getName(),
					roleId);
			if (functionDataAction != null) {
				role = mapper.readValue(functionDataAction.getFunctionJsonData(), Role.class);
			} else {
				role = roleRepository.getByRoleIdAndApproved(roleId, true);
				if (role != null) {
					role.setRoleFunctions(roleFunctionsRepository.getByRoleId(role.getRoleId()));
				} else {
					return ApiResponse.failure(ResponseMessage.NO_RECORDS_FOUND.getMessage());
				}
			}
			auditTrailService.setAuditTrail(Helper.getActiveUser(), roleId, Function.ROLE.getName(),
					EnumData.VIEW_ACTION.getName());
			return ApiResponse.success(ResponseMessage.FETCH_SUCCESS.getMessage(), role);
		} catch (Exception e) {
			log.error("In get exception", e);
			return ApiResponse.failure(ResponseMessage.OPERATION_FAILED.getMessage());
		}
	}

	public Boolean primaryKeyExists(Role role) {
		log.info("In role service primaryKeyExists method is executing");
		// primary key check
		FunctionData functionData = functionDataRepository.getByFunctionIdAndPkeyAndAction(Function.ROLE.getName(),
				role.getRoleId(), EnumData.NEW_ACTION.getName());
		Role roleFrmdb = roleRepository.getByRoleId(role.getRoleId());
		if (roleFrmdb != null || functionData != null) {
			return true;
		} else
			return false;
	}

	@Override
	public ApiResponse<Role> create(Role role) {
		log.info("In role service create method is executing");
		try {
			// validating mandatory fields
			Map<Boolean, String> map = mandatoryFieldValidationService.validateRoleFields(role);
			if (!map.isEmpty()) {
				return ApiResponse.failure(map.get(false));
			}

			// validate Dupicate RoleFunction
			if (checkDuplicateRoleFunction(role.getRoleFunctions())) {
				return ApiResponse.failure("Role function cannot be duplicate");
			}

			if (!accessManagementService.checkUserAccess(Helper.getActiveUser(), Function.ROLE.getName(),
					EnumData.CREATE_ACCESS.getName())) {
				return ApiResponse.failure(ValidationMessage.ACCESS_NOT_AVAILABLE_FOR_CREATE_ROLE.getMessage());
			}
			// primary key check
			if (primaryKeyExists(role)) {
				return ApiResponse.failure(ResponseMessage.ROLE_ALREADY_EXISTS.getMessage());
			}

			role.setRoleId(role.getRoleId().toUpperCase());
			role.setApproved(false);
			role.setCreatedBy(Helper.getActiveUser());
			role.setCreatedDate(Helper.getCurrentDateAndTime());
			role.setRecordStatus(EnumData.INACTIVE.getName());
			int recordVersion = 1;
			role.setRecordVersion(recordVersion);
			role.setPendingApproval(true);
			role.setLastUpdatedBy(null);
			role.setLastUpdatedDate(null);
			commonService.saveScreenData(role, role.getCreatedBy(), role.getRoleId(), Function.ROLE.getName(),
					EnumData.NEW_ACTION.getName(), recordVersion, EnumData.INACTIVE.getName());
			auditTrailService.setAuditTrail(Helper.getActiveUser(), role.getRoleId(), Function.ROLE.getName(),
					EnumData.NEW_ACTION.getName());
			return ApiResponse.success(ResponseMessage.ROLE_CREATED.getMessage(), role);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			log.error("In create exception", e);
			return ApiResponse.failure(ResponseMessage.OPERATION_FAILED.getMessage());
		}
	}

	@Override
	public ApiResponse<Role> update(Role role) {
		log.info("In role service update method is executing");
		try {
			// validating mandatory fields
			Map<Boolean, String> map = mandatoryFieldValidationService.validateRoleFields(role);
			if (!map.isEmpty()) {
				return ApiResponse.failure(map.get(false));
			}

			// validate Dupicate RoleFunction
			if (checkDuplicateRoleFunction(role.getRoleFunctions())) {
				return ApiResponse.failure("Role function cannot be duplicate");
			}

			if (!accessManagementService.checkUserAccess(Helper.getActiveUser(), Function.ROLE.getName(),
					EnumData.CREATE_ACCESS.getName())) {
				return ApiResponse.failure(ValidationMessage.ACCESS_NOT_AVAILABLE_FOR_UPDATE_ROLE.getMessage());
			}

			Role rolefmDb = new Role();
			int recordVersion = 1;
			boolean reupdate = false;
			ObjectMapper mapper = new ObjectMapper();
			FunctionData functionData = functionDataRepository.getByFunctionIdAndPkey(Function.ROLE.getName(),
					role.getRoleId());
			if (functionData != null) {
				// updating unapproved record
				rolefmDb = mapper.readValue(functionData.getFunctionJsonData(), Role.class);
				recordVersion = rolefmDb.getRecordVersion();
				reupdate = true;
			} else {
				// check db for update of approved records
				rolefmDb = roleRepository.getByRoleId(role.getRoleId());
				if (rolefmDb != null) {
					recordVersion = rolefmDb.getRecordVersion() + 1;
				} else {
					return ApiResponse.failure(ResponseMessage.UPDATE_RECORD_NOT_FOUND.getMessage());
				}

			}
			rolefmDb = roleRepository.getByRoleId(role.getRoleId());
			List<RoleFunctions> roleFunctionsList = new ArrayList<>();
			if (rolefmDb.getRoleFunctions().size() > 0) {
				for (RoleFunctions roleFunctions : role.getRoleFunctions()) {
					roleFunctions.setRoleId(rolefmDb.getRoleId());
					roleFunctionsList.add(roleFunctions);
				}
				role.setRoleFunctions(roleFunctionsList);
			}

			role.setApproved(false);
			role.setRecordVersion(recordVersion);
			role.setPendingApproval(true);
			role.setLastUpdatedBy(Helper.getActiveUser());
			role.setRecordStatus(EnumData.ACTIVE.getName());
			role.setLastUpdatedDate(Helper.getCurrentDateTime());
			if (reupdate)
				commonService.updateScreenData(role, rolefmDb.getCreatedBy(), role.getRoleId(), Function.ROLE.getName(),
						EnumData.UPDATE_ACTION.getName(), recordVersion, EnumData.INACTIVE.getName());
			else
				commonService.saveScreenData(role, rolefmDb.getCreatedBy(), role.getRoleId(), Function.ROLE.getName(),
						EnumData.UPDATE_ACTION.getName(), recordVersion, EnumData.INACTIVE.getName());
			roleRepository.updatePendingApproval(role.getRoleId(), true);
			auditTrailService.setAuditTrail(Helper.getActiveUser(), role.getRoleId(), Function.ROLE.getName(),
					EnumData.UPDATE_ACTION.getName());
			return ApiResponse.success(ResponseMessage.ROLE_UPDATED.getMessage(), role);
		} catch (JsonProcessingException e) {
			log.error("In update exception", e);
			return ApiResponse.failure(ResponseMessage.OPERATION_FAILED.getMessage());
		}
	}

	@Override
	public ApiResponse<Role> disableById(String roleId) {
		log.info("In role service disableById method is executing");
		Role rolesfmDb = roleRepository.getByRoleId(roleId);
		int recordVersion = rolesfmDb.getRecordVersion();
		try {
			if (!accessManagementService.checkUserAccess(Helper.getActiveUser(), Function.ROLE.getName(),
					EnumData.CREATE_ACCESS.getName())) {
				return ApiResponse.failure(ValidationMessage.ACCESS_NOT_AVAILABLE_FOR_DISABLE_ROLE.getMessage());
			}
			Role roles = commonService.makeRoleDisabled(rolesfmDb);
			commonService.saveScreenData(roles, rolesfmDb.getCreatedBy(), roles.getRoleId(), Function.ROLE.getName(),
					EnumData.DISABLED_RECORD.getName(), recordVersion, EnumData.DISABLED.getName());
			roleRepository.updatePendingApproval(roleId, true);
			auditTrailService.setAuditTrail(Helper.getActiveUser(), roleId, Function.ROLE.getName(),
					EnumData.DISABLE_ACTION.getName());
			return ApiResponse.success(ResponseMessage.OPERATION_SUCCESS.getMessage(), roles);
		} catch (Exception e) {
			log.error("In disableById exception", e);
			return ApiResponse.failure(ResponseMessage.OPERATION_FAILED.getMessage());
		}

	}

	@Override
	public ApiResponse<Role> approve(String roleId) {
		log.info("In role service approve method is executing");
		try {
			if (!accessManagementService.checkUserAccess(Helper.getActiveUser(), Function.ROLE.getName(),
					EnumData.APPROVE_ACCESS.getName())) {
				return ApiResponse.failure(ValidationMessage.ACCESS_NOT_AVAILABLE_FOR_APPROVE_ROLE.getMessage());
			}
			ObjectMapper mapper = new ObjectMapper();
			FunctionData functionData = functionDataRepository.getByFunctionIdAndPkey(Function.ROLE.getName(), roleId);
			Role role = mapper.readValue(functionData.getFunctionJsonData(), Role.class);

			if (role.getRecordStatus() != null && role.getRecordStatus().equals(EnumData.ACTIVE.getName())) {
				role.setRecordStatus(EnumData.ACTIVE.getName());
			}

			if (!makerConfig.checkApprovers(role.getRecordVersion(), role.getCreatedBy(), role.getLastUpdatedBy())) {
				return ApiResponse.failure(ResponseMessage.MAKER_CANNOT_APPROVE.getMessage());
			}

			role.setApproved(true);
			role.setPendingApproval(false);
			role.setApprovedBy(Helper.getActiveUser());
			role.setApprovedDate(Helper.getCurrentDateAndTime());
			role.setRecordStatus(EnumData.ACTIVE.getName());
			log.info("" + role);
			roleRepository.save(role);
			FunctionDataArchive functionDataArchive = (FunctionDataArchive) convertToDto(functionData,
					new FunctionDataArchive());
			if (functionDataArchive.getStatus() != null
					&& functionDataArchive.getStatus().equals(EnumData.DISABLED_RECORD.getName())) {
				functionDataArchive.setStatus(EnumData.DISABLED.getName());
			} else {
				functionDataArchive.setStatus(EnumData.ACTIVE.getName());
			}
			functionDataArchive.setApprovedBy(Helper.getActiveUser());
			functionDataArchive.setApprovedDate(Helper.getCurrentDateAndTime());
			functionDataArchiveRepository.save(functionDataArchive);
			functionDataRepository.delete(functionData);
			auditTrailService.setAuditTrail(Helper.getActiveUser(), roleId, Function.ROLE.getName(),
					EnumData.APPROVE_ACTION.getName());
			return ApiResponse.success(ResponseMessage.ROLE_APPROVED.getMessage(), role);
		} catch (Exception e) {
			log.error("In approve exception", e);
			return ApiResponse.failure(ResponseMessage.OPERATION_FAILED.getMessage());
		}
	}

	@Override
	public ApiResponse<Role> reject(String roleId) {
		log.info("In role service reject method is executing");
		Role role = new Role();
		try {
			FunctionData functionData = functionDataRepository.getByFunctionIdAndPkey(Function.ROLE.getName(), roleId);
			role = (Role) getObjectFromFunctionData(functionData.getFunctionJsonData(), role);
			FunctionDataArchive functionDataArchive = (FunctionDataArchive) convertToDto(functionData,
					new FunctionDataArchive());
			if (!accessManagementService.checkUserAccess(Helper.getActiveUser(), Function.ROLE.getName(),
					EnumData.APPROVE_ACCESS.getName())) {
				return ApiResponse.failure(ValidationMessage.ACCESS_NOT_AVAILABLE_FOR_REJECT_ROLE.getMessage());
			}
			if (!makerConfig.checkApprovers(role.getRecordVersion(), role.getCreatedBy(), role.getLastUpdatedBy())) {
				return ApiResponse.failure(ResponseMessage.MAKER_CANNOT_REJECT.getMessage());
			}
			functionDataArchive.setStatus(EnumData.REJECT.getName());
			functionDataArchive.setApprovedBy(Helper.getActiveUser());
			functionDataArchive.setApprovedDate(Helper.getCurrentDateAndTime());
			functionDataArchiveRepository.save(functionDataArchive);
			functionDataRepository.delete(functionData);
			roleRepository.updatePendingApproval(roleId, false);
			auditTrailService.setAuditTrail(Helper.getActiveUser(), roleId, Function.ROLE.getName(),
					EnumData.REJECT_ACTION.getName());
			return ApiResponse.success(ResponseMessage.ROLE_REJECTED.getMessage(), null);
		} catch (Exception e) {
			log.error("In reject exception", e);
			return ApiResponse.failure(ResponseMessage.OPERATION_FAILED.getMessage());
		}
	}

	public Object convertToDto(Object source, Object target)// , Object target)
	{
		BeanUtils.copyProperties(source, target);
		return target;
	}

	public ApiResponse<List<LookupValues>> getAllRolesService() {
		log.info("In role service reject method is executing");
		try {
			List<Role> roleList = roleRepository.findAll();
			List<LookupValues> lookupValueList = new ArrayList<>();
			for (Role role : roleList) {
				LookupValues lookupValues = new LookupValues();
				lookupValues.setName(role.getRoleId());
				lookupValues.setValue(role.getRoleId());
				lookupValueList.add(lookupValues);
			}
			return ApiResponse.success(ResponseMessage.FETCH_SUCCESS.getMessage(), lookupValueList);
		} catch (Exception e) {
			log.error("In getAllRolesService exception", e);
			return ApiResponse.failure(ResponseMessage.OPERATION_FAILED.getMessage());
		}
	}

	public Boolean checkDuplicateRoleFunction(List<RoleFunctions> roleFunctions) {
		Set<String> roleFunctionsSet = new HashSet<String>();
		for (RoleFunctions functions : roleFunctions) {
			if (roleFunctionsSet.add(functions.getFunctionId()) == false) {
				return true;
			}
		}
		return false;
	}

	public ApiResponse<List<RoleFunctions>> getAllRoleFunctions(String roleType) {
		log.info("In getAllRoleFunctions..");
		// brij logic
		try {
			List<AppFunctions> appFunctionsList = appFunctionsRepository.getByRoleType(roleType).stream()
					.sorted(Comparator.comparing(AppFunctions::getId)).collect(Collectors.toList());
			List<RoleFunctions> roleFunctionsList = new ArrayList<>();
			for (AppFunctions appFunctions : appFunctionsList) {
				RoleFunctions roleFunctions = new RoleFunctions();
				roleFunctions.setId(null);
				roleFunctions.setRoleId(null);
				roleFunctions.setFunctionId(appFunctions.getFunctionId());
				roleFunctions.setFunctionName(appFunctions.getFunctionName());
				roleFunctions.setViewAccess(false);
				roleFunctions.setCreateAccess(false);
				roleFunctions.setApproveAccess(false);
				roleFunctions.setParentFunctionId(appFunctions.getParentFunctionId());
				if (appFunctions.getFunctionId().equals(appFunctions.getParentFunctionId()))
					roleFunctions.setParentFunction(true);
				else
					roleFunctions.setParentFunction(false);

				roleFunctionsList.add(roleFunctions);
			}
			log.info("Return success from getAllRoleFunctions");
			return ApiResponse.success(ResponseMessage.OPERATION_SUCCESS.getMessage(), roleFunctionsList);
		} catch (Exception e) {
			log.error("In getAllRoleFunctions exception", e);
			return ApiResponse.failure(ResponseMessage.OPERATION_FAILED.getMessage());
		}
		// brij logic ends

//	        Set<String> functionIds = new HashSet<String>();
//	        Set<RoleFunctionMenuDTO> roleFunctionDTO = new LinkedHashSet<RoleFunctionMenuDTO>();
//	        try {
//	            functionIds.addAll(appFunctionsRepository.findAll().stream().map(res -> res.getFunctionId()).collect(Collectors.toSet()));
//	            List<AppFunctions> appFunctionsLists = appFunctionsRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
//	            log.info(" sorted data " + appFunctionsLists);
//	            List<AppFunctions> parentFunctionList = appFunctionsLists.stream().distinct().
//	                    filter(res -> res.getParentFunctionId().equals(res.getFunctionId())).collect(Collectors.toList());
//	            for (AppFunctions appFunctions : parentFunctionList) {
//	                log.info("parent->" + appFunctions.getParentFunctionId());
//	                RoleFunctionMenuDTO parentFunctionMenu = new RoleFunctionMenuDTO();
//	                log.info("parentScreen->" + appFunctions);
		//
//	                List<AppFunctions> childFunctionList = appFunctionsLists.stream().
//	                        filter(res -> res.getParentFunctionId().equals(appFunctions.getParentFunctionId())).collect(Collectors.toList());
//	                log.info("cIds->" + childFunctionList);
//	                Boolean functionAccess = false;
//	                List<ChildRoleFunctionsDTO> childFunctionMenuList = new ArrayList<>();
//	                for (AppFunctions childFunction : childFunctionList) {
//	                    log.info("child->" + childFunction);
//	                    if (childFunction.getFunctionId().equals(childFunction.getParentFunctionId())) {
//	                        parentFunctionMenu.setParentFunction(true);
//	                        parentFunctionMenu.setFunctionDesc(childFunction.getFunctionName());
//	                        parentFunctionMenu.setFunctionId(childFunction.getFunctionId());
//	                        parentFunctionMenu.setApproveAccess(false);
//	                        parentFunctionMenu.setCreateAccess(false);
//	                        parentFunctionMenu.setViewAccess(false);
//	                    }
//	                    if (functionIds.contains(childFunction.getFunctionId())) {
//	                        functionAccess = true;
//	                        if (!childFunction.getFunctionId().equals(childFunction.getParentFunctionId())) {
//	                            ChildRoleFunctionsDTO childFunctionMenu = new ChildRoleFunctionsDTO();
//	                            childFunctionMenu.setFunctionDesc(childFunction.getFunctionName());
//	                            childFunctionMenu.setFunctionId(childFunction.getFunctionId());
//	                            childFunctionMenu.setParentFunction(false);
//	                            childFunctionMenu.setApproveAccess(false);
//	                            childFunctionMenu.setCreateAccess(false);
//	                            childFunctionMenu.setViewAccess(false);
//	                            childFunctionMenuList.add(childFunctionMenu);
//	                        }
//	                    } else {
//	                        functionAccess = false;
//	                    }
		//
//	                    if (functionAccess) {
//	                        if (!childFunctionMenuList.isEmpty()) {
//	                            parentFunctionMenu.setChildRoleFunctionsList(childFunctionMenuList);
//	                        }
//	                        roleFunctionDTO.add(parentFunctionMenu);
//	                    }
//	                }
//	            }
//	            return ApiResponse.success(ResponseMessage.FETCH_SUCCESS.getMessage(), roleFunctionDTO);
//	        } catch (Exception e) {
//	            log.error("Your message ",e);
//	            return ApiResponse.failure(ResponseMessage.OPERATION_FAILED.getMessage());
//	        }
	}

	private Role convertDtotoRoleObject(CreateRoleDTO createRoleDTO) {
		List<RoleFunctions> roleFunctionsList = new ArrayList<>();
		Role role = new Role();
		role.setRoleId(createRoleDTO.getRoleId());
		role.setRoleDescription(createRoleDTO.getRoleDescription());
		role.setRoleType(createRoleDTO.getRoleType());
		role.setAppId(createRoleDTO.getAppId());
		for (RoleFunctionMenuDTO roleFunctionMenuDTO : createRoleDTO.getRoleFunctions()) {
			RoleFunctions roleFunctions = new RoleFunctions();
			roleFunctions.setRoleId(role.getRoleId());
			roleFunctions.setFunctionId(roleFunctionMenuDTO.getFunctionId());
			roleFunctions.setApproveAccess(roleFunctionMenuDTO.getApproveAccess());
			roleFunctions.setCreateAccess(roleFunctionMenuDTO.getCreateAccess());
			roleFunctions.setViewAccess(roleFunctionMenuDTO.getViewAccess());
			roleFunctionsList.add(roleFunctions);

			if (roleFunctionMenuDTO.getChildRoleFunctionsList() != null) {
				roleFunctionMenuDTO.getChildRoleFunctionsList().stream().forEach(res -> {
					RoleFunctions roleFunction = new RoleFunctions();
					roleFunction.setRoleId(role.getRoleId());
					roleFunction.setFunctionId(res.getFunctionId());
					roleFunction.setApproveAccess(res.getApproveAccess());
					roleFunction.setCreateAccess(res.getCreateAccess());
					roleFunction.setViewAccess(res.getViewAccess());
					roleFunctionsList.add(roleFunction);
				});
			}
		}
		role.setRoleFunctions(roleFunctionsList);
		return role;
	}

}
