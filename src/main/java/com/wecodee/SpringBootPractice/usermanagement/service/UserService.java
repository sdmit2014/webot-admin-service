package com.wecodee.SpringBootPractice.usermanagement.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
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
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wecodee.SpringBootPractice.admin.config.MakerConfig;
import com.wecodee.SpringBootPractice.admin.constant.EnumData;
import com.wecodee.SpringBootPractice.admin.constant.Function;
import com.wecodee.SpringBootPractice.admin.constant.ResponseMessage;
import com.wecodee.SpringBootPractice.admin.constant.ValidationMessage;
import com.wecodee.SpringBootPractice.admin.model.FunctionData;
import com.wecodee.SpringBootPractice.admin.model.FunctionDataArchive;
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
import com.wecodee.SpringBootPractice.common.service.PasswordValidationService;
import com.wecodee.SpringBootPractice.common.utils.ListUtils;
import com.wecodee.SpringBootPractice.login.dto.LoginUserDTO;
import com.wecodee.SpringBootPractice.login.service.LoginService;
import com.wecodee.SpringBootPractice.usermanagement.model.Role;
import com.wecodee.SpringBootPractice.usermanagement.model.User;
import com.wecodee.SpringBootPractice.usermanagement.model.UserBranchAccess;
import com.wecodee.SpringBootPractice.usermanagement.model.UserRole;
import com.wecodee.SpringBootPractice.usermanagement.repository.RoleRepository;
import com.wecodee.SpringBootPractice.usermanagement.repository.UserBranchAccessRepository;
import com.wecodee.SpringBootPractice.usermanagement.repository.UserRolesRepository;
import com.wecodee.SpringBootPractice.usermanagement.repository.UsersRepository;

import net.minidev.json.JSONObject;
import org.springframework.data.domain.*;

import java.util.Comparator;

@Service
public class UserService extends BaseService<User> {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	private UsersRepository usersRepository;

	@Autowired
	private EntityManager entityManager;

	@Autowired
	private UserRolesRepository userRoleRepository;

	@Autowired
	private BCryptPasswordEncoder encoder;

	@Autowired
	private FunctionDataRepository functionDataRepository;

	@Autowired
	private FunctionDataArchiveRepository functionDataArchiveRepository;

	@Autowired
	private CommonService commonService;

	@Autowired
	private AuditTrailService auditTrailService;

	@Autowired
	private AccessManagementService accessManagementService;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordValidationService passwordValidationService;

	@Autowired
	private LoginService loginService;

	@Autowired
	private UserBranchAccessRepository userBranchAccessRepository;

	@Autowired
	private MakerConfig makerConfig;

	@Autowired
	private MandatoryFieldValidationService mandatoryFieldValidationService;

	@Autowired
	public UserService(UsersRepository usersRepository) {
		super(usersRepository);
		this.usersRepository = usersRepository;
	}

	@Override
	public String getFunctionName() {
		return Function.USER.getName();
	}

	@Override
	public ApiResponse<Object> deleteById(String userId) {
		log.info("In user service deleteById method is executing");
		try {
			if (!accessManagementService.checkUserAccess(Helper.getActiveUser(), Function.USER.getName(),
					EnumData.CREATE_ACCESS.getName())) {
				return ApiResponse.failure(ValidationMessage.ACCESS_NOT_AVAILABLE_FOR_DELETE_USER.getMessage());
			}
			FunctionData functionData = functionDataRepository.getByFunctionIdAndPkey(Function.USER.getName(), userId);
			if (!functionData.getCreatedBy().equals(Helper.getActiveUser())) {
				return ApiResponse.failure(ResponseMessage.FAIL_TO_DELETE.getMessage());
			}
			int deletedRows = functionDataRepository.deleteByScreenIdAndPkey(Function.USER.getName(), userId);
			auditTrailService.setAuditTrail(Helper.getActiveUser(), userId, Function.USER.getName(),
					EnumData.DELETE_ACTION.getName());
			if (deletedRows != 0)
				return ApiResponse.success(ResponseMessage.USER_DELETED.getMessage(), functionData);
			else
				return ApiResponse.failure(ResponseMessage.DELETE_FAILED.getMessage());
		} catch (Exception e) {
			log.error("In deleteById exception", e);
			return ApiResponse.failure(ResponseMessage.OPERATION_FAILED.getMessage());
		}
	}

