package com.wecodee.SpringBootPractice.admin.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wecodee.SpringBootPractice.admin.model.LookupMain;

public interface LookupMainRepository extends BaseRepository<LookupMain> {

	LookupMain getByType(String type);

	@Transactional
	@Modifying
	@Query("update LookupMain set pendingApproval=:pendingApproval where type=:type")
	public int updatePendingApproval(@Param("type") String type, @Param("pendingApproval") boolean pendingApproval);

	public LookupMain getByTypeAndApproved(String type, Boolean approved);
}
