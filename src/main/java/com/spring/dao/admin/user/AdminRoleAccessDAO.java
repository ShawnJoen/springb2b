package com.spring.dao.admin.user;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.spring.dto.admin.AdminRoleAccess;

public interface AdminRoleAccessDAO {
	
	public void createAdminRoleAccess(List<AdminRoleAccess> adminRoleAccess) throws Exception;
	public void deleteAdminRoleAccess(int groupId) throws Exception;
	public List<String> getAdminRoleAccessByGroupId(int groupId);
}