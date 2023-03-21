package com.wecodee.SpringBootPractice.usermanagement.repository;

import java.awt.print.Pageable;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wecodee.SpringBootPractice.admin.repository.BaseRepository;
import com.wecodee.SpringBootPractice.usermanagement.model.Role;

public interface RoleRepository extends BaseRepository<Role> {

	public Role getByRoleId(String roleId);

	@Transactional
	@Modifying
	@Query("update Role set approved=:approve where roleId=:roleId")
	public int approve(@Param("roleId") String roleId, @Param("approve") String approve);

	@Transactional
	@Modifying
	@Query("update Role set recordStatus=:'D' where roleId=:roleId")
	public int invalidateRole(@Param("roleId") String roleId);

	public Role getByRoleIdAndApproved(String roleId, Boolean approved);

	public Page<Role> findByPendingApproval(boolean pendingApproval, Pageable pageable);

	@Transactional
	@Modifying
	@Query("update Role set pendingApproval=:pendingApproval where roleId=:rid")
	public int updatePendingApproval(@Param("rid") String roleId, @Param("pendingApproval") boolean pendingApproval);

	public List<Role> getByRoleType(String roleType);

}
