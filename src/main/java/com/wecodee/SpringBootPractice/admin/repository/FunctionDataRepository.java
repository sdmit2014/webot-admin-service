package com.wecodee.SpringBootPractice.admin.repository;

import java.awt.print.Pageable;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wecodee.SpringBootPractice.admin.model.FunctionData;

public interface FunctionDataRepository extends JpaRepository<FunctionData, Integer> {

	FunctionData getByFunctionIdAndPkey(String functionId, String pkey);

	FunctionData getByFunctionIdAndPkeyAndAction(String functionId, String pkey, String action);

	List<FunctionData> getByFunctionId(String functionId);

	Page<FunctionData> findByFunctionId(String functionId, Pageable pageable);

	List<FunctionData> findByFunctionId(String functionId);

	@Transactional
	@Modifying
	@Query("update FunctionData set status=:status where where functionId=:sid and pkey=:gid")
	int updateRecord(@Param("status") String status, @Param("sid") String screenId, @Param("gid") String groupId);

	@Transactional
	@Modifying
	@Query("delete FunctionData where functionId=:sid and pkey=:gid")
	int deleteByScreenIdAndPkey(@Param("sid") String screenId, @Param("gid") String groupId);

	@Transactional
	@Modifying
	@Query("delete FunctionData where functionId=:functionId and createdBy=:currentUser and pkey=:pkey")
	int deleteByFunctionIdAndCreatedByAndPkey(@Param("functionId") String functionId,
			@Param("currentUser") String createdBy, @Param("pkey") String pkey);

}
