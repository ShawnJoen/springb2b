package com.spring.dto.admin;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.NotBlank;

public class AdminGroup {
	
	private short groupId = 0;
	
	@NotBlank(message="{10008}")//���������Ʋ���Ϊ��
	private String groupName;
	
	private byte status;
	private byte isDel;
	
	//�޸Ĺ����� �г�����Ȩ�޿ɹ�ѡ��
	private List<AdminRole> adminRoles;
	//�޸Ĺ����� �г���Ȩ���� ƥ�������Ȩ��
	private List<String> adminRoleAccess = new ArrayList<>();
	
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
	public byte getIsDel() {
		return isDel;
	}
	public void setIsDel(byte isDel) {
		this.isDel = isDel;
	}
	public List<AdminRole> getAdminRoles() {
		return adminRoles;
	}
	public void setAdminRoles(List<AdminRole> adminRoles) {
		this.adminRoles = adminRoles;
	}
	public List<String> getAdminRoleAccess() {
		return adminRoleAccess;
	}
	public void setAdminRoleAccess(List<String> adminRoleAccess) {
		this.adminRoleAccess = adminRoleAccess;
	}
}