package com.wecodee.SpringBootPractice.admin.requestDTO;

public class UploadFileDto {

	private String customerList;

	public String getCustomerList() {
		return customerList;
	}

	public void setCustomerList(String customerList) {
		this.customerList = customerList;
	}

	@Override
	public String toString() {
		return "UploadFileDto [customerList=" + customerList + "]";
	}

}
