package com.wecodee.SpringBootPractice.login.dto;

public class AdminResetPasswordDTO {

	private String newPassword;

	private String confirmPassword;

	private String userId;

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "AdminResetPasswordDTO [newPassword=" + newPassword + ", confirmPassword=" + confirmPassword
				+ ", userId=" + userId + "]";
	}

}