	public ApiResponse<User> setImageService(User users) {
		log.info("In user service setImageService method is executing");
		try {
			log.info("" + users);
			User userfrmDb = usersRepository.getByUserId(Helper.getActiveUser());
			userfrmDb.setImageData(users.getImageData());
			userfrmDb.setRecordVersion(userfrmDb.getRecordVersion());
			User userResp = usersRepository.save(userfrmDb);
			if (userResp != null) {
				return ApiResponse.success(ResponseMessage.OPERATION_SUCCESS.getMessage(), userResp);
			} else {
				return ApiResponse.failure(ResponseMessage.OPERATION_FAILED.getMessage());
			}
		} catch (Exception e) {
			log.error("In setImageService exception", e);
			return ApiResponse.failure(ResponseMessage.OPERATION_FAILED.getMessage());
		}
	}

	public ApiResponse<LoginUserDTO> getLoggedUser(String userId) {
		log.info("In user service getLoggedUser method is executing");
		LoginUserDTO loginUserDTO = new LoginUserDTO();
		List<String> stringList = new ArrayList<>();
		try {
			User user = usersRepository.getByUserIdAndApproved(userId, true);
			for (UserRole userRole : user.getUserRoles()) {
				Role roleType = roleRepository.getByRoleId(userRole.getRoleId());
				stringList.add(roleType != null ? roleType.getRoleType() : "");
			}
			loginUserDTO.setRoleTypes(stringList);
			if (user != null) {
				loginUserDTO.setPassword(null);
				BeanUtils.copyProperties(user, loginUserDTO);
				for (UserRole userRole : user.getUserRoles()) {
					Role role = roleRepository.getByRoleId(userRole.getRoleId());

					if (role.getRoleType().equals(EnumData.ROLE_TYPE_ADMINISTRTOR.getName())) {
						loginUserDTO.setAdministrator(true);
					}
					if (role.getRoleType().equals(EnumData.ROLE_TYPE_REQUESTOR.getName())) {
						loginUserDTO.setRequestor(true);
					}
					if (role.getRoleType().equals(EnumData.ROLE_TYPE_APPROVER.getName())) {
						loginUserDTO.setApprover(true);
					}
				}
			} else {
				return ApiResponse.failure(ResponseMessage.OPERATION_FAILED.getMessage());
			}
			return ApiResponse.success(ResponseMessage.OPERATION_SUCCESS.getMessage(), loginUserDTO);
		} catch (Exception e) {
			log.error("In getLoggedUser exception", e);
			return ApiResponse.failure(ResponseMessage.OPERATION_FAILED.getMessage());
		}
	}

	public Boolean primaryKeyExists(User user) {
		log.info("In user service primaryKeyExists method is executing");
		// primary key check
		FunctionData functionData = functionDataRepository.getByFunctionIdAndPkeyAndAction(Function.USER.getName(),
				user.getUserId(), EnumData.NEW_ACTION.getName());
		User userFrmdb = usersRepository.getByUserId(user.getUserId());
		if (userFrmdb != null || functionData != null) {
			return true;
		} else
			return false;
	}

