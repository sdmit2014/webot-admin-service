package com.wecodee.SpringBootPractice.login.service;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.wecodee.SpringBootPractice.admin.config.JwtRequestFilter;
import com.wecodee.SpringBootPractice.admin.constant.EnumData;
import com.wecodee.SpringBootPractice.admin.constant.ResponseMessage;
import com.wecodee.SpringBootPractice.admin.repository.AppPropertiesRepository;
import com.wecodee.SpringBootPractice.admin.responseDTO.ApiResponse;
import com.wecodee.SpringBootPractice.admin.util.Helper;
import com.wecodee.SpringBootPractice.common.service.PasswordValidationService;
import com.wecodee.SpringBootPractice.login.dto.AdminResetPasswordDTO;
import com.wecodee.SpringBootPractice.login.dto.ChangePasswordDTO;
import com.wecodee.SpringBootPractice.login.dto.ResetPasswordRequestDTO;
import com.wecodee.SpringBootPractice.usermanagement.model.User;
import com.wecodee.SpringBootPractice.usermanagement.repository.UsersRepository;

import net.minidev.json.JSONObject;

@Service
public class LoginService {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UsersRepository bmUsersRepository;

	@Autowired
	private BCryptPasswordEncoder encoder;

	@Autowired
	private PasswordValidationService passwordValidationService;

	@Autowired
	private AppPropertiesRepository appPropertiesRepository;

