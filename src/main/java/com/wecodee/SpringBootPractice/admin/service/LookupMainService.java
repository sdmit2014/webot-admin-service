package com.wecodee.SpringBootPractice.admin.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.domain.Sort;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wecodee.SpringBootPractice.admin.config.MakerConfig;
import com.wecodee.SpringBootPractice.admin.constant.EnumData;
import com.wecodee.SpringBootPractice.admin.constant.Function;
import com.wecodee.SpringBootPractice.admin.constant.ResponseMessage;
import com.wecodee.SpringBootPractice.admin.constant.ValidationMessage;
import com.wecodee.SpringBootPractice.admin.model.FunctionData;
import com.wecodee.SpringBootPractice.admin.model.FunctionDataArchive;
import com.wecodee.SpringBootPractice.admin.model.LookupMain;
import com.wecodee.SpringBootPractice.admin.model.LookupValues;
import com.wecodee.SpringBootPractice.admin.model.LookupValuesAll;
import com.wecodee.SpringBootPractice.admin.repository.FunctionDataArchiveRepository;
import com.wecodee.SpringBootPractice.admin.repository.FunctionDataRepository;
import com.wecodee.SpringBootPractice.admin.repository.LookupMainRepository;
import com.wecodee.SpringBootPractice.admin.repository.LookupValuesAllRepository;
import com.wecodee.SpringBootPractice.admin.requestDTO.SearchFilterDTO;
import com.wecodee.SpringBootPractice.admin.responseDTO.ApiResponse;
import com.wecodee.SpringBootPractice.admin.util.Helper;
import com.wecodee.SpringBootPractice.common.service.CommonService;
import net.minidev.json.JSONObject;

public class LookupMainService extends BaseService<LookupMain> {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	private LookupMainRepository lookupMainRepository;

	@Autowired
	private AccessManagementService accessManagementService;

	@Autowired
	private CommonService commonService;

	@Autowired
	private AuditTrailService auditTrailService;

	@Autowired
	private FunctionDataRepository functionDataRepository;

	@Autowired
	private FunctionDataArchiveRepository functionDataArchiveRepository;

	@Autowired
	private EntityManager entityManager;

	@Autowired
	private MakerConfig makerConfig;

	@Autowired
	private LookupValuesAllRepository lookupValuesAllRepository;

	public LookupMainService(LookupMainRepository lookupMainRepository) {
		super(lookupMainRepository);
		this.lookupMainRepository = lookupMainRepository;
	}

	public ApiResponse<List<LookupValuesAll>> getLookUpDetailsByType(String type) {
		log.info("Lookup type :" + type);
		try {
			List<LookupValuesAll> lookupValuesAllList = lookupValuesAllRepository.getByType(type);
			if (type.equals("COUNTRY_CODE")) {
				List<LookupValuesAll> lookupValuesAllList1 = lookupValuesAllList.stream()
						.sorted(Comparator.comparing(LookupValuesAll::getName)).collect(Collectors.toList());
				return ApiResponse.success(ResponseMessage.FETCH_SUCCESS.getMessage(), lookupValuesAllList1);
			} else if (type.equals("NATIONALITY")) {
				List<LookupValuesAll> lookupValuesAllList2 = lookupValuesAllList.stream()
						.sorted((s1, s2) -> s1.getName().compareTo(s2.getName())).collect(Collectors.toList());
				return ApiResponse.success(ResponseMessage.FETCH_SUCCESS.getMessage(), lookupValuesAllList2);
			} else if (type.equals("COUNTRY")) {
				List<LookupValuesAll> lookupValuesAllList3 = lookupValuesAllList.stream()
						.sorted((s1, s2) -> s1.getName().compareTo(s2.getName())).collect(Collectors.toList());
				return ApiResponse.success(ResponseMessage.FETCH_SUCCESS.getMessage(), lookupValuesAllList3);
			}
			return ApiResponse.success(ResponseMessage.FETCH_SUCCESS.getMessage(), lookupValuesAllList);

		} catch (Exception e) {
			log.info("In Exception :" + e);
			return ApiResponse.failure(ResponseMessage.OPERATION_FAILED.getMessage());
		}

	}

