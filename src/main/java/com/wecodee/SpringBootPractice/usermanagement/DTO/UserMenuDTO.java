package com.wecodee.SpringBootPractice.usermanagement.DTO;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.wecodee.SpringBootPractice.admin.requestDTO.MenuDTO;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserMenuDTO {

	@JsonIgnore
	private int id;

	@JsonIgnore
	private String screenId;

	private String name;

	private String url;

	private String icon;

	private List<MenuDTO> children;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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

	public List<MenuDTO> getChildren() {
		return children;
	}

	public void setChildren(List<MenuDTO> children) {
		this.children = children;
	}

	@Override
	public String toString() {
		return "UserMenuDTO [id=" + id + ", screenId=" + screenId + ", name=" + name + ", url=" + url + ", icon=" + icon
				+ ", children=" + children + "]";
	}

}
