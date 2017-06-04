package com.spring.dao.admin;

import java.util.HashMap;
import java.util.List;

import com.spring.model.admin.AdminGroup;

public interface AdminGroupDAO {
	
	void createAdminGroup(AdminGroup adminGroup) throws Exception;
	List<HashMap<String,Object>> getAdminGroups();
}