	@Override
	public ApiResponse<User> create(User user) {
		log.info("In user service create method is executing");
		log.info("" + user);
		try {
			// validating mandatory fields
			Map<Boolean, String> map = mandatoryFieldValidationService.validateUserFields(user);
			if (!map.isEmpty()) {
				return ApiResponse.failure(map.get(false));
			}

			if (!accessManagementService.checkUserAccess(Helper.getActiveUser(), Function.USER.getName(),
					EnumData.CREATE_ACCESS.getName())) {
				return ApiResponse.failure(ValidationMessage.ACCESS_NOT_AVAILABLE_FOR_CREATE_USER.getMessage());
			}
			// primary key check
			if (primaryKeyExists(user)) {
				return ApiResponse.failure(ResponseMessage.USER_ALREADY_EXISTS.getMessage());
			}
			// validating the password
			ApiResponse<String> response = passwordValidationService.passwordValidate(user.getPassword());
			if (response.getStatus() == 0) {
				return ApiResponse.failure(response.getMessage());
			}

			int id = Integer.parseInt(generateRandomNumber());
			user.setId(id);
			user.setUserId(user.getUserId().toUpperCase());
			user.setPendingApproval(true);
			user.setApproved(false);
			user.setChgPassFlg(true);
			String encryptedPass = encoder.encode(user.getPassword());
			user.setPassword(encryptedPass);
			int recordVersion = 1;
			user.setNoOfAttempts(0);
			user.setValidateDate(Helper.getCurrentDateAndTime());
			user.setLoginStatus(false);
			user.setRecordVersion(recordVersion);
			user.setCreatedBy(Helper.getActiveUser());
			user.setCreatedDate(Helper.getCurrentDateAndTime());
			user.setRecordStatus(EnumData.INACTIVE.getName());
			user.setLastUpdatedBy(null);
			user.setLastUpdatedDate(null);
//            temp fix starts
			List<UserRole> newUserRoleList = new ArrayList<>();
			for (UserRole userRole : user.getUserRoles()) {
				userRole.setUserId(user.getUserId());
				newUserRoleList.add(userRole);
			}
			user.setUserRoles(newUserRoleList);
//            temp fix ends

			commonService.saveScreenData(user, user.getCreatedBy(), user.getUserId(), Function.USER.getName(),
					EnumData.NEW_ACTION.getName(), recordVersion, EnumData.INACTIVE.getName());
			auditTrailService.setAuditTrail(Helper.getActiveUser(), user.getUserId(), Function.USER.getName(),
					EnumData.NEW_ACTION.getName());
			return ApiResponse.success(ResponseMessage.USER_CREATED.getMessage(), user);
		} catch (JsonProcessingException e) {
			log.error("In create exception", e);
			return ApiResponse.failure(ResponseMessage.OPERATION_FAILED.getMessage());
		}
	}

	// new
	@Override
	public ApiResponse<User> update(User user) {
		log.info("In user service update method is executing");
		try {
			// validating mandatory fields
			Map<Boolean, String> map = mandatoryFieldValidationService.validateUserFields(user);
			if (!map.isEmpty()) {
				return ApiResponse.failure(map.get(false));
			}
			if (!accessManagementService.checkUserAccess(Helper.getActiveUser(), Function.USER.getName(),
					EnumData.CREATE_ACCESS.getName())) {
				return ApiResponse.failure(ValidationMessage.ACCESS_NOT_AVAILABLE_FOR_UPDATE_USER.getMessage());
			}
			User userFrmDb = new User();
			int recordVersion = 1;
			boolean reupdate = false;
			ObjectMapper mapper = new ObjectMapper();
			FunctionData functionData = functionDataRepository.getByFunctionIdAndPkey(Function.USER.getName(),
					user.getUserId());
			if (functionData != null) {
				// updating unapproved record
				userFrmDb = mapper.readValue(functionData.getFunctionJsonData(), User.class);
				recordVersion = userFrmDb.getRecordVersion();
				reupdate = true;
			} else {
				// check db for update of approved records
				userFrmDb = usersRepository.getByUserId(user.getUserId());
				if (userFrmDb != null) {
					recordVersion = userFrmDb.getRecordVersion() + 1;
				} else {
					return ApiResponse.failure(ResponseMessage.UPDATE_RECORD_NOT_FOUND.getMessage());
				}
			}
			List<UserRole> userRoleList = new ArrayList<>();
			List<UserBranchAccess> userBranchAccessList = new ArrayList<>();
			if (userFrmDb.getUserRoles().size() > 0) {
				for (UserRole userRole : user.getUserRoles()) {
					userRole.setUserId(userFrmDb.getUserId());
					userRoleList.add(userRole);
				}
				user.setUserRoles(userRoleList);
			}
			if (userFrmDb.getUserBranchAccess().size() > 0) {
				for (UserBranchAccess userBranchAccess : user.getUserBranchAccess()) {
					userBranchAccess.setUserId(userFrmDb.getUserId());
					userBranchAccessList.add(userBranchAccess);
				}
				user.setUserBranchAccess(userBranchAccessList);
			}
			user.setPassword(userFrmDb.getPassword());
			user.setRecordStatus(userFrmDb.getRecordStatus());
			user.setApproved(false);
			user.setChgPassFlg(false);
			user.setPendingApproval(true);
			user.setRecordVersion(recordVersion);
			user.setValidateDate(userFrmDb.getValidateDate());
			user.setLoginStatus(userFrmDb.isLoginStatus());
			user.setLastUpdatedBy(Helper.getActiveUser());
			user.setLastUpdatedDate(Helper.getCurrentDateAndTime());
			if (reupdate)
				commonService.updateScreenData(user, Helper.getActiveUser(), user.getUserId(), Function.USER.getName(),
						EnumData.UPDATE_ACTION.getName(), recordVersion, EnumData.INACTIVE.getName());
			else
				commonService.saveScreenData(user, Helper.getActiveUser(), user.getUserId(), Function.USER.getName(),
						EnumData.UPDATE_ACTION.getName(), recordVersion, EnumData.INACTIVE.getName());
			usersRepository.updatePendingApproval(user.getUserId(), true);
			auditTrailService.setAuditTrail(Helper.getActiveUser(), user.getUserId(), Function.USER.getName(),
					EnumData.UPDATE_ACTION.getName());
			return ApiResponse.success(ResponseMessage.USER_UPDATED.getMessage(), user);

		} catch (Exception e) {
			log.error("In update exception ", e);
			return ApiResponse.failure(ResponseMessage.OPERATION_FAILED.getMessage());
		}
	}

