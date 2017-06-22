package com.spring.dao.admin.user;

import java.util.List;
import com.spring.vo.admin.AdminRoleVO;

public interface AdminRoleDAO {

	List<AdminRoleVO> getAdminRolesForMenu(AdminRoleVO adminRoleVO);
	List<AdminRoleVO> getAdminRoles(AdminRoleVO adminRoleVO);
	List<AdminRoleVO> getAdminRoleAccessByPageDeepNGroupId(AdminRoleVO adminRoleVO);
	String getPageTreeByRoleCode(AdminRoleVO adminRoleVO);
}