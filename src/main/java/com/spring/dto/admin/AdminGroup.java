package com.spring.dto.admin;

import org.hibernate.validator.constraints.NotBlank;

public class AdminGroup {
	
	private int groupId = 0;
	@NotBlank(message="{10008}")//管理组名称不能为空
	private String groupName;
	private int status;
	private int isDel;
	
	public AdminGroup() {
		super();
	}
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getIsDel() {
		return isDel;
	}
	public void setIsDel(int isDel) {
		this.isDel = isDel;
	}
}