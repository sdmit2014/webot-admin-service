package com.wecodee.SpringBootPractice.admin.requestDTO;

public class SearchCustomerDto {

	private String customerName;

	private String customerNumber;

	private String emailId;

	private String phoneNo;

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerNumber() {
		return customerNumber;
	}

	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	@Override
	public String toString() {
		return "SearchCustomerDto [customerName=" + customerName + ", customerNumber=" + customerNumber + ", emailId="
				+ emailId + ", phoneNo=" + phoneNo + "]";
	}

}
