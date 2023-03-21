package com.wecodee.SpringBootPractice.appconfiguration.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "BA_CBS_DATA_PROCEDURE")
public class CbsDataProcedure {

	@Id
	private String dataName;

	private String procedureName;

	public String getDataName() {
		return dataName;
	}

	public void setDataName(String dataName) {
		this.dataName = dataName;
	}

	public String getProcedureName() {
		return procedureName;
	}

	public void setProcedureName(String procedureName) {
		this.procedureName = procedureName;
	}

	@Override
	public String toString() {
		return "CbsDataProcedure [dataName=" + dataName + ", procedureName=" + procedureName + "]";
	}

}
