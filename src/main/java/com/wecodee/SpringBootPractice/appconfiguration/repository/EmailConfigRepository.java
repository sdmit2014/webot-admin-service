package com.wecodee.SpringBootPractice.appconfiguration.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.wecodee.SpringBootPractice.admin.repository.BaseRepository;
import com.wecodee.SpringBootPractice.appconfiguration.model.EmailConfig;

@Repository
public interface EmailConfigRepository extends BaseRepository<EmailConfig>{
	
	public EmailConfig getById(int id);

	@Transactional
	@Modifying
	@Query("update EmailConfig set approved=:approve where id=:id")
	public int approve(@Param("id") Integer id, @Param("approve") String approve);

	public EmailConfig getByIdAndApproved(Integer id, Boolean approved);

	@Transactional
	@Modifying
	@Query("update EmailConfig set pendingApproval=:pendingApproval where id=:id")
	public int updatePendingApproval(@Param("id") Integer id, @Param("pendingApproval") boolean pendingApproval);


}
