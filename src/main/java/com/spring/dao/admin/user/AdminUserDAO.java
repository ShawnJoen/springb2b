package com.spring.dao.admin.user;

import java.util.List;
import com.spring.vo.admin.AdminUserVO;
import com.spring.vo.admin.AdminUserAuthenticationVO;

public interface AdminUserDAO {
	
	void createAdminUser(AdminUserVO adminUserVO) throws Exception;
	int hasAdminUserByUsername(String username);
	AdminUserVO getAdminUserByUsername(String username);
	int getGroupIdByUsername(String username);
	AdminUserVO getAdminUserAuthentication(AdminUserAuthenticationVO adminUserAuthenticationVO);
	int modifyPassword(AdminUserAuthenticationVO adminUserAuthenticationVO) throws Exception;
	int modifyAdminUser(AdminUserVO adminUserVO) throws Exception;
	int modifyAdminUserAndPassword(AdminUserVO adminUserVO) throws Exception;
	List<AdminUserVO> getAdminUsers(AdminUserVO adminUserVO);
	int deleteAdminUser(AdminUserVO adminUserVO) throws Exception;
	String getTime();
}