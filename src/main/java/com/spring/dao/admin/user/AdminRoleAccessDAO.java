package com.spring.dao.admin.user;

import java.util.List;
import com.spring.vo.admin.AdminRoleAccessVO;

public interface AdminRoleAccessDAO {
	
	public void createAdminRoleAccess(List<AdminRoleAccessVO> adminRoleAccessVO) throws Exception;
	public void deleteAdminRoleAccess(int groupId) throws Exception;
	public List<String> getAdminRoleAccessByGroupId(int groupId);
}