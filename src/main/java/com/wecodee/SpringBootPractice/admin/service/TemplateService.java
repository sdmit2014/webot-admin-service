package com.wecodee.SpringBootPractice.admin.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.wecodee.SpringBootPractice.admin.constant.ResponseMessage;
import com.wecodee.SpringBootPractice.admin.model.Template;
import com.wecodee.SpringBootPractice.admin.repository.TemplateRepository;
import com.wecodee.SpringBootPractice.admin.responseDTO.ApiResponse;

public class TemplateService extends BaseService<Template> {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private TemplateRepository templateRepository;

	public TemplateService(TemplateRepository templateRepository) {
		super(templateRepository);
		this.templateRepository = templateRepository;
	}

	public ApiResponse<List<Template>> getByChannel(String channel) {
		try {
			List<Template> templateList = templateRepository.getByChannel(channel);
			return ApiResponse.success(ResponseMessage.FETCH_SUCCESS.getMessage(), templateList);
		} catch (Exception e) {
			log.info("In Exceptio :" + e);
			return ApiResponse.failure(ResponseMessage.OPERATION_FAILED.getMessage());
		}
	}

}