	// change password
	public ApiResponse<String> changePasswordService(ChangePasswordDTO changePasswordDTO) {
		String loggedInUserId = JwtRequestFilter.loggedInUserId;
		System.out.println("loggedInUserId = " + loggedInUserId);
		try {
			String encodedNewPassword = encoder.encode(changePasswordDTO.getNewPassword());
			User dbUser = bmUsersRepository.getByUserId(loggedInUserId);
			if (dbUser != null) {
				if (encoder.matches(changePasswordDTO.getOldPassword(), dbUser.getPassword())) {
					// validate password
					ApiResponse<String> response = passwordValidationService
							.passwordValidate(changePasswordDTO.getNewPassword());
					if (response.getStatus() == 0) {
						return ApiResponse.failure(response.getMessage());
					}
					int res = bmUsersRepository.changePassword(loggedInUserId, encodedNewPassword,
							Helper.getCurrentDateTime());
					if (res == 1) {
						log.info("password updated successfully");
						return ApiResponse.success(ResponseMessage.PASSWORD_UPDATE_SUCCESS.getMessage(), null);
					} else {
						log.info("failed to update password");
						return ApiResponse.failure(ResponseMessage.PASSWORD_UPDATE_FAILED.getMessage());
					}
				} else {
					log.info("password not matched");
					return ApiResponse.failure(ResponseMessage.PASSWORD_MISMATCH.getMessage());

				}
			} else {
				log.info("user not found");
				return ApiResponse.failure(ResponseMessage.INVALID_USER.getMessage());
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			return ApiResponse.failure(ResponseMessage.PASSWORD_UPDATE_FAILED.getMessage());
		}
	}

	// reset password
	public ApiResponse<String> resetPasswordService(ResetPasswordRequestDTO resetPasswordRequest) {
		try {
			JSONObject jsonObject = new JSONObject();
			User dbBmUser = bmUsersRepository.getByUserId(resetPasswordRequest.getUserId());
			log.info("dbBmUser->" + dbBmUser);
			if (dbBmUser != null) {

				if (resetPasswordRequest.getEmail().matches(dbBmUser.getEmailId())) {
					CharSequence uuid = (String) UUID.randomUUID().toString().subSequence(0, 8);
					String randomPass = (String) uuid;
					String encryptedPass = encoder.encode(randomPass);
					int result = bmUsersRepository.resetPassword(encryptedPass, dbBmUser.getUserId());
//					if (result > 0) {
//						EmailTemplate emailTemplate = emailTemplateRepository
//								.getEmailTemplate(EnumData.RESETPASSWORD.getName());
//						log.info("emailTemplate->" + emailTemplate);
//						String msg = resetpassMailContent(dbBmUser, randomPass, emailTemplate);
//						jsonObject = emailService.sendMessage(null, dbBmUser.getEmailId(), emailTemplate.getDefaultCc(),
//								emailTemplate.getDefaultBcc(), emailTemplate.getEmailSubject(), msg,
//								emailTemplate.getThemeId());
//						boolean status = (boolean) jsonObject.get("status");
//						if (!status) {
//							return ApiResponse.failure(ResponseMessage.RESET_PASSWORD_FAILED.getMessage());
//						}
//						log.info("After sending message");
//					}
//                    int result = bmUsersRepository.resetPassword(encryptedPass, dbBmUser.getUserId());
					if (result > 0) {
						var resource = new ClassPathResource("/ResetPassword.html");
						String mailBody = IOUtils.toString(resource.getInputStream(), StandardCharsets.UTF_8);
//                        String msg = resetpassMailContent(dbBmUser, randomPass, mailBody);
//                        jsonObject = emailService.sendMessage(null, dbBmUser.getEmailId(), null, null, "Webot - Reset Password", msg, null);
						boolean status = (boolean) jsonObject.get("status");
						if (!status) {
							return ApiResponse.failure(ResponseMessage.RESET_PASSWORD_FAILED.getMessage());
						}
						log.info("After sending message");
					}
					return ApiResponse.success(ResponseMessage.RESET_PASSWORD_SUCCESS.getMessage(), null);

				} else {
					log.info("User ID and Registered email id does not match");
					return ApiResponse.failure(ResponseMessage.USER_EMAIL_MISMATCH.getMessage());
				}
			} else {
				log.info("Invalid User Id");
				return ApiResponse.failure(ResponseMessage.INVALID_USER.getMessage());
			}

		} catch (Exception e) {
			log.error(e.getMessage());
			return ApiResponse.failure(ResponseMessage.RESET_PASSWORD_FAILED.getMessage());
		}
	}

//	public String resetpassMailContent(User dbUser, String password, EmailTemplate emailTemplate) {
//	String content = "";
//	try {
//
////		content = emailTemplate.getEmailBody().toString();
//		content = content.replace("[[USERNAME]]", dbUser.getFirstName() + " " + dbUser.getLastName())
//				.replace("[[NEWPASSWORD]]", password);
//		log.info(content);
//
//	} catch (Exception e) {
//		log.error(e.getMessage());
//	}
//	return content;
//}

	public String resetpassMailContent(User dbUser, String password, String mailBody) {
		String content = "";
		try {

			content = mailBody;
			content = content.replace("[[USERNAME]]", dbUser.getFirstName() + " " + dbUser.getLastName())
					.replace("[[NEWPASSWORD]]", password);
			log.info(content);

		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return content;
	}

	// reset password
	public ApiResponse<User> adminUnlockService(AdminResetPasswordDTO adminResetPasswordDTO) {
		try {

			if (!adminResetPasswordDTO.getNewPassword().equals(adminResetPasswordDTO.getConfirmPassword())) {
				return ApiResponse.failure(ResponseMessage.NEW_PASS_AND_CONFRM_PASS.getMessage());
			}
			User dbBmUser = bmUsersRepository.getByUserId(adminResetPasswordDTO.getUserId());
			if (dbBmUser != null) {
				dbBmUser.setRecordStatus(EnumData.ACTIVE.getName());
				dbBmUser.setNoOfAttempts(0);
				dbBmUser.setChgPassFlg(false);
				int result = bmUsersRepository.resetPassword(encoder.encode(adminResetPasswordDTO.getNewPassword()),
						dbBmUser.getUserId());
				if (result > 0) {
					return ApiResponse.success(ResponseMessage.ADMIN_UNLOCKED_USER_SUCCESS.getMessage(), dbBmUser);
				}
				return ApiResponse.failure(ResponseMessage.USER_UNLOCKED_FAILED.getMessage());
			} else {
				return ApiResponse.failure(ResponseMessage.USER_NOT_FOUND.getMessage());
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			return ApiResponse.failure(ResponseMessage.OPERATION_FAILED.getMessage());
		}
	}

}
