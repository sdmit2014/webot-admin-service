package com.wecodee.SpringBootPractice.login.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.wecodee.SpringBootPractice.admin.config.JwtTokenUtil;
import com.wecodee.SpringBootPractice.admin.config.JwtUserDetailsService;
import com.wecodee.SpringBootPractice.admin.constant.EnumData;
import com.wecodee.SpringBootPractice.admin.constant.Function;
import com.wecodee.SpringBootPractice.admin.constant.ResponseMessage;
import com.wecodee.SpringBootPractice.admin.responseDTO.ApiResponse;
import com.wecodee.SpringBootPractice.admin.service.AuditTrailService;
import com.wecodee.SpringBootPractice.admin.util.Helper;
import com.wecodee.SpringBootPractice.appconfiguration.repository.SecurityParameterRepository;
import com.wecodee.SpringBootPractice.common.service.PasswordValidationService;
import com.wecodee.SpringBootPractice.login.dto.LoginAuthenticationRequestDTO;
import com.wecodee.SpringBootPractice.login.dto.LoginAuthenticationResponseDTO;
import com.wecodee.SpringBootPractice.login.dto.RefreshTokenDTO;
import com.wecodee.SpringBootPractice.usermanagement.model.User;
import com.wecodee.SpringBootPractice.usermanagement.repository.UsersRepository;

@Service
public class LoginAuthenticationService {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService userDetailsService;

	@Autowired
	private UsersRepository UsersRepository;

	@Autowired
	private PasswordValidationService passwordValidationService;

	@Autowired
	private AuditTrailService auditTrailService;

	@Autowired
	private SecurityParameterRepository securityParameterRepository;

	@Autowired
	private UsersRepository usersRepository;

	public ApiResponse<LoginAuthenticationResponseDTO> loginAuthenticate(
			LoginAuthenticationRequestDTO authenticationRequest) {
		log.info("Inside Login Authentication.");
		LoginAuthenticationResponseDTO responseDTO = new LoginAuthenticationResponseDTO();
		try {
			final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUserId());
			authenticate(authenticationRequest.getUserId(), authenticationRequest.getPassword());
			final String token = jwtTokenUtil.generateToken(userDetails);
			final String refreshToken = jwtTokenUtil.generateRefreshToken(userDetails);

			User user = UsersRepository.getByUserId(userDetails.getUsername());
			System.out.println("user details **************** " + user);
			log.info("User " + user);
			if (!user.getApproved()) {
				return ApiResponse.failure(ResponseMessage.USER_UNAPPROVED.getMessage());
			} else if (user.getRecordStatus().equalsIgnoreCase(EnumData.ACTIVE.getName())) {
				responseDTO.setToken(token);
//				responseDTO.setUserName(user.getFirstName() + " " + user.getLastName());
				responseDTO.setUserName(user.getFirstName());
				responseDTO.setChangePassword(user.isChgPassFlg());
				responseDTO.setRefreshToken(refreshToken);
				user.setLoginStatus(true);
				usersRepository.save(user);
				usersRepository.updateNoOfAttempts(0, user.getUserId());
				auditTrailService.setAuditTrail(Helper.getActiveUser(), user.getUserId(), Function.LOGIN.getName(),
						EnumData.LOGIN.getName());
				return ApiResponse.success(ResponseMessage.LOGIN_SUCCESS.getMessage(), responseDTO);
			} else if (user.getRecordStatus().equalsIgnoreCase(EnumData.LOCKED.getName())) {
				return ApiResponse.failure(ResponseMessage.USER_LOCKED.getMessage());
			} else if (user.getRecordStatus().equalsIgnoreCase(EnumData.DISABLED.getName())) {
				return ApiResponse.failure(ResponseMessage.USER_LOGIN_DISABLED.getMessage());
			}
		} catch (BadCredentialsException e) {
			log.error(e.getMessage(), e);
			if (passwordValidationService.updateNoOfAttempts(authenticationRequest.getUserId())) {
				return ApiResponse.failure(ResponseMessage.USER_LOCKED.getMessage());
			}
			return ApiResponse.failure(ResponseMessage.INVALID_CREDENTIALS.getMessage());
		} catch (UsernameNotFoundException e) {
			log.error(e.getMessage(), e);
			return ApiResponse.failure(ResponseMessage.USER_NOT_FOUND.getMessage());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return ApiResponse.failure(ResponseMessage.LOGIN_FAILED.getMessage());
		}
		return null;
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			log.error(e.getMessage());
			throw new Exception(ResponseMessage.USER_LOGIN_DISABLED.getMessage(), e);
		} catch (BadCredentialsException e) {
			log.error(e.getMessage());
			throw new BadCredentialsException(ResponseMessage.INVALID_CREDENTIALS.getMessage(), e);
		}
	}

	public ApiResponse<RefreshTokenDTO> refreshToken() {
		try {
			log.info("In getRefreshToken..");
			final UserDetails userDetails = userDetailsService.loadUserByUsername(Helper.getActiveUser());
			final String token = jwtTokenUtil.generateToken(userDetails);
			final String refreshToken = jwtTokenUtil.generateRefreshToken(userDetails);
			RefreshTokenDTO refreshTokenDTO = new RefreshTokenDTO();
			refreshTokenDTO.setToken(token);
			refreshTokenDTO.setRefreshToken(refreshToken);
			log.info("Return success from getRefreshToken");
			return ApiResponse.success(ResponseMessage.OPERATION_SUCCESS.getMessage(), refreshTokenDTO);
		} catch (Exception e) {
			log.error("In getRefreshToken exception ", e);
			return ApiResponse.failure(ResponseMessage.OPERATION_FAILED.getMessage());
		}
	}

}