	public ApiResponse<User> enableUser(String userId) {
		log.info("In user service enableUser method is executing");
		try {
			if (!accessManagementService.checkUserAccess(Helper.getActiveUser(), Function.USER.getName(),
					EnumData.CREATE_ACCESS.getName())) {
				return ApiResponse.failure(ValidationMessage.ACCESS_NOT_AVAIALABLE_FOR_ENABLE_USER.getMessage());
			}
			User userDb = usersRepository.getByUserId(userId);
			int recordVersion = userDb.getRecordVersion() + 1;
			userDb.setApproved(false);
			userDb.setPendingApproval(true);
			userDb.setRecordStatus(EnumData.ACTIVE.getName());
			userDb.setLastUpdatedBy(Helper.getActiveUser());
			userDb.setLastUpdatedDate(Helper.getCurrentDateAndTime());
			userDb.setRecordVersion(recordVersion);
			commonService.saveScreenData(userDb, userDb.getLastUpdatedBy(), userDb.getUserId(), Function.USER.getName(),
					EnumData.ENABLE_ACTION.getName(), recordVersion, EnumData.ACTIVE.getName());
			usersRepository.updatePendingApproval(userDb.getUserId(), true);
			auditTrailService.setAuditTrail(Helper.getActiveUser(), userId, Function.USER.getName(),
					EnumData.ENABLE_ACTION.getName());
			return ApiResponse.success(ResponseMessage.USER_ENABLED_SUCCESS.getMessage(), userDb);
		} catch (Exception e) {
			log.error("In enableUser exception ", e);
			return ApiResponse.failure(ResponseMessage.OPERATION_FAILED.getMessage());
		}
	}

