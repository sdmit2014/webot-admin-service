package com.wecodee.SpringBootPractice.admin.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wecodee.SpringBootPractice.admin.constant.ResponseMessage;
import com.wecodee.SpringBootPractice.admin.model.FunctionData;
import com.wecodee.SpringBootPractice.admin.repository.BaseRepository;
import com.wecodee.SpringBootPractice.admin.repository.FunctionDataRepository;
import com.wecodee.SpringBootPractice.admin.requestDTO.SearchFilterDTO;
import com.wecodee.SpringBootPractice.admin.responseDTO.ApiResponse;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import net.minidev.json.JSONObject;

public abstract class BaseService<T> {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	private BaseRepository<T> baseRepository;

	@Autowired
	private FunctionDataRepository functionDataRepository;

	public BaseService(BaseRepository<T> repository) {
		this.baseRepository = repository;
	}

	public String getFunctionName() {
		return "";
	};

	public ApiResponse<List<T>> getAll() {
		return ApiResponse.success(ResponseMessage.FETCH_SUCCESS.getMessage(), baseRepository.findAll());
	}

	public ApiResponse<JSONObject> getUnapprovedRecords(SearchFilterDTO<T> filterDTO) {
		log.info("In base service getUnapprovedRecords:" + filterDTO);
		return ApiResponse.success(ResponseMessage.FETCH_SUCCESS.getMessage(), null);
	}

	public ApiResponse<JSONObject> getApprovedRecords(SearchFilterDTO<T> filterDTO) {
		log.info("In base service getApprovedRecords:" + filterDTO);
		return ApiResponse.success(ResponseMessage.FETCH_SUCCESS.getMessage(), null);
	}

	public ApiResponse<T> get(String id) {
		Optional<T> entity = baseRepository.findById(id);
		if (!entity.isPresent()) {
			return ApiResponse.failure(ResponseMessage.NO_RECORDS_FOUND.getMessage());
		}
		return ApiResponse.success(ResponseMessage.OPERATION_SUCCESS.getMessage(), entity.get());
	}

	public ApiResponse<JSONObject> getRecordsCount() {

		Integer approvedRecordsCount = baseRepository.getApprovedRecordsCount();

		List<FunctionData> functionDataList = functionDataRepository.findByFunctionId(getFunctionName());
		Integer upapprovedRecordsCount = functionDataList.size();

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("approvedRecordsCount", approvedRecordsCount);
		jsonObject.put("upapprovedRecordsCount", upapprovedRecordsCount);
		return ApiResponse.success(ResponseMessage.FETCH_SUCCESS.getMessage(), jsonObject);
	}

	public ApiResponse<T> create(T entity) {
		log.info("In base service create method");
		return ApiResponse.success(ResponseMessage.OPERATION_SUCCESS.getMessage(), baseRepository.save(entity));
	}

	public ApiResponse<T> update(T entity) {
		log.info("In base service update method");
		return ApiResponse.success(ResponseMessage.OPERATION_SUCCESS.getMessage(), baseRepository.save(entity));
	}

	public ApiResponse<T> approve(String id) {
		return null;
	}

	public ApiResponse<T> reject(String id) {
		return null;
	}

	public ApiResponse<Object> deleteById(String id) {
		return ApiResponse.success(ResponseMessage.DELETE_SUCCESS.getMessage(), null);
	}

	public ApiResponse<T> delete(T entity) {
		baseRepository.delete(entity);
		return ApiResponse.success(ResponseMessage.OPERATION_SUCCESS.getMessage(), entity);
	}

	public ApiResponse<T> disableById(String id) {
		return null;
	}

	public static Object getObjectFromFunctionData(String functionJsonData, Object obj)
			throws JsonMappingException, JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		Object object = mapper.readValue(functionJsonData, obj.getClass());
		return object;
	}

	public Object convertToDto(Object source, Object target) {
		log.info("In base service convertToDto method is executing");
		BeanUtils.copyProperties(source, target);
		return target;
	}

}
