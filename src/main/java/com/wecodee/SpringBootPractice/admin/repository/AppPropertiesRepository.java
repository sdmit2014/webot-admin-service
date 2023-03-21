package com.wecodee.SpringBootPractice.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wecodee.SpringBootPractice.admin.model.AppProperties;

public interface AppPropertiesRepository extends JpaRepository<AppProperties, Integer> {
	
	public AppProperties getByPropertyName(String propertyName);


}