	@Override
	public ApiResponse<User> approve(String userId) {
		log.info("In user service approve method is executing");
		ObjectMapper mapper = new ObjectMapper();
		try {
			if (!accessManagementService.checkUserAccess(Helper.getActiveUser(), Function.USER.getName(),
					EnumData.APPROVE_ACCESS.getName())) {
				return ApiResponse.failure(ValidationMessage.ACCESS_NOT_AVAILABLE_FOR_APPROVE_USER.getMessage());
			}
			FunctionData functionData = functionDataRepository.getByFunctionIdAndPkey(Function.USER.getName(), userId);
			User user = mapper.readValue(functionData.getFunctionJsonData(), User.class);
			if (!makerConfig.checkApprovers(user.getRecordVersion(), user.getCreatedBy(), user.getLastUpdatedBy())) {
				return ApiResponse.failure(ResponseMessage.MAKER_CANNOT_APPROVE.getMessage());
			}
			if (user.getRecordStatus() != null && user.getRecordStatus().equals(EnumData.ACTIVE.getName())) {
				user.setChgPassFlg(false);
			}
			user.setApproved(true);
			user.setPendingApproval(false);
			user.setApprovedBy(Helper.getActiveUser());
			user.setApprovedDate(Helper.getCurrentDateAndTime());
			if (user.getRecordStatus().equals(EnumData.DISABLED.getName())) {
				user.setRecordStatus(EnumData.DISABLED.getName());
			} else {
				user.setRecordStatus(EnumData.ACTIVE.getName());
			}
			log.info("User object " + user);
			usersRepository.save(user);
			FunctionDataArchive functionDataArchive = (FunctionDataArchive) convertToDto(functionData,
					new FunctionDataArchive());
			if (functionDataArchive.getStatus() != null
					&& functionDataArchive.getStatus().equals(EnumData.DISABLED.getName())) {
				functionDataArchive.setStatus(EnumData.DISABLED.getName());
			} else {
				functionDataArchive.setStatus(EnumData.ACTIVE.getName());
			}
			functionDataArchive.setApprovedBy(Helper.getActiveUser());
			functionDataArchive.setApprovedDate(Helper.getCurrentDateAndTime());

			functionDataArchiveRepository.save(functionDataArchive);
			functionDataRepository.delete(functionData);
			auditTrailService.setAuditTrail(Helper.getActiveUser(), userId, Function.USER.getName(),
					EnumData.APPROVE_ACTION.getName());
			return ApiResponse.success(ResponseMessage.USER_APPROVED.getMessage(), user);
		} catch (Exception e) {
			log.error("In approve exception", e);
			return ApiResponse.failure(ResponseMessage.OPERATION_FAILED.getMessage());
		}
	}

	@Override
	public ApiResponse<User> reject(String userId) {
		log.info("In user service reject method is executing");
		User user = new User();
		try {

			FunctionData functionData = functionDataRepository.getByFunctionIdAndPkey(Function.USER.getName(), userId);
			user = (User) getObjectFromFunctionData(functionData.getFunctionJsonData(), user);
			FunctionDataArchive functionDataArchive = (FunctionDataArchive) convertToDto(functionData,
					new FunctionDataArchive());
			if (!accessManagementService.checkUserAccess(Helper.getActiveUser(), Function.USER.getName(),
					EnumData.APPROVE_ACCESS.getName())) {
				return ApiResponse.failure(ValidationMessage.ACCESS_NOT_AVAILABLE_FOR_REJECT_USER.getMessage());
			}
			if (!makerConfig.checkApprovers(user.getRecordVersion(), user.getCreatedBy(), user.getLastUpdatedBy())) {
				return ApiResponse.failure(ResponseMessage.MAKER_CANNOT_REJECT.getMessage());
			}
			functionDataArchive.setStatus(EnumData.REJECT.getName());
			functionDataArchive.setApprovedBy(Helper.getActiveUser());
			functionDataArchive.setApprovedDate(Helper.getCurrentDateAndTime());
			functionDataArchiveRepository.save(functionDataArchive);
			functionDataRepository.delete(functionData);
			usersRepository.updatePendingApproval(userId, false);
			auditTrailService.setAuditTrail(Helper.getActiveUser(), userId, Function.USER.getName(),
					EnumData.REJECT_ACTION.getName());
			return ApiResponse.success(ResponseMessage.USER_REJECTED.getMessage(), null);
		} catch (Exception e) {
			log.error("In reject exception", e);
			return ApiResponse.failure(ResponseMessage.OPERATION_FAILED.getMessage());
		}

	}

	public Object convertToDto(Object source, Object target)// , Object target)
	{
		log.info("In user service convertToDto method is executing");
		BeanUtils.copyProperties(source, target);
		return target;
	}

