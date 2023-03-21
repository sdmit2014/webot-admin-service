package com.wecodee.SpringBootPractice.appconfiguration.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.wecodee.SpringBootPractice.admin.model.AuditProperties;

@Entity
@Table(name = "BA_SECURITY_PARAMETERS")
public class SecurityParameters extends AuditProperties {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private int pwdMinLength;
	private int pwdMaxLength;
	private int pwdMinNumChars;
	private int pwdMinSplChars;
	private int pwdMinLowerCaseChars;
	private int pwdMinUpperCaseChars;
	private int pwdResetDays;
	private int sessionTimeoutInMins;
	private int invalidPwdAttempts;
	private int sessionTimeoutWarningInMins;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPwdMinLength() {
		return pwdMinLength;
	}

	public void setPwdMinLength(int pwdMinLength) {
		this.pwdMinLength = pwdMinLength;
	}

	public int getPwdMaxLength() {
		return pwdMaxLength;
	}

	public void setPwdMaxLength(int pwdMaxLength) {
		this.pwdMaxLength = pwdMaxLength;
	}

	public int getPwdMinNumChars() {
		return pwdMinNumChars;
	}

	public void setPwdMinNumChars(int pwdMinNumChars) {
		this.pwdMinNumChars = pwdMinNumChars;
	}

	public int getPwdMinSplChars() {
		return pwdMinSplChars;
	}

	public void setPwdMinSplChars(int pwdMinSplChars) {
		this.pwdMinSplChars = pwdMinSplChars;
	}

	public int getPwdMinLowerCaseChars() {
		return pwdMinLowerCaseChars;
	}

	public void setPwdMinLowerCaseChars(int pwdMinLowerCaseChars) {
		this.pwdMinLowerCaseChars = pwdMinLowerCaseChars;
	}

	public int getPwdMinUpperCaseChars() {
		return pwdMinUpperCaseChars;
	}

	public void setPwdMinUpperCaseChars(int pwdMinUpperCaseChars) {
		this.pwdMinUpperCaseChars = pwdMinUpperCaseChars;
	}

	public int getPwdResetDays() {
		return pwdResetDays;
	}

	public void setPwdResetDays(int pwdResetDays) {
		this.pwdResetDays = pwdResetDays;
	}

	public int getSessionTimeoutInMins() {
		return sessionTimeoutInMins;
	}

	public void setSessionTimeoutInMins(int sessionTimeoutInMins) {
		this.sessionTimeoutInMins = sessionTimeoutInMins;
	}

	public int getInvalidPwdAttempts() {
		return invalidPwdAttempts;
	}

	public void setInvalidPwdAttempts(int invalidPwdAttempts) {
		this.invalidPwdAttempts = invalidPwdAttempts;
	}

	public int getSessionTimeoutWarningInMins() {
		return sessionTimeoutWarningInMins;
	}

	public void setSessionTimeoutWarningInMins(int sessionTimeoutWarningInMins) {
		this.sessionTimeoutWarningInMins = sessionTimeoutWarningInMins;
	}

	@Override
	public String toString() {
		return "SecurityParameters [id=" + id + ", pwdMinLength=" + pwdMinLength + ", pwdMaxLength=" + pwdMaxLength
				+ ", pwdMinNumChars=" + pwdMinNumChars + ", pwdMinSplChars=" + pwdMinSplChars
				+ ", pwdMinLowerCaseChars=" + pwdMinLowerCaseChars + ", pwdMinUpperCaseChars=" + pwdMinUpperCaseChars
				+ ", pwdResetDays=" + pwdResetDays + ", sessionTimeoutInMins=" + sessionTimeoutInMins
				+ ", invalidPwdAttempts=" + invalidPwdAttempts + ", sessionTimeoutWarningInMins="
				+ sessionTimeoutWarningInMins + "]";
	}

}
