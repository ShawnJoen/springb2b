package com.spring.service.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import com.spring.model.admin.AdminGroup;
import com.spring.model.admin.AdminRoleAccess;
import com.spring.model.admin.AdminUser;
import com.spring.model.admin.AdminUserAuthentication;
import com.spring.model.admin.OperationRecord;

public interface AdminUserService {
	/*
	 * 创建管理员
	 * */
	Map<String, Object> createAdminUser(AdminUser adminUser, Locale locale) throws Exception;
	/*
	 * 用账户查询存在此管理员与否
	 * */
	int hasAdminUserByUsername(String username);
	/*
	 * 用账户获取管理员信息
	 * */
	AdminUser getAdminUserByUsername(String username);
	/*
	 * 用账户获取管理员登入 账户,密码 修改密码等 比较老密码时用
	 * */
	AdminUser getAdminUserAuthentication(AdminUserAuthentication adminUserAuthentication);
	/*
	 * 修改管理员密码
	 * */
	Map<String, Object> modifyPassword(AdminUserAuthentication adminUserAuthentication, Locale locale) throws Exception;
	/*
	 * 设置管理员页面登入权限
	 * */
	void createAdminRoleAccess(AdminRoleAccess adminRoleAccess) throws Exception;
	/*
	 * 获取管理员页面登入权限列表
	 * */
	List<String> getAdminRoleAccessByUsername(String username);
	/*
	 * 获取管理组列表
	 * */
	List<AdminGroup> getAdminGroupSelectBox();
	/*
	 * 修改管理员信息除了密码
	 * */
	Map<String, Object> modifyAdminUser(AdminUser adminUser, Locale locale) throws Exception;
	/*
	 * 修改管理员信息包括密码
	 * */
	Map<String, Object> modifyAdminUserAndPassword(AdminUser adminUser, Locale locale) throws Exception;
	/*
	 * 管理人员列表
	 * */
	List<AdminUser> getAdminUsers(AdminUser adminUser);
	/*
	 * 删除管理账号
	 * */
	Map<String, Object> deleteAdminUser(AdminUser adminUser, Locale locale) throws Exception;
	/*
	 * 创建管理组
	 * */
	Map<String, Object> createAdminGroup(AdminGroup adminGroup, Locale locale) throws Exception;
	/*
	 * 管理组列表
	 * */
	List<AdminGroup> getAdminGroups();
	/*
	 * 管理组信息
	 * */
	AdminGroup getAdminGroup(int groupId);
	/*
	 * 修改管理组
	 * */
	Map<String, Object> modifyAdminGroup(AdminGroup adminGroup, Locale locale) throws Exception;
	/*
	 * 删除管理组
	 * */
	Map<String, Object> deleteAdminGroup(AdminGroup adminGroup, Locale locale) throws Exception;
	/*
	 * 创建操作记录
	 * */
	void createOperationRecord(String content, long createTime) throws Exception;
	/*
	 * 操作记录列表
	 * */
	List<OperationRecord> getOperationRecords(OperationRecord operationRecord);
	
}