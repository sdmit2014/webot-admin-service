package com.wecodee.SpringBootPractice.admin.responseDTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SmsApiResponse<T> {

	@JsonProperty("ResponseCode")
	private int ResponseCode;

	@JsonProperty("ResponseDescription")
	private String ResponseDescription;

	@JsonProperty("ResponseData")
	private T ResponseData;

	public int getResponseCode() {
		return ResponseCode;
	}

	public void setResponseCode(int responseCode) {
		ResponseCode = responseCode;
	}

	public String getResponseDescription() {
		return ResponseDescription;
	}

	public void setResponseDescription(String responseDescription) {
		ResponseDescription = responseDescription;
	}

	public T getResponseData() {
		return ResponseData;
	}

	public void setResponseData(T responseData) {
		ResponseData = responseData;
	}

	@Override
	public String toString() {
		return "SmsApiResponse [ResponseCode=" + ResponseCode + ", ResponseDescription=" + ResponseDescription
				+ ", ResponseData=" + ResponseData + "]";
	}

}
