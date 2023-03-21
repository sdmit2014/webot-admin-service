package com.wecodee.SpringBootPractice.admin.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.wecodee.SpringBootPractice.admin.constant.ResponseMessage;
import com.wecodee.SpringBootPractice.admin.model.Language;
import com.wecodee.SpringBootPractice.admin.repository.LanguageRepository;
import com.wecodee.SpringBootPractice.admin.responseDTO.ApiResponse;

@Service
public class LanguageService extends BaseService<Language> {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	private LanguageRepository languageRepository;

	public LanguageService(LanguageRepository languageRepository) {
		super(languageRepository);
		this.languageRepository = languageRepository;
	}

	@Override
	public ApiResponse<List<Language>> getAll() {
		try {
			return ApiResponse.success(ResponseMessage.FETCH_SUCCESS.getMessage(), languageRepository.findAll());
		} catch (Exception e) {
			log.info("In getAll exception :" + e);
			return ApiResponse.failure(ResponseMessage.FETCH_FAILED.getMessage());
		}
	}

}
