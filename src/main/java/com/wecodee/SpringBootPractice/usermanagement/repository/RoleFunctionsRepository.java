package com.wecodee.SpringBootPractice.usermanagement.repository;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.wecodee.SpringBootPractice.admin.repository.BaseRepository;
import com.wecodee.SpringBootPractice.usermanagement.model.RoleFunctions;

public interface RoleFunctionsRepository extends BaseRepository<RoleFunctions> {

	public List<RoleFunctions> getByRoleId(String roleId);

	@Query("from RoleFunctions where roleId=:roleId order by id desc")
	public List<RoleFunctions> getAllRoleFunctionsOrderById(String roleId);

	public RoleFunctions getByRoleIdAndFunctionId(String roleId, String functionId);

	@Transactional
	@Modifying
	public int deleteByRoleId(String roleId);

	@Query("select functionId from RoleFunctions where roleId=:roleId and (viewAccess = 1 OR createAccess = 1 OR approveAccess = 1)")
	public List<String> getFunctionId(@Param("roleId") String roleId);
}
