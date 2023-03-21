package com.wecodee.SpringBootPractice.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wecodee.SpringBootPractice.admin.model.FunctionDataArchive;

public interface FunctionDataArchiveRepository extends JpaRepository<FunctionDataArchive, Integer> {

}
