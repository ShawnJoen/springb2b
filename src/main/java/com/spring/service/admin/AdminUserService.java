package com.spring.service.admin;

import java.util.List;
import java.util.Map;
import java.util.Set;
import com.spring.model.admin.AdminRoleAccess;
import com.spring.model.admin.AdminUser;
import com.spring.model.admin.AdminUserAuthentication;

public interface AdminUserService {
	
	Map<String, Object> createAdminUser(AdminUser adminUser) throws Exception;
	AdminUser getAdminUserByUsername(String username);
	AdminUser getAdminUserAuthentication(AdminUserAuthentication adminUserAuthentication);
	Map<String, Object> modifyPassword(AdminUserAuthentication adminUserAuthentication) throws Exception;
	void createAdminRoleAccess(AdminRoleAccess adminRoleAccess) throws Exception;
	List<String> getAdminRoleAccessByUsername(String username);
}