	@Override
	public ApiResponse<JSONObject> getUnapprovedRecords(SearchFilterDTO<User> filterDTO) {
		log.info("In user service getUnapprovedRecords method is executing");
		try {
			ObjectMapper mapper = new ObjectMapper();
			User userFilter = filterDTO.getFilterFieldValues();
			List<User> unapprovedList = new ArrayList<User>();
			List<FunctionData> functionDataList = functionDataRepository.findByFunctionId(Function.USER.getName());
			for (FunctionData functionData : functionDataList) {
				User data = mapper.readValue(functionData.getFunctionJsonData(), User.class);
				unapprovedList.add(data);
			}
			List<User> filteredUnapprovedList = unapprovedList;
			if (userFilter.getUserId() != null && !userFilter.getUserId().isEmpty()) {
				filteredUnapprovedList = filteredUnapprovedList.stream()
						.filter(c -> c.getUserId().toLowerCase().contains(userFilter.getUserId().toLowerCase()))
						.collect(Collectors.toList());
			}
			if (userFilter.getFirstName() != null && !userFilter.getFirstName().isEmpty()) {
				filteredUnapprovedList = filteredUnapprovedList.stream()
						.filter(c -> c.getFirstName().toLowerCase().contains(userFilter.getFirstName().toLowerCase()))
						.collect(Collectors.toList());
			}
			if (userFilter.getLastName() != null && !userFilter.getLastName().isEmpty()) {
				filteredUnapprovedList = filteredUnapprovedList.stream()
						.filter(c -> c.getLastName().toLowerCase().contains(userFilter.getLastName().toLowerCase()))
						.collect(Collectors.toList());
			}
			if (userFilter.getEmailId() != null && !userFilter.getEmailId().isEmpty()) {
				filteredUnapprovedList = filteredUnapprovedList.stream()
						.filter(c -> c.getEmailId().toLowerCase().contains(userFilter.getEmailId().toLowerCase()))
						.collect(Collectors.toList());
			}
			if (userFilter.getPhoneNumber() != null && !userFilter.getPhoneNumber().isEmpty()) {
				filteredUnapprovedList = filteredUnapprovedList.stream().filter(
						c -> c.getPhoneNumber().toLowerCase().contains(userFilter.getPhoneNumber().toLowerCase()))
						.collect(Collectors.toList());
			}

			return ApiResponse.success(ResponseMessage.USER_UNAPPROVED_RECORDS.getMessage(),
					getPaginatedList(filteredUnapprovedList, filterDTO.getPageNumber(), filterDTO.getPageSize(),
							filterDTO.getSortBy(), filterDTO.getDirection()));
		} catch (Exception e) {
			log.error("In getUnapprovedRecords exception" + e);
			return ApiResponse.failure(ResponseMessage.OPERATION_FAILED.getMessage());
		}
	}

