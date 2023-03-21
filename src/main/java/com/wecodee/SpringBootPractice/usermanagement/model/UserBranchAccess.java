package com.wecodee.SpringBootPractice.usermanagement.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "WA_USER_BRANCH_ACCESS")
public class UserBranchAccess {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "USER_ID")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private String userId;

	@Column(name = "BRANCH_ID", length = 3)
	private String branchId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getBranchId() {
		return branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	@Override
	public String toString() {
		return "UserBranchAccess [id=" + id + ", userId=" + userId + ", branchId=" + branchId + "]";
	}

}
