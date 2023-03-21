package com.wecodee.SpringBootPractice.login.dto;

import java.util.List;

import com.wecodee.SpringBootPractice.usermanagement.model.UserRole;

public class LoginUserDTO {

	private String userId;
	private String firstName;
	private String lastName;
	private String emailId;
	private String phoneNumber;
	private String countryCode;
	private String password;
	private String imageData;
	private String userCategory;
	private String userBranch;
	private boolean administrator;
	private boolean requestor;
	private boolean approver;
	private boolean chgPassFlg;
	private List<UserRole> userRoles;
	private List<String> roleTypes;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getImageData() {
		return imageData;
	}

	public void setImageData(String imageData) {
		this.imageData = imageData;
	}

	public String getUserCategory() {
		return userCategory;
	}

	public void setUserCategory(String userCategory) {
		this.userCategory = userCategory;
	}

	public String getUserBranch() {
		return userBranch;
	}

	public void setUserBranch(String userBranch) {
		this.userBranch = userBranch;
	}

	public boolean isAdministrator() {
		return administrator;
	}

	public void setAdministrator(boolean administrator) {
		this.administrator = administrator;
	}

	public boolean isRequestor() {
		return requestor;
	}

	public void setRequestor(boolean requestor) {
		this.requestor = requestor;
	}

	public boolean isApprover() {
		return approver;
	}

	public void setApprover(boolean approver) {
		this.approver = approver;
	}

	public boolean isChgPassFlg() {
		return chgPassFlg;
	}

	public void setChgPassFlg(boolean chgPassFlg) {
		this.chgPassFlg = chgPassFlg;
	}

	public List<UserRole> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(List<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	public List<String> getRoleTypes() {
		return roleTypes;
	}

	public void setRoleTypes(List<String> roleTypes) {
		this.roleTypes = roleTypes;
	}

	@Override
	public String toString() {
		return "LoginUserDTO [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", emailId="
				+ emailId + ", phoneNumber=" + phoneNumber + ", countryCode=" + countryCode + ", password=" + password
				+ ", imageData=" + imageData + ", userCategory=" + userCategory + ", userBranch=" + userBranch
				+ ", administrator=" + administrator + ", requestor=" + requestor + ", approver=" + approver
				+ ", chgPassFlg=" + chgPassFlg + ", userRoles=" + userRoles + ", roleTypes=" + roleTypes + "]";
	}

}
