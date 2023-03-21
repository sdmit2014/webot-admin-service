package com.wecodee.SpringBootPractice.login.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wecodee.SpringBootPractice.admin.config.JwtTokenUtil;
import com.wecodee.SpringBootPractice.admin.constant.ResponseMessage;
import com.wecodee.SpringBootPractice.admin.responseDTO.ApiResponse;
import com.wecodee.SpringBootPractice.admin.service.AuditTrailService;
import com.wecodee.SpringBootPractice.usermanagement.repository.UsersRepository;

@Service
public class LogoutService {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private AuditTrailService auditTrailService;

	@Autowired
	private UsersRepository usersRepository;

	public ApiResponse<String> doLogOut() {
		log.info("In logout service doLogOut method is executing");
//		String token = JwtRequestFilter.jwtToken;
//		String userName=jwtTokenUtil.getUsernameFromToken(token);
//		User user=usersRepository.getByUserId(userName);

//		Date date=jwtTokenUtil.getExpirationDateFromToken(token);

//			user.setLoginStatus(false);
//			usersRepository.save(user);
//			auditTrailService.setAuditTrail(Helper.getActiveUser(), user.getUserId(), Function.LOGOUT.getName(),
//                    EnumData.LOGOUT.getName());
		return ApiResponse.success(ResponseMessage.OPERATION_SUCCESS.getMessage(), "Logged out successfully");
	}

}
