package com.wecodee.SpringBootPractice.admin.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "BN_TEMPLATES")
public class Template {

	@Id
	private int id;

	private String channel;

	private String templateId;

	private String templateDesc;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	public String getTemplateDesc() {
		return templateDesc;
	}

	public void setTemplateDesc(String templateDesc) {
		this.templateDesc = templateDesc;
	}

	@Override
	public String toString() {
		return "Template [id=" + id + ", channel=" + channel + ", templateId=" + templateId + ", templateDesc="
				+ templateDesc + "]";
	}

}
