package com.wecodee.SpringBootPractice.admin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wecodee.SpringBootPractice.admin.model.AppFunctions;

public interface AppFunctionsRepository extends BaseRepository<AppFunctions> {

	@Query("FROM AppFunctions WHERE parentFunctionId =functionId ORDER BY id ")
	public List<AppFunctions> getParentIdss();

	@Query("from AppFunctions where functionId=:fid order by id")
	public AppFunctions getFunctionByFunctionId(@Param("fid") String functionId);

	@Query("SELECT functionId FROM AppFunctions where parentFunctionId !=functionId and parentFunctionId =:pid order by id")
	public List<String> getChildScreens(@Param("pid") String parentFunctionId);

	@Query("FROM AppFunctions where parentFunctionId !=functionId or standalone=1 ORDER BY id")
	public List<AppFunctions> getAllChildScreens();

	List<AppFunctions> getByRoleType(String roletype);

}
