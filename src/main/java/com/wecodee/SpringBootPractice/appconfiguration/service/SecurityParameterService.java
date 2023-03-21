package com.wecodee.SpringBootPractice.appconfiguration.service;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
import com.wecodee.SpringBootPractice.admin.responseDTO.ApiResponse;
import com.wecodee.SpringBootPractice.admin.service.AccessManagementService;
import com.wecodee.SpringBootPractice.admin.service.AuditTrailService;
import com.wecodee.SpringBootPractice.admin.service.BaseService;
import com.wecodee.SpringBootPractice.admin.util.Helper;
import com.wecodee.SpringBootPractice.appconfiguration.model.SecurityParameters;
import com.wecodee.SpringBootPractice.appconfiguration.repository.SecurityParameterRepository;
import com.wecodee.SpringBootPractice.common.service.CommonService;

@Service
public class SecurityParameterService extends BaseService<SecurityParameters> {

	private SecurityParameterRepository securityParameterRepository;

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private EntityManager entityManager;

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
	private MakerConfig makerConfig;

	@Autowired
	public SecurityParameterService(SecurityParameterRepository securityParameterRepository) {
		super(securityParameterRepository);
		this.securityParameterRepository = securityParameterRepository;
	}

	@Override
	public ApiResponse<SecurityParameters> get(String id) {
		log.info("In security parameters service get method is executing");
		try {
			if (!accessManagementService.checkUserAccess(Helper.getActiveUser(), Function.SECURITYPARAMETER.getName(),
					EnumData.VIEW_ACCESS.getName())) {
				return ApiResponse
						.failure(ValidationMessage.ACCESS_NOT_AVAILABLE_FOR_VIEW_SECURITY_PARAMETER.getMessage());
			}
			ObjectMapper mapper = new ObjectMapper();
			SecurityParameters securityParameters = new SecurityParameters();
			FunctionData functionData = functionDataRepository
					.getByFunctionIdAndPkey(Function.SECURITYPARAMETER.getName(), id);
			if (functionData != null) {
				securityParameters = mapper.readValue(functionData.getFunctionJsonData(), SecurityParameters.class);
			} else {
				securityParameters = securityParameterRepository.findAll().get(0);
			}
			auditTrailService.setAuditTrail(Helper.getActiveUser(), id, Function.SECURITYPARAMETER.getName(),
					EnumData.VIEW_ACTION.getName());
			return ApiResponse.success(ResponseMessage.OPERATION_SUCCESS.getMessage(), securityParameters);
		} catch (Exception e) {
			log.error("In get exception", e);
			return ApiResponse.failure(ResponseMessage.OPERATION_FAILED.getMessage());
		}
	}

	@Override
	public ApiResponse<SecurityParameters> update(SecurityParameters securityParameters) {
		log.info("security parameters Service update method executing");
		try {
			if (!accessManagementService.checkUserAccess(Helper.getActiveUser(), Function.SECURITYPARAMETER.getName(),
					EnumData.CREATE_ACCESS.getName())) {
				return ApiResponse
						.failure(ValidationMessage.ACCESS_NOT_AVAIALABLE_FOR_UPDATE_SECURITY_PARAMETER.getMessage());
			}
			SecurityParameters securityParametersFrmDb = securityParameterRepository.findAll().get(0);
			int recordVersion = securityParametersFrmDb.getRecordVersion() + 1;
			securityParameters.setId(securityParametersFrmDb.getId());
			securityParameters.setPendingApproval(true);
			securityParameters.setLastUpdatedBy(Helper.getActiveUser());
			securityParameters.setLastUpdatedDate(Helper.getCurrentDateTime());
			securityParameters.setRecordVersion(recordVersion);
			securityParameters.setRecordStatus(securityParametersFrmDb.getRecordStatus());
			commonService.saveScreenData(securityParameters, securityParametersFrmDb.getCreatedBy(),
					String.valueOf(securityParameters.getId()), EnumData.SECURITY_PARAMETER.getName(),
					EnumData.UPDATE_ACTION.getName(), recordVersion, EnumData.INACTIVE.getName());
			securityParameterRepository.updatePendingApproval(securityParametersFrmDb.getId(), true);
			auditTrailService.setAuditTrail(Helper.getActiveUser(), String.valueOf(securityParameters.getId()),
					Function.SECURITYPARAMETER.getName(), EnumData.UPDATE_ACTION.getName());
			return ApiResponse.success(ResponseMessage.SECURITY_PARAMETER_UPDATED.getMessage(), securityParameters);
		} catch (Exception e) {
			log.error("In update exception", e);
			return ApiResponse.failure(ResponseMessage.UPDATE_FAILED.getMessage());
		}
	}

