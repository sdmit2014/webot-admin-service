package com.wecodee.SpringBootPractice.admin.service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.wecodee.SpringBootPractice.admin.constant.ResponseMessage;
import com.wecodee.SpringBootPractice.admin.model.TemplateTag;
import com.wecodee.SpringBootPractice.admin.repository.TemplateTagRepository;
import com.wecodee.SpringBootPractice.admin.responseDTO.ApiResponse;

public class TemplateTagService extends BaseService<TemplateTag> {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private TemplateTagRepository templateTagRepository;

	public TemplateTagService(TemplateTagRepository templateTagRepository) {
		super(templateTagRepository);
		this.templateTagRepository = templateTagRepository;
	}

	public ApiResponse<List<TemplateTag>> getTemplateTagService(String notificationType) {
		try {
			List<TemplateTag> templateTagList = templateTagRepository.getAllNotificationType(notificationType);
			return ApiResponse.success(ResponseMessage.FETCH_SUCCESS.getMessage(), templateTagList);
		} catch (Exception e) {
			log.error("In getTemplateTagService exception", e);
			return ApiResponse.failure(ResponseMessage.FETCH_FAILED.getMessage());
		}
	}

	public ApiResponse<List<TemplateTag>> getAllTemplateTagService() {
		try {
			List<TemplateTag> templateTags = templateTagRepository.findAll();
			return ApiResponse.success(ResponseMessage.FETCH_SUCCESS.getMessage(), templateTags);
		} catch (Exception e) {
			log.error("In getAllTemplateTagService exception", e);
			return ApiResponse.failure(ResponseMessage.OPERATION_FAILED.getMessage());
		}
	}

}
