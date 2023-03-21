package com.wecodee.SpringBootPractice.admin.model;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "BA_ACCESS_LOG")
public class AccessLog {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String userId;

	private String ipAddress;

	private Date inTime;

	private Date outTime;

	private String reqMethod;

	private String reqUrl;

	private String reqParameters;

	private String reqBody;

	private int resStatus;

	private String resMsg;

	private String resBody;

	private int httpStatus;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public Date getInTime() {
		return inTime;
	}

	public void setInTime(Date inTime) {
		this.inTime = inTime;
	}

	public Date getOutTime() {
		return outTime;
	}

	public void setOutTime(Date outTime) {
		this.outTime = outTime;
	}

	public String getReqMethod() {
		return reqMethod;
	}

	public void setReqMethod(String reqMethod) {
		this.reqMethod = reqMethod;
	}

	public String getReqUrl() {
		return reqUrl;
	}

	public void setReqUrl(String reqUrl) {
		this.reqUrl = reqUrl;
	}

	public String getReqParameters() {
		return reqParameters;
	}

	public void setReqParameters(String reqParameters) {
		this.reqParameters = reqParameters;
	}

	public String getReqBody() {
		return reqBody;
	}

	public void setReqBody(String reqBody) {
		this.reqBody = reqBody;
	}

	public int getResStatus() {
		return resStatus;
	}

	public void setResStatus(int resStatus) {
		this.resStatus = resStatus;
	}

	public String getResMsg() {
		return resMsg;
	}

	public void setResMsg(String resMsg) {
		this.resMsg = resMsg;
	}

	public String getResBody() {
		return resBody;
	}

	public void setResBody(String resBody) {
		this.resBody = resBody;
	}

	public int getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(int httpStatus) {
		this.httpStatus = httpStatus;
	}

	@Override
	public String toString() {
		return "AccessLog [id=" + id + ", userId=" + userId + ", ipAddress=" + ipAddress + ", inTime=" + inTime
				+ ", outTime=" + outTime + ", reqMethod=" + reqMethod + ", reqUrl=" + reqUrl + ", reqParameters="
				+ reqParameters + ", reqBody=" + reqBody + ", resStatus=" + resStatus + ", resMsg=" + resMsg
				+ ", resBody=" + resBody + ", httpStatus=" + httpStatus + "]";
	}

}
