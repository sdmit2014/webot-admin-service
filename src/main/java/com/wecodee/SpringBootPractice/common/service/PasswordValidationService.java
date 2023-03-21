package com.wecodee.SpringBootPractice.common.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.wecodee.SpringBootPractice.admin.constant.EnumData;
import com.wecodee.SpringBootPractice.admin.constant.ResponseMessage;
import com.wecodee.SpringBootPractice.admin.responseDTO.ApiResponse;
import com.wecodee.SpringBootPractice.appconfiguration.model.SecurityParameters;
import com.wecodee.SpringBootPractice.appconfiguration.repository.SecurityParameterRepository;
import com.wecodee.SpringBootPractice.usermanagement.model.User;
import com.wecodee.SpringBootPractice.usermanagement.repository.UsersRepository;

public class PasswordValidationService {

	@Autowired
	private SecurityParameterRepository securityParameterRepository;

	@Autowired
	private UsersRepository usersRepository;

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	public ApiResponse<String> passwordValidate(String password) {
		log.info("password validate method is executing");
		SecurityParameters securityParameters = securityParameterRepository.findAll().get(0);
		try {
			if (password.length() <= securityParameters.getPwdMinLength()) {
				return ApiResponse.failure("Password Minimum Length Should Be " + securityParameters.getPwdMinLength());
			}
			if (password.length() >= securityParameters.getPwdMaxLength()) {
				return ApiResponse.failure("Password Maximum Length Should Be " + securityParameters.getPwdMaxLength());
			}
			if (countSpecialCharacters(password) < securityParameters.getPwdMinSplChars()) {
				return ApiResponse
						.failure("Special Character Length Should Be " + securityParameters.getPwdMinSplChars());
			}
			if (countNumaricCharecters(password) < securityParameters.getPwdMinNumChars()) {
				return ApiResponse
						.failure("Numeric Character Length Should Be " + securityParameters.getPwdMinNumChars());
			}
			if (countUpperCaseCharacters(password) < securityParameters.getPwdMinUpperCaseChars()) {
				return ApiResponse.failure(
						"Upper Case Character Length Should Be " + securityParameters.getPwdMinUpperCaseChars());
			}
			if (countLowerCaseCharacter(password) < securityParameters.getPwdMinLowerCaseChars()) {
				return ApiResponse.failure(
						"Lower Case Character Length Should Be " + securityParameters.getPwdMinLowerCaseChars());
			}
			return ApiResponse.success("Password Validation Success", "");

		} catch (Exception e) {
			log.error("In passwordValidate exception", e);
			return ApiResponse.failure(ResponseMessage.OPERATION_FAILED.getMessage());
		}
	}

	public int countSpecialCharacters(String password) {
		int specialCharactersCount = 0;
		Pattern pattern = Pattern.compile("[$&+,:;=?@#|'<>.-^*()%!]");
		Matcher matcher = pattern.matcher(password);

		while (matcher.find()) {
			specialCharactersCount++;
		}
		log.info("specialCharactersCount :" + specialCharactersCount);
		return specialCharactersCount;
	}

	public int countNumaricCharecters(String password) {
		int numaricCharectersCount = 0;
		Pattern pattern = Pattern.compile("[0-9]");
		Matcher matcher = pattern.matcher(password);
		while (matcher.find()) {
			numaricCharectersCount++;
		}
		log.info("numaricCharectersCount :" + numaricCharectersCount);
		return numaricCharectersCount;
	}

	public int countUpperCaseCharacters(String password) {
		int upperCaseCharactersCount = 0;
		Pattern pattern = Pattern.compile("[A-Z]");
		Matcher matcher = pattern.matcher(password);
		while (matcher.find()) {
			upperCaseCharactersCount++;
		}
		log.info("upperCaseCharactersCount :" + upperCaseCharactersCount);
		return upperCaseCharactersCount;
	}

	public int countLowerCaseCharacter(String password) {
		int lowerCaseCharacterCount = 0;
		Pattern pattern = Pattern.compile("[a-z]");
		Matcher matcher = pattern.matcher(password);
		while (matcher.find()) {
			lowerCaseCharacterCount++;
		}
		log.info("lowerCaseCharacterCount :" + lowerCaseCharacterCount);
		return lowerCaseCharacterCount;
	}

	public Boolean updateNoOfAttempts(String userName) {
		SecurityParameters securityParameters = securityParameterRepository.findAll().get(0);
		User user = usersRepository.getByUserId(userName);
		int noOfAttemptCount = user.getNoOfAttempts();
		Boolean temp = false;
		if (user.getNoOfAttempts() >= securityParameters.getInvalidPwdAttempts()) {
			user.setRecordStatus(EnumData.LOCKED.getName());
			temp = true;
		} else {
			noOfAttemptCount = user.getNoOfAttempts();
			user.setNoOfAttempts(noOfAttemptCount + 1);
		}
		usersRepository.save(user);
		return temp;
	}

}
