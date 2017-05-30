package com.spring.service.admin;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.spring.dao.AdminRoleAccessDAO;
import com.spring.dao.AdminUserDAO;
import com.spring.model.admin.AdminRoleAccess;
import com.spring.model.admin.AdminUser;
import com.spring.util.Common;
import com.spring.util.validation.ValidationResult;
import com.spring.util.validation.ValidationUtils;

@Service("adminUserServiceImpl")
public class AdminUserServiceImpl implements AdminUserService {
	
	@Autowired
	private AdminUserDAO adminUserDAO;
	@Autowired
	private AdminRoleAccessDAO adminRoleAccessDAO;
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	@Override
	public void createAdminUser(AdminUser adminUser) throws Exception {
		
		adminUser.setPassword(passwordEncoder.encode(adminUser.getPassword()));
		adminUser.setCreateTime(new Common().getTimeStampsLength10());
		
		adminUserDAO.createAdminUser(adminUser);
	}
	
	@Override
	public AdminUser getAdminUserByUsername(String username) {
		
		return adminUserDAO.getAdminUserByUsername(username);
	}
	
	@Override
	public void createAdminRoleAccess(AdminRoleAccess adminRoleAccess) throws Exception {
		
		adminRoleAccessDAO.createAdminRoleAccess(adminRoleAccess);
	}
	
	@Override
	public List<String> getAdminRoleAccessByUsername(String username) {
		
		return adminRoleAccessDAO.getAdminRoleAccessByUsername(username);
	}
}