	@Override
	public ApiResponse<SecurityParameters> approve(String id) {
		log.info("In security parameters service approve method is executing");
		SecurityParameters securityParameters = new SecurityParameters();
		try {
			FunctionData functionData = functionDataRepository
					.getByFunctionIdAndPkey(EnumData.SECURITY_PARAMETER.getName(), id);
			securityParameters = (SecurityParameters) Helper
					.getObjectFromFunctionData(functionData.getFunctionJsonData(), securityParameters);
			if (!accessManagementService.checkUserAccess(Helper.getActiveUser(), Function.SECURITYPARAMETER.getName(),
					EnumData.APPROVE_ACCESS.getName())) {
				return ApiResponse
						.failure(ValidationMessage.ACCESS_NOT_AVAIALABLE_FOR_APPROVE_SECURITY_PARAMETER.getMessage());
			}
			if (!makerConfig.checkApprovers(securityParameters.getRecordVersion(), securityParameters.getCreatedBy(),
					securityParameters.getLastUpdatedBy())) {
				return ApiResponse.failure(ResponseMessage.MAKER_CANNOT_APPROVE.getMessage());
			}
			securityParameters.setApproved(true);
			securityParameters.setPendingApproval(false);
			securityParameters.setApprovedBy(Helper.getActiveUser());
			securityParameters.setApprovedDate(Helper.getCurrentDateTime());
			securityParameterRepository.save(securityParameters);
			FunctionDataArchive functionDataArchive = (FunctionDataArchive) convertToDto(functionData,
					new FunctionDataArchive());
			functionDataArchive.setApprovedBy(Helper.getActiveUser());
			functionDataArchive.setApprovedDate(Helper.getCurrentDateTime());
			functionDataArchiveRepository.save(functionDataArchive);
			functionDataRepository.delete(functionData);
			auditTrailService.setAuditTrail(Helper.getActiveUser(), id, Function.SECURITYPARAMETER.getName(),
					EnumData.APPROVE_ACTION.getName());
			return ApiResponse.success(ResponseMessage.SECURITY_PARAMETER_APPROVED.getMessage(), securityParameters);
		} catch (Exception e) {
			log.error("In approve exception", e);
			return ApiResponse.failure(ResponseMessage.OPERATION_FAILED.getMessage());
		}
	}

	@Override
	public ApiResponse<SecurityParameters> reject(String id) {
		log.info("In security parameters service reject method is executing");
		try {
			if (!accessManagementService.checkUserAccess(Helper.getActiveUser(), Function.SECURITYPARAMETER.getName(),
					EnumData.APPROVE_ACCESS.getName())) {
				return ApiResponse
						.failure(ValidationMessage.ACCESS_NOT_AVAIALABLE_FOR_REJECT_SECURITY_PARAMETER.getMessage());
			}
			FunctionData functionData = functionDataRepository
					.getByFunctionIdAndPkey(EnumData.SECURITY_PARAMETER.getName(), id);
			SecurityParameters securityParameters = (SecurityParameters) convertToDto(
					functionData.getFunctionJsonData(), SecurityParameters.class);
			FunctionDataArchive functionDataArchive = (FunctionDataArchive) convertToDto(functionData,
					new FunctionDataArchive());
			if (!makerConfig.checkApprovers(securityParameters.getRecordVersion(), securityParameters.getCreatedBy(),
					securityParameters.getLastUpdatedBy())) {
				return ApiResponse.failure(ResponseMessage.MAKER_CANNOT_REJECT.getMessage());
			}
			functionDataArchive.setStatus(EnumData.REJECT.getName());
			functionDataArchive.setAction(EnumData.REJECT.getName());
			functionDataArchiveRepository.save(functionDataArchive);
			functionDataRepository.delete(functionData);
			securityParameters.setPendingApproval(false);
			securityParameterRepository.save(securityParameters);
			auditTrailService.setAuditTrail(Helper.getActiveUser(), id, Function.SECURITYPARAMETER.getName(),
					EnumData.REJECT_ACTION.getName());
			return ApiResponse.success(ResponseMessage.SECURITY_PARAMETER_REJECTED.getMessage(), securityParameters);
		} catch (Exception e) {
			log.error("In reject exception", e);
			return ApiResponse.failure(ResponseMessage.OPERATION_FAILED.getMessage());
		}
	}

}
