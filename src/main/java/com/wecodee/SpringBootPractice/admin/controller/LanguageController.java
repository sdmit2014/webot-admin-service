package com.wecodee.SpringBootPractice.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wecodee.SpringBootPractice.admin.model.Language;
import com.wecodee.SpringBootPractice.admin.service.LanguageService;

@RestController
@RequestMapping("/language")
public class LanguageController extends BaseController<Language> {

	private LanguageService languageService;

	@Autowired
	public LanguageController(LanguageService languageService) {
		super(languageService);
		this.languageService = languageService;
	}

}
