package com.wecodee.SpringBootPractice.usermanagement.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.wecodee.SpringBootPractice.admin.model.AuditProperties;

@Entity
@Table(name = "WA_USERS")
public class User extends AuditProperties {

	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Id
	@Column(name = "USER_ID", length = 50)
	private String userId;

	@Column(name = "FIRST_NAME")
	private String firstName;

	@Column(name = "LAST_NAME")
	private String lastName;

	@Column(name = "EMAIL_ID")
	private String emailId;

	@Column(name = "PHONE_NUMBER")
	private String phoneNumber;

	@Column(name = "COUNTRY_CODE")
	private String countryCode;

	@Column(name = "PASSWORD")
	private String password;

	@Column(name = "IMAGE_DATA")
	private String imageData;

	@Column(name = "HOME_BRANCH")
	private String homeBranch;

	@Column(name = "CHG_PASS_FLG")
	private boolean chgPassFlg;

	@Column(name = "NO_OF_ATTEMPTS")
	private int noOfAttempts;

	@Column(name = "VALIDATE_DATE")
	private Date validateDate;

	@Column(name = "LOGIN_STATUS")
	private boolean loginStatus;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	@JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")
	private List<UserRole> userRoles;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	@JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")
	private List<UserBranchAccess> userBranchAccess;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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

	public String getHomeBranch() {
		return homeBranch;
	}

	public void setHomeBranch(String homeBranch) {
		this.homeBranch = homeBranch;
	}

	public boolean isChgPassFlg() {
		return chgPassFlg;
	}

	public void setChgPassFlg(boolean chgPassFlg) {
		this.chgPassFlg = chgPassFlg;
	}

	public int getNoOfAttempts() {
		return noOfAttempts;
	}

	public void setNoOfAttempts(int noOfAttempts) {
		this.noOfAttempts = noOfAttempts;
	}

	public Date getValidateDate() {
		return validateDate;
	}

	public void setValidateDate(Date validateDate) {
		this.validateDate = validateDate;
	}

	public boolean isLoginStatus() {
		return loginStatus;
	}

	public void setLoginStatus(boolean loginStatus) {
		this.loginStatus = loginStatus;
	}

	public List<UserRole> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(List<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	public List<UserBranchAccess> getUserBranchAccess() {
		return userBranchAccess;
	}

	public void setUserBranchAccess(List<UserBranchAccess> userBranchAccess) {
		this.userBranchAccess = userBranchAccess;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", emailId=" + emailId + ", phoneNumber=" + phoneNumber + ", countryCode=" + countryCode
				+ ", password=" + password + ", imageData=" + imageData + ", homeBranch=" + homeBranch + ", chgPassFlg="
				+ chgPassFlg + ", noOfAttempts=" + noOfAttempts + ", validateDate=" + validateDate + ", loginStatus="
				+ loginStatus + ", userRoles=" + userRoles + ", userBranchAccess=" + userBranchAccess + "]";
	}

}
