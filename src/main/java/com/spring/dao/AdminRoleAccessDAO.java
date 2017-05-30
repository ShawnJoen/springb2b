package com.spring.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;
import com.spring.model.admin.AdminRoleAccess;

public interface AdminRoleAccessDAO {
	
	public void createAdminRoleAccess(AdminRoleAccess adminRoleAccess) throws Exception;
	public List<String> getAdminRoleAccessByUsername(String username);
}