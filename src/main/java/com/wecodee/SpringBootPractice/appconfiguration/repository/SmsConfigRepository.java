package com.wecodee.SpringBootPractice.appconfiguration.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wecodee.SpringBootPractice.admin.repository.BaseRepository;
import com.wecodee.SpringBootPractice.appconfiguration.model.SmsConfig;

public interface SmsConfigRepository extends BaseRepository<SmsConfig> {

	public SmsConfig getById(Integer id);

	@Transactional
	@Modifying
	@Query("update SmsConfig set approved=:approve where id=:id")
	public int approve(@Param("id") String id, @Param("approve") String approve);

	@Transactional
	@Modifying
	@Query("update SmsConfig set pendingApproval=:pendingApproval where id=:id")
	public int updatePendingApproval(@Param("id") Integer id, @Param("pendingApproval") boolean pendingApproval);

}
