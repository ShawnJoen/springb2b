package com.spring.dto.admin;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.validator.constraints.NotBlank;

public class AdminGroup {
	
	private int groupId = 0;
	@NotBlank(message="{10008}")//管理组名称不能为空
	private String groupName;
	private int status;
	private int isDel;
	
	//修改管理组 列出所有权限可供选择
	private List<AdminRole> adminRoles;
	//修改管理组 列出的权限中 匹配管理组权限
	private List<String> adminRoleAccess = new ArrayList<>();
	
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