	public ApiResponse<List<LookupValuesAll>> getLookUpDetailsByValue(String type, String value) {
		log.info("Lookup type :" + type + " Lookup value :" + value);
		try {
			List<LookupValuesAll> lookupValuesList = lookupValuesAllRepository.getByTypeAndValue(type, value);
			return ApiResponse.success(ResponseMessage.FETCH_SUCCESS.getMessage(), lookupValuesList);
		} catch (Exception e) {
			return ApiResponse.success(ResponseMessage.OPERATION_FAILED.getMessage());
		}
	}

	@Override
	public ApiResponse<LookupMain> get(String type) {
		log.info("In lookup service get method is executing");
		try {
			if (!accessManagementService.checkUserAccess(Helper.getActiveUser(), Function.LOOKUP.getName(),
					EnumData.VIEW_ACCESS.getName())) {
				return ApiResponse.failure(ValidationMessage.ACCESS_NOT_AVAILABLE_FOR_VIEW_LOOKUP.getMessage());
			}
			log.info(type);
			ObjectMapper mapper = new ObjectMapper();
			LookupMain lookupMain = new LookupMain();
			FunctionData functionDataAction = functionDataRepository.getByFunctionIdAndPkey(Function.LOOKUP.getName(),
					type);
			if (functionDataAction != null) {
				lookupMain = mapper.readValue(functionDataAction.getFunctionJsonData(), LookupMain.class);
			} else {
				lookupMain = lookupMainRepository.getByTypeAndApproved(type, true);
			}
			return ApiResponse.success(ResponseMessage.FETCH_SUCCESS.getMessage(), lookupMain);
		} catch (Exception e) {
			log.error("In get exception", e);
			return ApiResponse.failure(ResponseMessage.OPERATION_FAILED.getMessage());
		}
	}

	@Override
	public ApiResponse<LookupMain> update(LookupMain lookupMain) {
		try {
			if (!accessManagementService.checkUserAccess(Helper.getActiveUser(), Function.LOOKUP.getName(),
					EnumData.CREATE_ACCESS.getName())) {
				return ApiResponse.failure(ValidationMessage.ACCESS_NOT_AVAILABLE_FOR_UPDATE_LOOKUP.getMessage());
			}
			List<LookupValues> lookupList = new ArrayList<>();
			LookupMain lookupfrmDb = lookupMainRepository.getByType(lookupMain.getType());
			if (lookupfrmDb.getLookupValues().size() > 0) {
				for (LookupValues lookupValues : lookupMain.getLookupValues()) {
					lookupValues.setType(lookupfrmDb.getType());
					lookupList.add(lookupValues);
				}
				lookupMain.setLookupValues(lookupList);
			}
			lookupMain.setType(lookupfrmDb.getType());
			// lookupMain.setLookupDesc(lookupfrmDb.getLookupDesc());
			lookupMain.setRecordStatus(lookupfrmDb.getRecordStatus());
			int recordVersion = lookupfrmDb.getRecordVersion() + 1;
			lookupMain.setApproved(false);
			lookupMain.setPendingApproval(true);
			lookupMain.setRecordVersion(recordVersion);
			lookupMain.setCreatedBy(lookupfrmDb.getCreatedBy());
			lookupMain.setCreatedDate(lookupfrmDb.getCreatedDate());
			lookupMain.setLastUpdatedBy(Helper.getActiveUser());
			lookupMain.setLastUpdatedDate(Helper.getCurrentDateAndTime());
			commonService.saveScreenData(lookupMain, lookupfrmDb.getCreatedBy(), lookupMain.getType(),
					Function.LOOKUP.getName(), EnumData.UPDATE_ACTION.getName(), recordVersion,
					EnumData.INACTIVE.getName());
			lookupMainRepository.updatePendingApproval(lookupMain.getType(), true);
			auditTrailService.setAuditTrail(Helper.getActiveUser(), lookupMain.getType(), Function.LOOKUP.getName(),
					EnumData.UPDATE_ACTION.getName());
			return ApiResponse.success(ResponseMessage.OPERATION_SUCCESS.getMessage(), lookupMain);
		} catch (Exception e) {
			log.error("In update exception", e);
			return ApiResponse.failure(ResponseMessage.OPERATION_FAILED.getMessage());
		}
	}

