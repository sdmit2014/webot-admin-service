package com.wecodee.SpringBootPractice.admin.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "BA_LOOKUP_MAIN")
public class LookupMain extends AuditProperties {

	@Id
	@Column(name = "TYPE")
	private String type;

	@Column(name = "LOOKUP_DESC")
	private String lookupDesc;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "TYPE", referencedColumnName = "TYPE")
	private List<LookupValues> lookupValues;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLookupDesc() {
		return lookupDesc;
	}

	public void setLookupDesc(String lookupDesc) {
		this.lookupDesc = lookupDesc;
	}

	public List<LookupValues> getLookupValues() {
		return lookupValues;
	}

	public void setLookupValues(List<LookupValues> lookupValues) {
		this.lookupValues = lookupValues;
	}

	@Override
	public String toString() {
		return "LookupMain [type=" + type + ", lookupDesc=" + lookupDesc + ", lookupValues=" + lookupValues + "]";
	}

}
