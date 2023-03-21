package com.wecodee.SpringBootPractice.appconfiguration.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.wecodee.SpringBootPractice.admin.repository.BaseRepository;
import com.wecodee.SpringBootPractice.appconfiguration.model.SecurityParameters;

@Repository
public interface SecurityParameterRepository extends BaseRepository<SecurityParameters> {

	@Transactional
	@Modifying
	@Query("delete from SecurityParameters where id=:id")
	int deleteRecords(@Param("id") String id);

	@Transactional
	@Modifying
	@Query("update SecurityParameters set pendingApproval=:pendingApproval where id=:id")
	public int updatePendingApproval(@Param("id") int id, @Param("pendingApproval") boolean pendingApproval);

	public SecurityParameters getByIdAndApproved(String id, Boolean approved);

}
