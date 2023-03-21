package com.wecodee.SpringBootPractice.admin.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.wecodee.SpringBootPractice.admin.requestDTO.SearchFilterDTO;
import com.wecodee.SpringBootPractice.admin.responseDTO.ApiResponse;
import com.wecodee.SpringBootPractice.admin.service.BaseService;

import net.minidev.json.JSONObject;

public abstract class BaseController<T> {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	private BaseService<T> baseService;

	public BaseController(BaseService<T> service) {
		this.baseService = service;
	}

	@GetMapping("/get/recordscount")
	public ApiResponse<JSONObject> getRecordsCount() {
		return baseService.getRecordsCount();
	}

	@GetMapping("/get/all")
	public ApiResponse<List<T>> get() {
		return baseService.getAll();
	}

	@GetMapping("/get/{id}")
	public ApiResponse<T> get(@PathVariable("id") String id) {
		return baseService.get(id);
	}

	@PostMapping("/get/unapproved")
	public ApiResponse<JSONObject> getUnapprovedRecords(@RequestBody SearchFilterDTO<T> filterDTO) {
		log.info("In getUnapprovedRecords:" + filterDTO);
		return baseService.getUnapprovedRecords(filterDTO);
	}

	@PostMapping("/get/approved")
	public ApiResponse<JSONObject> getApprovedRecords(@RequestBody SearchFilterDTO<T> filterDTO) {
		log.info("In getApprovedRecords:" + filterDTO);
		return baseService.getApprovedRecords(filterDTO);
	}

	@PostMapping("/create")
	public ApiResponse<T> create(@RequestBody T record) {
		return baseService.create(record);
	}

	@PostMapping("/update")
	public ApiResponse<T> update(@RequestBody T record) {
		return baseService.update(record);
	}

	@GetMapping("/approve/{id}")
	public ApiResponse<T> approve(@PathVariable("id") String id) {
		return baseService.approve(id);
	}

	@GetMapping("/reject/{id}")
	public ApiResponse<T> reject(@PathVariable("id") String id) {
		return baseService.reject(id);
	}

	@GetMapping("/delete/{id}")
	public ApiResponse<Object> deleteById(@PathVariable("id") String id) {
		return baseService.deleteById(id);
	}

	@PostMapping("/delete")
	public ApiResponse<T> delete(@RequestBody T record) {
		return baseService.delete(record);
	}

	@GetMapping("/disable/{id}")
	public ApiResponse<T> disableById(@PathVariable("id") String id) {
		return baseService.disableById(id);
	}

}
