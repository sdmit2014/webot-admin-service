package com.wecodee.SpringBootPractice.admin.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "BA_NOTIFICATION_TYPE_TAG")
public class TemplateTag {

	@Id
	private Long id;

	private String notificationType;

	private String tagName;

	private String tagDesc;

	private String tagPreviewValue;

	private Integer tagMaxLength;

	private String field;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNotificationType() {
		return notificationType;
	}

	public void setNotificationType(String notificationType) {
		this.notificationType = notificationType;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public String getTagDesc() {
		return tagDesc;
	}

	public void setTagDesc(String tagDesc) {
		this.tagDesc = tagDesc;
	}

	public String getTagPreviewValue() {
		return tagPreviewValue;
	}

	public void setTagPreviewValue(String tagPreviewValue) {
		this.tagPreviewValue = tagPreviewValue;
	}

	public Integer getTagMaxLength() {
		return tagMaxLength;
	}

	public void setTagMaxLength(Integer tagMaxLength) {
		this.tagMaxLength = tagMaxLength;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	@Override
	public String toString() {
		return "TemplateTag [id=" + id + ", notificationType=" + notificationType + ", tagName=" + tagName
				+ ", tagDesc=" + tagDesc + ", tagPreviewValue=" + tagPreviewValue + ", tagMaxLength=" + tagMaxLength
				+ ", field=" + field + "]";
	}

}
