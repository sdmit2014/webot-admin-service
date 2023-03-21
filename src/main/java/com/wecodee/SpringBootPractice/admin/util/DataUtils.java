package com.wecodee.SpringBootPractice.admin.util;

public interface DataUtils {

	static Double getDouble(int value) {
		return Double.valueOf(String.valueOf(value));
	}

}
