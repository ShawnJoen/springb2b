package com.spring.vo.admin;

public class AdminRoleAccessVO {
	
	private int groupId;
	private String role = "";
	
	public AdminRoleAccessVO() {
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