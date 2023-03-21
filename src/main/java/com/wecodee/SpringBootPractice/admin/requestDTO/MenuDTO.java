package com.wecodee.SpringBootPractice.admin.requestDTO;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class MenuDTO {

	@JsonIgnore
	private String screenId;

	private String name;

	private String url;

	private String icon;

	public String getScreenId() {
		return screenId;
	}

	public void setScreenId(String screenId) {
		this.screenId = screenId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

}
