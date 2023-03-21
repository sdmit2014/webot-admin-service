package com.wecodee.SpringBootPractice.appconfiguration.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.wecodee.SpringBootPractice.admin.model.AuditProperties;

@Entity
@Table(name = "WA_SMS_CONFIG")
public class SmsConfig extends AuditProperties {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private String userName;

	private String password;

	private String token;

	private String gatewayUrl;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getGatewayUrl() {
		return gatewayUrl;
	}

	public void setGatewayUrl(String gatewayUrl) {
		this.gatewayUrl = gatewayUrl;
	}

	@Override
	public String toString() {
		return "SmsConfig [id=" + id + ", userName=" + userName + ", password=" + password + ", token=" + token
				+ ", gatewayUrl=" + gatewayUrl + "]";
	}

}
