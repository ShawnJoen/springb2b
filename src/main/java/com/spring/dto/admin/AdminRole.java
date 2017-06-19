package com.spring.dto.admin;

import java.util.List;

public class AdminRole {

	private int roleId;
	private String pageTree = "";
	private String pageName;
	private String roleCode;
	private String pageURI;
	private int pageDeep = 0;
	private int childCount = 0;
	private int sort;
	private int isDel;
	
	//被选中menu
	private boolean selected;
	
	private List<AdminRole> adminRoles;
	
	//获取已授权的权限Menu列表时
	private int groupId;
	
	public AdminRole() {
		super();
	}
	public AdminRole(int pageDeep) {
		super();
		this.pageDeep = pageDeep;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public String getPageTree() {
		return pageTree;
	}
	public void setPageTree(String pageTree) {
		this.pageTree = pageTree;
	}
	public String getPageName() {
		return pageName;
	}
	public void setPageName(String pageName) {
		this.pageName = pageName;
	}
	public String getRoleCode() {
		return roleCode;
	}
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	public String getPageURI() {
		return pageURI;
	}
	public void setPageURI(String pageURI) {
		this.pageURI = pageURI;
	}
	public int getPageDeep() {
		return pageDeep;
	}
	public void setPageDeep(int pageDeep) {
		this.pageDeep = pageDeep;
	}
	public int getChildCount() {
		return childCount;
	}
	public void setChildCount(int childCount) {
		this.childCount = childCount;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	public int getIsDel() {
		return isDel;
	}
	public void setIsDel(int isDel) {
		this.isDel = isDel;
	}
	public boolean isSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	public List<AdminRole> getAdminRoles() {
		return adminRoles;
	}
	public void setAdminRoles(List<AdminRole> adminRoles) {
		this.adminRoles = adminRoles;
	}
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	
}