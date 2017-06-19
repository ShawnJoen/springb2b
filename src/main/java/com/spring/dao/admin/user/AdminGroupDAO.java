package com.spring.dao.admin.user;

import java.util.List;

import com.spring.dto.admin.AdminGroup;

public interface AdminGroupDAO {
	
	void createAdminGroup(AdminGroup adminGroup) throws Exception;
	List<AdminGroup> getAdminGroupSelectBox();
	List<AdminGroup> getAdminGroups();
	AdminGroup getAdminGroup(int groupId);
	int modifyAdminGroup(AdminGroup adminGroup) throws Exception;
	int deleteAdminGroup(AdminGroup adminGroup) throws Exception;
}