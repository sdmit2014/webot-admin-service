package com.wecodee.SpringBootPractice.login.dto;

public class LoginAuthenticationRequestDTO {

	private String userId;

	private String password;

	public LoginAuthenticationRequestDTO() {

	}

	public LoginAuthenticationRequestDTO(String userId, String password) {
		super();
		this.userId = userId;
		this.password = password;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "LoginAuthenticationRequestDto [userId=" + userId + ", password=" + password + "]";
	}
}
