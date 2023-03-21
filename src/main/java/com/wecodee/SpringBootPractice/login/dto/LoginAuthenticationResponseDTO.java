package com.wecodee.SpringBootPractice.login.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoginAuthenticationResponseDTO {

	private String token;

	private Boolean changePassword;

	private String userName;

	private String refreshToken;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Boolean getChangePassword() {
		return changePassword;
	}

	public void setChangePassword(Boolean changePassword) {
		this.changePassword = changePassword;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	@Override
	public String toString() {
		return "LoginAuthenticationResponseDTO [token=" + token + ", changePassword=" + changePassword + ", userName="
				+ userName + ", refreshToken=" + refreshToken + "]";
	}

}
