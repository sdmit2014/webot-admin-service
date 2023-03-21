package com.wecodee.SpringBootPractice.admin.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wecodee.SpringBootPractice.admin.constant.EnumData;
import com.wecodee.SpringBootPractice.admin.model.AuditTrail;
import com.wecodee.SpringBootPractice.admin.repository.AuditTrailRepository;
import com.wecodee.SpringBootPractice.admin.util.Helper;

@Service
public class AuditTrailService {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private AuditTrailRepository auditTrailRepository;

	public void setAuditTrail(String loginUserId, String id, String screen, String action) {
		log.info("In audit trail service setAuditTrail method is executing");
		AuditTrail auditTrail = new AuditTrail();
		auditTrail.setUserId(loginUserId);
		auditTrail.setAction(action);
		auditTrail.setRecordId(id);
		auditTrail.setAction(action);

		auditTrail.setDateTime(Helper.getCurrentDate());
		if (action.equals(EnumData.APPROVE_ACTION.getName())) {
			auditTrail.setApprovedBy(null);
			auditTrail.setApprovedDate(Helper.getCurrentDateAndTime());
		}
	}

}