	@Override
	public ApiResponse<LookupMain> approve(String type) {
		log.info("In lookup service approve method is executing");
		ObjectMapper mapper = new ObjectMapper();
		try {
			if (!accessManagementService.checkUserAccess(Helper.getActiveUser(), Function.LOOKUP.getName(),
					EnumData.APPROVE_ACCESS.getName())) {
				return ApiResponse.failure(ValidationMessage.ACCESS_NOT_AVAILABLE_FOR_APPROVE_LOOKUP.getMessage());
			}
			FunctionData functionData = functionDataRepository.getByFunctionIdAndPkey(Function.LOOKUP.getName(), type);
			LookupMain lookupMain = mapper.readValue(functionData.getFunctionJsonData(), LookupMain.class);
			if (!makerConfig.checkApprovers(lookupMain.getRecordVersion(), lookupMain.getCreatedBy(),
					lookupMain.getLastUpdatedBy())) {
				return ApiResponse.failure(ResponseMessage.MAKER_CANNOT_APPROVE.getMessage());
			}

			lookupMain.setApproved(true);
			lookupMain.setPendingApproval(false);
			lookupMain.setApprovedBy(Helper.getActiveUser());
			lookupMain.setApprovedDate(Helper.getCurrentDateAndTime());

			log.info("Lookup object " + lookupMain);
			lookupMainRepository.save(lookupMain);
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
			auditTrailService.setAuditTrail(Helper.getActiveUser(), type, Function.LOOKUP.getName(),
					EnumData.APPROVE_ACCESS.getName());
			return ApiResponse.success(ResponseMessage.OPERATION_SUCCESS.getMessage(), lookupMain);
		} catch (Exception e) {
			log.error("In approve exception", e);
			return ApiResponse.failure(ResponseMessage.OPERATION_FAILED.getMessage());
		}
	}

	@Override
	public ApiResponse<LookupMain> reject(String type) {
		log.info("In lookupmain service reject method is executing");
		try {
			FunctionData functionData = functionDataRepository.getByFunctionIdAndPkey(Function.LOOKUP.getName(), type);
			LookupMain lookupMain = (LookupMain) convertToDto(functionData.getFunctionJsonData(), LookupMain.class);
			FunctionDataArchive functionDataArchive = (FunctionDataArchive) convertToDto(functionData,
					new FunctionDataArchive());
			if (!accessManagementService.checkUserAccess(Helper.getActiveUser(), Function.LOOKUP.getName(),
					EnumData.APPROVE_ACCESS.getName())) {
				return ApiResponse.failure(ValidationMessage.ACCESS_NOT_AVAILABLE_FOR_REJECT_LOOKUP.getMessage());
			}
			if (!makerConfig.checkApprovers(lookupMain.getRecordVersion(), lookupMain.getCreatedBy(),
					lookupMain.getLastUpdatedBy())) {
				return ApiResponse.failure(ResponseMessage.MAKER_CANNOT_REJECT.getMessage());
			}
			functionDataArchive.setStatus(EnumData.REJECT.getName());
			functionDataArchive.setApprovedBy(Helper.getActiveUser());
			functionDataArchive.setApprovedDate(Helper.getCurrentDateAndTime());
			functionDataArchiveRepository.save(functionDataArchive);
			lookupMainRepository.updatePendingApproval(type, false);
			functionDataRepository.delete(functionData);
			auditTrailService.setAuditTrail(Helper.getActiveUser(), type, Function.LOOKUP.getName(),
					EnumData.REJECT_ACTION.getName());
			return ApiResponse.success(ResponseMessage.OPERATION_SUCCESS.getMessage(), null);
		} catch (Exception e) {
			log.error("In reject exception", e);
			return ApiResponse.failure(ResponseMessage.OPERATION_FAILED.getMessage());
		}
	}

	@Override
	public ApiResponse<JSONObject> getApprovedRecords(SearchFilterDTO<LookupMain> filterDTO) {
		log.info("In lookupmain service getApprovedRecords method is executing");
		try {
			log.info("approve filter");
			JSONObject jsonObject = new JSONObject();
			LookupMain lookupMain = filterDTO.getFilterFieldValues();

			CriteriaBuilder builder = entityManager.getCriteriaBuilder();
			CriteriaQuery<LookupMain> criteria = builder.createQuery(LookupMain.class);
			Root<LookupMain> newlookup = criteria.from(LookupMain.class);
			List<Predicate> predicates = new ArrayList<Predicate>();

			log.info("Message " + filterDTO.getPageNumber());
			log.info("Size " + filterDTO.getPageSize());

			Pageable pageable = PageRequest.of(filterDTO.getPageNumber(), filterDTO.getPageSize(),
					Sort.by("type").ascending());

			if (lookupMain.getType() != null) {
				predicates.add(builder.like(builder.lower(newlookup.get("type")),
						"%" + lookupMain.getType().toLowerCase() + "%"));
			}
			if (lookupMain.getLookupDesc() != null) {
				predicates.add(builder.like(builder.lower(newlookup.get("lookupDesc")),
						"%" + lookupMain.getLookupDesc().toLowerCase() + "%"));
			}

			predicates.add(builder.equal(newlookup.get("pendingApproval"), false));
			criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));