	private JSONObject getPaginatedList(List<User> sourceList, int pageNumber, int pageSize, String sortBy,
			String direction) {
		log.info("In UserService, getPaginatedList is executing");
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
	public ApiResponse<JSONObject> getApprovedRecords(SearchFilterDTO<User> filterDTO) {
		log.info("In user service getApprovedRecords method is executing");
		try {
			log.info("approve filter");
			JSONObject jsonObject = new JSONObject();
			User user = filterDTO.getFilterFieldValues();

			CriteriaBuilder builder = entityManager.getCriteriaBuilder();
			CriteriaQuery<User> criteria = builder.createQuery(User.class);
			Root<User> usr = criteria.from(User.class);
			List<Predicate> predicates = new ArrayList<Predicate>();

			log.info("Message " + filterDTO.getPageNumber());
			log.info("Size " + filterDTO.getPageSize());

			Pageable pageable = PageRequest.of(filterDTO.getPageNumber(), filterDTO.getPageSize(),
					Sort.by("userId").ascending());

			if (user.getUserId() != null && !user.getUserId().isBlank()) {
				predicates.add(
						builder.like(builder.lower(usr.get("userId")), "%" + user.getUserId().toLowerCase() + "%"));
			}
			if (user.getFirstName() != null && !user.getFirstName().isEmpty()) {
				predicates.add(builder.like(builder.lower(usr.get("firstName")),
						"%" + user.getFirstName().toLowerCase() + "%"));
			}
			if (user.getLastName() != null && !user.getLastName().isEmpty()) {
				predicates.add(
						builder.like(builder.lower(usr.get("lastName")), "%" + user.getLastName().toLowerCase() + "%"));
			}
			if (user.getEmailId() != null && !user.getEmailId().isEmpty()) {
				predicates.add(
						builder.like(builder.lower(usr.get("emailId")), "%" + user.getEmailId().toLowerCase() + "%"));
			}
			if (user.getPhoneNumber() != null && !user.getPhoneNumber().isEmpty()) {
				predicates.add(builder.like(builder.lower(usr.get("phoneNumber")),
						"%" + user.getPhoneNumber().toLowerCase() + "%"));
			}
			predicates.add(builder.equal(usr.get("pendingApproval"), false));
			criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));

			// sorting logic
			if (Objects.nonNull(filterDTO.getSortBy()) && Objects.nonNull(filterDTO.getDirection())
					&& !filterDTO.getSortBy().isEmpty() && !filterDTO.getDirection().isEmpty()) {
				criteria = ListUtils.setOrders(filterDTO.getSortBy(), filterDTO.getDirection(), criteria, usr, builder);
			}

			// This query fetches the records as per the Page Limit
			List<User> result = entityManager.createQuery(criteria).setFirstResult((int) pageable.getOffset())
					.setMaxResults(pageable.getPageSize()).getResultList();

			// Create Count Query
			CriteriaQuery<Long> countQuery = builder.createQuery(Long.class);
			Root<User> usrCount = countQuery.from(User.class);
			countQuery.select(builder.count(usrCount))
					.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));

			// Fetches the count of all records as per given criteria
			Long count = entityManager.createQuery(countQuery).getSingleResult();

			log.info("count->" + count);
			log.info("size->" + result.size());
			Page<User> pageData = new PageImpl<>(result, pageable, count);

			jsonObject.put("currentPage", pageData.getNumber());
			jsonObject.put("totalItems", pageData.getTotalElements());
			jsonObject.put("totalPages", pageData.getTotalPages());
			jsonObject.put("items", pageData.getContent());
			log.info("" + pageData.getContent().size());
			if (pageData.getContent().size() == 0)
				return ApiResponse.success(ResponseMessage.USER_APPROVED_RECORDS.getMessage(), jsonObject);
			else
				return ApiResponse.success(ResponseMessage.USER_APPROVED_RECORDS.getMessage(), jsonObject);
		} catch (Exception e) {
			log.error("In getApprovedRecords exception", e);
			return ApiResponse.failure(ResponseMessage.OPERATION_FAILED.getMessage());
		}
	}

	@Override
	public ApiResponse<User> get(String userId) {
		log.info("In user service get method is executing");
		try {
			if (!accessManagementService.checkUserAccess(Helper.getActiveUser(), Function.USER.getName(),
					EnumData.VIEW_ACCESS.getName())) {
				return ApiResponse.failure(ValidationMessage.ACCESS_NOT_AVAILABLE_FOR_VIEW_USER.getMessage());
			}
			log.info(userId);
			ObjectMapper mapper = new ObjectMapper();
			User user = new User();
			FunctionData functionDataAction = functionDataRepository.getByFunctionIdAndPkey(Function.USER.getName(),
					userId);
			if (functionDataAction != null) {
				user = mapper.readValue(functionDataAction.getFunctionJsonData(), User.class);
			} else {
				user = usersRepository.getByUserIdAndApproved(userId, true);
			}
			auditTrailService.setAuditTrail(Helper.getActiveUser(), userId, Function.USER.getName(),
					EnumData.VIEW_ACTION.getName());
			return ApiResponse.success(ResponseMessage.FETCH_SUCCESS.getMessage(), user);
		} catch (Exception e) {
			log.error("In get exception", e);
			return ApiResponse.failure(ResponseMessage.FETCH_FAILED.getMessage());
		}
	}

	public ApiResponse<String> unlockUserService(String userId) {
		log.info("In user service unlockUserService method is executing");
		JSONObject jsonObject = new JSONObject();

		try {
			User user = usersRepository.getByUserId(userId);
			user.setRecordStatus(EnumData.ACTIVE.getName());
			user.setNoOfAttempts(Integer.parseInt(EnumData.NO_OF_ATTEMPTS.getName()));
			user.setChgPassFlg(true);
//			CharSequence uuid = (String) UUID.randomUUID().toString().subSequence(0, 8);
//			String randomPass = (String) uuid;
			String encryptedPass = encoder.encode("password");
			int result = usersRepository.resetPassword(encryptedPass, userId);
			usersRepository.save(user);

//			if (result > 0) {
//				EmailTemplate emailTemplate = emailTemplateRepository
//						.getEmailTemplate(EnumData.RESETPASSWORD.getName());
//				log.info("emailTemplate->" + emailTemplate);
//				String msg = loginService.resetpassMailContent(user, randomPass, emailTemplate);
//				jsonObject = emailService.sendMessage(null, user.getEmailId(), emailTemplate.getDefaultCc(),
//						emailTemplate.getDefaultBcc(), emailTemplate.getEmailSubject(), msg,
//						emailTemplate.getThemeId());
//				boolean status = (boolean) jsonObject.get("status");
//				if (!status) {
//					return ApiResponse.failure(ResponseMessage.USER_UNLOCKED_FAILED.getMessage());
//				}
//				log.info("After sending message");
//			}

			return ApiResponse.success(ResponseMessage.USER_UNLOCKED_SUCCESS.getMessage(), null);
		} catch (Exception e) {
			log.error("In unlockUserService exception ", e);
			return ApiResponse.failure(ResponseMessage.USER_UNLOCKED_FAILED.getMessage());
		}
	}

	public ApiResponse<User> getActiveUserService() {
		try {
			User user = usersRepository.getByUserId(Helper.getActiveUser());
			return ApiResponse.success(ResponseMessage.FETCH_SUCCESS.getMessage(), user);
		} catch (Exception e) {
			return ApiResponse.failure(ResponseMessage.FETCH_FAILED.getMessage());
		}
	}

	@Override
	public ApiResponse<User> disableById(String userId) {
		log.info("In user service disableUser method is executing");
		try {
			if (!accessManagementService.checkUserAccess(Helper.getActiveUser(), Function.USER.getName(),
					EnumData.CREATE_ACCESS.getName())) {
				return ApiResponse.failure(ValidationMessage.ACCESS_NOT_AVAIALABLE_FOR_DISABLE_USER.getMessage());
			}
			User userDb = usersRepository.getByUserId(userId);
			int recordVersion = userDb.getRecordVersion();
			userDb.setApproved(false);
			userDb.setPendingApproval(true);
			userDb.setRecordStatus(EnumData.DISABLED.getName());
			userDb.setLastUpdatedBy(Helper.getActiveUser());
			userDb.setLastUpdatedDate(Helper.getCurrentDateAndTime());
			userDb.setRecordVersion(recordVersion);
			commonService.saveScreenData(userDb, userDb.getLastUpdatedBy(), userDb.getUserId(), Function.USER.getName(),
					EnumData.DISABLE_ACTION.getName(), recordVersion, EnumData.DISABLED.getName());
			usersRepository.updatePendingApproval(userDb.getUserId(), true);
			auditTrailService.setAuditTrail(Helper.getActiveUser(), userId, Function.USER.getName(),
					EnumData.DISABLE_ACTION.getName());
			return ApiResponse.success(ResponseMessage.USER_DISABLED_SUCCESS.getMessage(), userDb);
		} catch (Exception e) {
			log.error("In disableById exception", e);
			return ApiResponse.failure(ResponseMessage.OPERATION_FAILED.getMessage());
		}
	}

	public String generateRandomNumber() {
		Random rnd = new Random();
		int numb = rnd.nextInt(9999);
		String res = String.format("%04d", numb);
		System.out.println("4 digit random number = " + res);
		return res;

	}

}
