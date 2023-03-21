package com.wecodee.SpringBootPractice.appconfiguration.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wecodee.SpringBootPractice.admin.controller.BaseController;
import com.wecodee.SpringBootPractice.admin.service.BaseService;
import com.wecodee.SpringBootPractice.appconfiguration.model.SecurityParameters;

@RestController
@RequestMapping("/securityparams")
public class SecurityParameterController extends BaseController<SecurityParameters>{

	public SecurityParameterController(BaseService<SecurityParameters> service) {
		super(service);
		// TODO Auto-generated constructor stub
	}

}
