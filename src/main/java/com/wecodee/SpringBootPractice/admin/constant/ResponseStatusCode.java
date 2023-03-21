package com.wecodee.SpringBootPractice.admin.constant;

public enum ResponseStatusCode {
	
	SUCCESS(1), FAILED(0) , WARNING(2);

	private int status;

	ResponseStatusCode(int status) {
		this.status = status;
	}

	public int getStatus() {
		return status;
	}


}
