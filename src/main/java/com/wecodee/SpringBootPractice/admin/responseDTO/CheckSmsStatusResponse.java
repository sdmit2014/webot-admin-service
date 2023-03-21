package com.wecodee.SpringBootPractice.admin.responseDTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CheckSmsStatusResponse {

	@JsonProperty("MessageID")
	private String MessageID;

	@JsonProperty("MSISDN")
	private String MSISDN;

	@JsonProperty("Status")
	private String Status;

	@JsonProperty("SubmissionDate")
	private String SubmissionDate;

	@JsonProperty("DeliveryDate")
	private String DeliveryDate;

	public String getMessageID() {
		return MessageID;
	}

	public void setMessageID(String messageID) {
		MessageID = messageID;
	}

	public String getMSISDN() {
		return MSISDN;
	}

	public void setMSISDN(String mSISDN) {
		MSISDN = mSISDN;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public String getSubmissionDate() {
		return SubmissionDate;
	}

	public void setSubmissionDate(String submissionDate) {
		SubmissionDate = submissionDate;
	}

	public String getDeliveryDate() {
		return DeliveryDate;
	}

	public void setDeliveryDate(String deliveryDate) {
		DeliveryDate = deliveryDate;
	}

	@Override
	public String toString() {
		return "CheckSmsStatusResponse [MessageID=" + MessageID + ", MSISDN=" + MSISDN + ", Status=" + Status
				+ ", SubmissionDate=" + SubmissionDate + ", DeliveryDate=" + DeliveryDate + "]";
	}

}
