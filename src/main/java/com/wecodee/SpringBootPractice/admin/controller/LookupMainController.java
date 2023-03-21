package com.wecodee.SpringBootPractice.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wecodee.SpringBootPractice.admin.model.LookupMain;
import com.wecodee.SpringBootPractice.admin.model.LookupValuesAll;
import com.wecodee.SpringBootPractice.admin.responseDTO.ApiResponse;
import com.wecodee.SpringBootPractice.admin.service.LookupMainService;

@RestController
@RequestMapping("/lookup")
public class LookupMainController extends BaseController<LookupMain> {

	private final LookupMainService lookupMainService;

	@Autowired
	public LookupMainController(LookupMainService lookupMainService) {
		super(lookupMainService);
		this.lookupMainService = lookupMainService;
	}

	@GetMapping("/getbytype/{type}")
	public ApiResponse<List<LookupValuesAll>> getLookUpDetailsByType(@PathVariable("type") String type) {
		return lookupMainService.getLookUpDetailsByType(type);
	}

	@GetMapping("/getbyvalue/{type}/{value}")
	public ApiResponse<List<LookupValuesAll>> lookUpDetail(@PathVariable("type") String type,
			@PathVariable("value") String value) {
		return lookupMainService.getLookUpDetailsByValue(type, value);
	}

}
