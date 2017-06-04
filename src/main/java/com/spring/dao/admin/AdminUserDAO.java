package com.spring.dao.admin;

import java.util.HashMap;
import java.util.List;

import com.spring.model.admin.AdminUser;
import com.spring.model.admin.AdminUserAuthentication;

public interface AdminUserDAO {
	
	void createAdminUser(AdminUser adminUser) throws Exception;
	int hasAdminUserByUsername(String username);
	AdminUser getAdminUserByUsername(String username);
	AdminUser getAdminUserAuthentication(AdminUserAuthentication adminUserAuthentication);
	int modifyPassword(AdminUserAuthentication adminUserAuthentication) throws Exception;
	int modifyAdminUser(AdminUser adminUser) throws Exception;
	int modifyAdminUserAndPassword(AdminUser adminUser) throws Exception;
	List<AdminUser> getAdminUsers(AdminUser adminUser);
	
	String getTime();
}