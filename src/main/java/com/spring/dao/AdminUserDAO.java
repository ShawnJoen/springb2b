package com.spring.dao;

import com.spring.model.admin.AdminUser;

public interface AdminUserDAO {
	
	public void createAdminUser(AdminUser adminUser) throws Exception;
	public AdminUser getAdminUserByUsername(String username);
	
		
	
	public String getTime();
	//public void createMember(AdminUserVO adminUser) throws Exception;
	//public void findById(int id);
	//public void selectUserList();
	//public void selectUserList();
}
