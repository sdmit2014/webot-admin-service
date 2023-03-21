package com.wecodee.SpringBootPractice.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wecodee.SpringBootPractice.admin.model.LookupValues;

public interface LookUpRepository extends JpaRepository<LookupValues, Long> {

	LookupValues getByTypeAndValue(String type, String value);
}
