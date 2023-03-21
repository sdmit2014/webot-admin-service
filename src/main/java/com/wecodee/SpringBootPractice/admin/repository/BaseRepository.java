package com.wecodee.SpringBootPractice.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseRepository<T> extends JpaRepository<T, String> {

	@Query("SELECT count(1) FROM #{#entityName} WHERE pendingApproval=false")
	public <T> Integer getApprovedRecordsCount();

	@Query("SELECT count(1) FROM #{#entityName} WHERE pendingApproval=true")
	public <T> Integer getUnapprovedRecordsCount();

}
