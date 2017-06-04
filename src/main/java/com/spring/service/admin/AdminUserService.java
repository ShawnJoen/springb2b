package com.spring.service.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import com.spring.model.admin.AdminRoleAccess;
import com.spring.model.admin.AdminUser;
import com.spring.model.admin.AdminUserAuthentication;

public interface AdminUserService {
	/*
	 * 创建管理员
	 * */
	Map<String, Object> createAdminUser(AdminUser adminUser) throws Exception;
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
	Map<String, Object> modifyPassword(AdminUserAuthentication adminUserAuthentication) throws Exception;
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
	List<HashMap<String,Object>> getAdminGroups();
	/*
	 * 修改管理员信息除了密码
	 * */
	Map<String, Object> modifyAdminUser(AdminUser adminUser) throws Exception;
	/*
	 * 修改管理员信息包括密码
	 * */
	Map<String, Object> modifyAdminUserAndPassword(AdminUser adminUser) throws Exception;
	/*
	 * 管理人员列表
	 * */
	List<AdminUser> getAdminUsers(AdminUser adminUser);
	
}