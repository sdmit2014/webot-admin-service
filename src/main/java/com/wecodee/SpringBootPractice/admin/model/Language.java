package com.wecodee.SpringBootPractice.admin.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "BN_LANGUAGES")
public class Language {

	@Id
	private int id;

	private String language;

	private String name;

	private String languageDesc;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLanguageDesc() {
		return languageDesc;
	}

	public void setLanguageDesc(String languageDesc) {
		this.languageDesc = languageDesc;
	}

	@Override
	public String toString() {
		return "Language [id=" + id + ", language=" + language + ", name=" + name + ", languageDesc=" + languageDesc
				+ "]";
	}

}
