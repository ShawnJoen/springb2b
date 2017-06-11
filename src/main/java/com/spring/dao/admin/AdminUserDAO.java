package com.spring.dao.admin;

import java.util.List;
import com.spring.dto.admin.AdminUser;
import com.spring.dto.admin.AdminUserAuthentication;

public interface AdminUserDAO {
	
	void createAdminUser(AdminUser adminUser) throws Exception;
	int hasAdminUserByUsername(String username);
	AdminUser getAdminUserByUsername(String username);
	int getGroupIdByUsername(String username);
	AdminUser getAdminUserAuthentication(AdminUserAuthentication adminUserAuthentication);
	int modifyPassword(AdminUserAuthentication adminUserAuthentication) throws Exception;
	int modifyAdminUser(AdminUser adminUser) throws Exception;
	int modifyAdminUserAndPassword(AdminUser adminUser) throws Exception;
	List<AdminUser> getAdminUsers(AdminUser adminUser);
	int deleteAdminUser(AdminUser adminUser) throws Exception;
	String getTime();
}