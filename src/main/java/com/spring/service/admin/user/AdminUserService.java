package com.spring.service.admin.user;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import com.spring.vo.admin.AdminGroupVO;
import com.spring.vo.admin.AdminRoleVO;
import com.spring.vo.admin.AdminRoleAccessVO;
import com.spring.vo.admin.AdminUserVO;
import com.spring.vo.admin.AdminUserAuthenticationVO;

public interface AdminUserService {
	/*
	 * 创建管理员
	 * */
	Map<String, Object> createAdminUser(AdminUserVO adminUserVO, Locale locale) throws Exception;
	/*
	 * 用账户查询存在此管理员与否
	 * */
	int hasAdminUserByUsername(String username);
	/*
	 * 用账户获取管理员信息
	 * */
	AdminUserVO getAdminUserByUsername(String username);
	/*
	 * 用账户获取管理组编号
	 * */
	int getGroupIdByUsername(String username);
	/*
	 * 用账户获取管理员登入 账户,密码 修改密码等 比较老密码时用
	 * */
	AdminUserVO getAdminUserAuthentication(AdminUserAuthenticationVO adminUserAuthenticationVO);
	/*
	 * 修改管理员密码
	 * */
	Map<String, Object> modifyPassword(AdminUserAuthenticationVO adminUserAuthenticationVO, Locale locale) throws Exception;
	/*
	 * 设置管理员页面登入权限
	 * */
	void createAdminRoleAccess(List<AdminRoleAccessVO> adminRoleAccessVO) throws Exception;
	/*
	 * 获取管理员页面登入权限列表
	 * */
	List<String> getAdminRoleAccessByGroupId(int groupId);
	/*
	 * 删除指定管理组权限
	 * */
	void deleteAdminRoleAccess(int groupId) throws Exception;
	/*
	 * 获取管理组列表
	 * */
	List<AdminGroupVO> getAdminGroupSelectBox();
	/*
	 * 修改管理员信息除了密码
	 * */
	Map<String, Object> modifyAdminUser(AdminUserVO adminUserVO, Locale locale) throws Exception;
	/*
	 * 修改管理员信息包括密码
	 * */
	Map<String, Object> modifyAdminUserAndPassword(AdminUserVO adminUserVO, Locale locale) throws Exception;
	/*
	 * 管理人员列表
	 * */
	List<AdminUserVO> getAdminUsers(AdminUserVO adminUserVO);
	/*
	 * 删除管理账号
	 * */
	Map<String, Object> deleteAdminUser(AdminUserVO adminUserVO, Locale locale) throws Exception;
	/*
	 * 创建管理组
	 * */
	Map<String, Object> createAdminGroup(AdminGroupVO adminGroupVO, Locale locale) throws Exception;
	/*
	 * 管理组列表
	 * */
	List<AdminGroupVO> getAdminGroups();
	/*
	 * 管理组信息
	 * */
	AdminGroupVO getAdminGroup(int groupId);
	/*
	 * 修改管理组
	 * */
	Map<String, Object> modifyAdminGroup(AdminGroupVO adminGroupVO, List<AdminRoleAccessVO> selectedRoleCodeVO, Locale locale) throws Exception;
	/*
	 * 删除管理组
	 * */
	Map<String, Object> deleteAdminGroup(AdminGroupVO adminGroupVO, Locale locale) throws Exception;
	/*
	 * 获取管理Menu
	 * */
	Map<String, List<AdminRoleVO>> getAdminRolesForMenu(String pagePath);
	/*
	 * 获取管理权限列表
	 * */
	List<AdminRoleVO> getAdminRoles(AdminRoleVO adminRoleVO);
	/*
	 * 获取已授权的权限列表
	 * */
	List<AdminRoleVO> getAdminRoleAccessByPageDeepNGroupId(AdminRoleVO adminRoleVO);
	/*
	 * 使用urlPath获取pageTree
	 * */
	String getPageTreeByRoleCode(AdminRoleVO adminRoleVO);
}