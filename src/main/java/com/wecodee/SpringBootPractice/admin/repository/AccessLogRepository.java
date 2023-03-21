package com.wecodee.SpringBootPractice.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wecodee.SpringBootPractice.admin.model.AccessLog;

@Repository
public interface AccessLogRepository extends JpaRepository<AccessLog, Integer> {

}