			// This query fetches the records as per the Page Limit
			List<LookupMain> result = entityManager.createQuery(criteria).setFirstResult((int) pageable.getOffset())
					.setMaxResults(pageable.getPageSize()).getResultList();

			// Create Count Query
			CriteriaQuery<Long> countQuery = builder.createQuery(Long.class);
			Root<LookupMain> lookupCount = countQuery.from(LookupMain.class);
			countQuery.select(builder.count(lookupCount))
					.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));

			// Fetches the count of all records as per given criteria
			Long count = entityManager.createQuery(countQuery).getSingleResult();

			log.info("count->" + count);
			log.info("size->" + result.size());
			Page<LookupMain> pageData = new PageImpl<>(result, pageable, count);

			jsonObject.put("currentPage", pageData.getNumber());
			jsonObject.put("totalItems", pageData.getTotalElements());
			jsonObject.put("totalPages", pageData.getTotalPages());
			jsonObject.put("items", pageData.getContent());
			log.info("" + pageData.getContent().size());
			if (pageData.getContent().size() == 0)
				return ApiResponse.success(ResponseMessage.OPERATION_SUCCESS.getMessage(), jsonObject);
			else
				return ApiResponse.success(ResponseMessage.OPERATION_SUCCESS.getMessage(), jsonObject);
		} catch (Exception e) {
			log.error("In getApprovedRecords exception", e);
			return ApiResponse.failure(ResponseMessage.OPERATION_FAILED.getMessage());
		}
	}

	@Override
	public ApiResponse<JSONObject> getUnapprovedRecords(SearchFilterDTO<LookupMain> filterDTO) {
		log.info("In lookupmain service getUnapprovedRecords method is executing");
		try {
			ObjectMapper mapper = new ObjectMapper();
			log.info("unapprove filter");
			LookupMain lookupMain = filterDTO.getFilterFieldValues();
			List<LookupMain> finalList = new ArrayList<LookupMain>();
			List<String> type = new ArrayList<String>();
			List<FunctionData> functionDataList = functionDataRepository.findByFunctionId(Function.LOOKUP.getName());
			for (FunctionData functionData : functionDataList) {
				if (functionData.getStatus().equals(EnumData.INACTIVE.getName())
						|| functionData.getStatus().equals(EnumData.DISABLED.getName())) {
					LookupMain data = mapper.readValue(functionData.getFunctionJsonData(), LookupMain.class);
					type.add(data.getType());
					finalList.add(data);
					log.info("data->" + data);
				}
			}
			List<LookupMain> filteredList = new ArrayList<LookupMain>();
			filteredList = finalList;
			if (lookupMain.getType() != null) {
				filteredList = filteredList.stream()
						.filter(c -> c.getType().toLowerCase().contains(lookupMain.getType().toLowerCase()))
						.collect(Collectors.toList());
			}
			if (lookupMain.getLookupDesc() != null) {
				filteredList = filteredList.stream()
						.filter(c -> c.getLookupDesc().toLowerCase().contains(lookupMain.getLookupDesc().toLowerCase()))
						.collect(Collectors.toList());
			}

			Page<LookupMain> pageData = new PageImpl<>(filteredList,
					PageRequest.of(filterDTO.getPageNumber(), filterDTO.getPageSize()), filteredList.size());

			JSONObject jsonObject = new JSONObject();
			jsonObject.put("currentPage", pageData.getNumber());
			jsonObject.put("totalItems", pageData.getTotalElements());
			jsonObject.put("totalPages", pageData.getTotalPages());
			jsonObject.put("items", filteredList);

			if (pageData.getContent().size() == 0)
				return ApiResponse.success(ResponseMessage.OPERATION_SUCCESS.getMessage(), jsonObject);
			else
				return ApiResponse.success(ResponseMessage.OPERATION_SUCCESS.getMessage(), jsonObject);
		} catch (Exception e) {
			log.error("In getUnapprovedRecords exception" + e);
			return ApiResponse.failure(ResponseMessage.OPERATION_FAILED.getMessage());
		}
	}

}
