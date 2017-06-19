package com.spring.dao.admin.user;

import java.util.List;
import com.spring.dto.admin.AdminRole;

public interface AdminRoleDAO {

	List<AdminRole> getAdminRolesForMenu(AdminRole adminRole);
	List<AdminRole> getAdminRoles(AdminRole adminRole);
	List<AdminRole> getAdminRoleAccessByPageDeepNGroupId(AdminRole adminRole);
	String getPageTreeByRoleCode(AdminRole adminRole);
}