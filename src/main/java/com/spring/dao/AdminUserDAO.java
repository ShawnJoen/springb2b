package com.spring.dao;

import com.spring.model.admin.AdminUser;
import com.spring.model.admin.AdminUserAuthentication;

public interface AdminUserDAO {
	
	void createAdminUser(AdminUser adminUser) throws Exception;
	AdminUser getAdminUserByUsername(String username);
	AdminUser getAdminUserAuthentication(AdminUserAuthentication adminUserAuthentication);
	int modifyPassword(AdminUserAuthentication adminUserAuthentication) throws Exception;
	String getTime();
}