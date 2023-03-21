package com.wecodee.SpringBootPractice.admin.repository;

import java.util.List;

import com.wecodee.SpringBootPractice.admin.model.Template;

public interface TemplateRepository extends BaseRepository<Template> {
	
	public List<Template> getByChannel(String channel);

}
