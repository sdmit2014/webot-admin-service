package com.wecodee.SpringBootPractice.appconfiguration.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.wecodee.SpringBootPractice.admin.model.AuditProperties;

@Entity
@Table(name = "WA_EMAIL_CONFIG")
public class EmailConfig extends AuditProperties {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "HOST", length = 50)
	private String host;

	@Column(name = "PORT", length = 50)
	private Integer port;

	private String emailId;

	private String password;

	private String fromEmail;

	@Column(name = "SSL_REQUIRED", length = 1)
	private Boolean sslRequired;

	private String fromName;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFromEmail() {
		return fromEmail;
	}

	public void setFromEmail(String fromEmail) {
		this.fromEmail = fromEmail;
	}

	public Boolean getSslRequired() {
		return sslRequired;
	}

	public void setSslRequired(Boolean sslRequired) {
		this.sslRequired = sslRequired;
	}

	public String getFromName() {
		return fromName;
	}

	public void setFromName(String fromName) {
		this.fromName = fromName;
	}

	@Override
	public String toString() {
		return "EmailConfig [id=" + id + ", host=" + host + ", port=" + port + ", emailId=" + emailId + ", password="
				+ password + ", fromEmail=" + fromEmail + ", sslRequired=" + sslRequired + ", fromName=" + fromName
				+ "]";
	}

}
