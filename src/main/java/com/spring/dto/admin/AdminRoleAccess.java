package com.spring.dto.admin;

import java.util.List;

public class AdminRoleAccess {
	
	private int groupId;
	private String role = "";
	
	public AdminRoleAccess() {
		super();
	}
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
}