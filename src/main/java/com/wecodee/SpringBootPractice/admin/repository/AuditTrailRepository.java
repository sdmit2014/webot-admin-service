package com.wecodee.SpringBootPractice.admin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wecodee.SpringBootPractice.admin.model.AuditTrail;

public interface AuditTrailRepository extends JpaRepository<AuditTrail, Integer> {

	public List<AuditTrail> getByUserId(String userId);
}
