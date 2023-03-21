package com.wecodee.SpringBootPractice.admin.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "BA_APP_PROPERTIES")
public class AppProperties {

	@Id
	// @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	// @JsonIgnore
	private int id;

	private String propertyName;

	private String propertyValue;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	public String getPropertyValue() {
		return propertyValue;
	}

	public void setPropertyValue(String propertyValue) {
		this.propertyValue = propertyValue;
	}

	@Override
	public String toString() {
		return "AppProperties [id=" + id + ", propertyName=" + propertyName + ", propertyValue=" + propertyValue + "]";
	}

}
