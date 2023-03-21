package com.wecodee.SpringBootPractice.admin.responseDTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SmsLoginResponse {

	@JsonProperty("ID")
	private String ID;

	@JsonProperty("Name")
	private String Name;

	@JsonProperty("Token")
	private String Token;

	@JsonProperty("Timestamp")
	private String Timestamp;

	@JsonProperty("Duration")
	private int Duration;

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getToken() {
		return Token;
	}

	public void setToken(String token) {
		Token = token;
	}

	public String getTimestamp() {
		return Timestamp;
	}

	public void setTimestamp(String timestamp) {
		Timestamp = timestamp;
	}

	public int getDuration() {
		return Duration;
	}

	public void setDuration(int duration) {
		Duration = duration;
	}

	@Override
	public String toString() {
		return "SmsLoginResponse [ID=" + ID + ", Name=" + Name + ", Token=" + Token + ", Timestamp=" + Timestamp
				+ ", Duration=" + Duration + "]";
	}

}
