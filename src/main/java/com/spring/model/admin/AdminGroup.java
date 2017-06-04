package com.spring.model.admin;

import org.hibernate.validator.constraints.NotBlank;

public class AdminGroup {
	
	private short groupId;
	
	@NotBlank(message="���������Ʋ���Ϊ��")
	private String groupName;
	
	private byte status;
	
	public AdminGroup() {
		super();
	}
	public short getGroupId() {
		return groupId;
	}
	public void setGroupId(short groupId) {
		this.groupId = groupId;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public byte getStatus() {
		return status;
	}
	public void setStatus(byte status) {
		this.status = status;
	}
}