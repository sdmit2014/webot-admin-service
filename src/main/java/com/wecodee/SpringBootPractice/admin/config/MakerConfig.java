package com.wecodee.SpringBootPractice.admin.config;

import org.springframework.stereotype.Service;

import com.wecodee.SpringBootPractice.admin.util.Helper;

@Service
public class MakerConfig {

	public boolean checkApprovers(int recordVersion, String createdBy, String lastUpdatedBy) {

		if (recordVersion == 1) {
			if (createdBy.equals(Helper.getActiveUser())) {
				return false;
			}
		} else if (recordVersion > 1) {
			if (lastUpdatedBy.equals(Helper.getActiveUser())) {
				return false;
			}
		}
		return true;
	}

}
