package com.wecodee.SpringBootPractice.admin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wecodee.SpringBootPractice.admin.model.LookupValuesAll;

public interface LookupValuesAllRepository extends JpaRepository<LookupValuesAll, String> {

	List<LookupValuesAll> getByType(String type);

	List<LookupValuesAll> getByTypeAndValue(String type, String value);

	@Query("select name from LookupValuesAll where type=:type and value=:value")
	String getNameByTypeAndValue(@Param("type") String type, @Param("value") String value);

}
