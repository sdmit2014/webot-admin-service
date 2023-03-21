package com.wecodee.SpringBootPractice.admin.responseDTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SendSmsResponse {

	@JsonProperty("MessageID")
	private String MessageID;

	@JsonProperty("MSISDN")
	private String MSISDN;

	@JsonProperty("SubmissionDate")
	private String SubmissionDate;

	@JsonProperty("Status")
	private String Status;

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

	public String getSubmissionDate() {
		return SubmissionDate;
	}

	public void setSubmissionDate(String submissionDate) {
		SubmissionDate = submissionDate;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	@Override
	public String toString() {
		return "SendSmsResponse [MessageID=" + MessageID + ", MSISDN=" + MSISDN + ", SubmissionDate=" + SubmissionDate
				+ ", Status=" + Status + "]";
	}

}
