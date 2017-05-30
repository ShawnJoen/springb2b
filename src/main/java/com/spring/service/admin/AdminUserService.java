package com.spring.service.admin;

import java.util.List;
import java.util.Map;
import java.util.Set;
import com.spring.model.admin.AdminRoleAccess;
import com.spring.model.admin.AdminUser;

public interface AdminUserService {
	
	public void createAdminUser(AdminUser adminUser) throws Exception;
	public AdminUser getAdminUserByUsername(String username);
	public void createAdminRoleAccess(AdminRoleAccess adminRoleAccess) throws Exception;
	public List<String> getAdminRoleAccessByUsername(String username);
}