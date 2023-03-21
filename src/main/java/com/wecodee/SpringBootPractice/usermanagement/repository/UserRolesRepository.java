package com.wecodee.SpringBootPractice.usermanagement.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wecodee.SpringBootPractice.admin.repository.BaseRepository;
import com.wecodee.SpringBootPractice.usermanagement.model.UserRole;

public interface UserRolesRepository extends BaseRepository<UserRole> {

	public List<UserRole> getByUserId(String userId);

	@Transactional
	@Modifying
	public int deleteByUserId(String userId);

	UserRole getByRoleIdAndUserId(String roleId, String userId);

	List<UserRole> getByRoleId(String roleId);

	@Query(value = "from UserRole where roleId in(select roleId from Role where approverType=:approverType)")
	List<UserRole> getByapproverType(@Param("approverType") String approverType);

	@Query(value = "select userId from UserRole where roleId in(select roleId from Role where approverType=:approverType)")
	List<String> getActiveBranchApprovers(@Param("approverType") String approverType);

	@Query(value = "select userId from UserRole where roleId in(select roleId from Role where approverType=:approverType) and userId!=:assignee")
	List<String> getActiveApprovers(@Param("approverType") String approverType, @Param("assignee") String assignee);

	@Query(value = "select userId from UserRole where roleId in(select roleId from Role where approverType=:'KO')")
	List<String> getActiveKycApprovers();
}
