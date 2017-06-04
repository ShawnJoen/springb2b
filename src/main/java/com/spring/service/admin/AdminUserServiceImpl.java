package com.spring.service.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;

import com.spring.dao.admin.AdminGroupDAO;
import com.spring.dao.admin.AdminRoleAccessDAO;
import com.spring.dao.admin.AdminUserDAO;
import com.spring.model.admin.AdminRoleAccess;
import com.spring.model.admin.AdminUser;
import com.spring.model.admin.AdminUserAuthentication;
import com.spring.util.validation.ValidationResult;
import com.spring.util.validation.ValidationUtils;
import static com.spring.util.Common.*;

@Service("adminUserServiceImpl")
public class AdminUserServiceImpl implements AdminUserService {
	
	@Autowired
	private AdminUserDAO adminUserDAO;
	@Autowired
	private AdminRoleAccessDAO adminRoleAccessDAO;
	@Autowired
	private AdminGroupDAO adminGroupDAO;
	@Autowired
    private Md5PasswordEncoder passwordEncoder;
	
	@Override
	public Map<String, Object> createAdminUser(AdminUser adminUser) throws Exception {

		String[] fieldNames = {"username","password","contactMobile","contactName","groupId"};
		ValidationResult ValidResult = null;
		for (String fieldName: fieldNames) {
			
			ValidResult = ValidationUtils.validateProperty(adminUser, fieldName);
			if (ValidResult.isHasErrors()) {
				
				return output("1", null, 
						ValidResult.getErrorMsg().get(fieldName));
			}
		}

		int hasAdminUser = this.hasAdminUserByUsername(adminUser.getUsername());
        if(hasAdminUser > 0) {
        	return output("1", null, "创建失败，此账号已注册");
        }

		adminUser.setPassword(passwordEncoder.encodePassword(adminUser.getPassword(), adminUser.getUsername()));
		adminUser.setCreateTime(getTimeStampsLength10());
		
		adminUserDAO.createAdminUser(adminUser);
		
		return output("0", null, "创建成功");
	}
	@Override
	public Map<String, Object> modifyAdminUser(AdminUser adminUser) throws Exception {

		String[] fieldNames = {"username","contactMobile","contactName","groupId"};
		ValidationResult ValidResult = null;
		for (String fieldName: fieldNames) {
			
			ValidResult = ValidationUtils.validateProperty(adminUser, fieldName);
			if (ValidResult.isHasErrors()) {
				
				return output("1", null, 
						ValidResult.getErrorMsg().get(fieldName));
			}
		}
		
		int hasAdminUser = this.hasAdminUserByUsername(adminUser.getUsername());
        if(hasAdminUser == 0) {
        	return output("1", null, "修改失败，此账号不存在");
        }
        
		adminUser.setUpdateTime(getTimeStampsLength10());
		
		final int result = adminUserDAO.modifyAdminUser(adminUser);
		if (result == 0) {
			
			return output("1", null, "修改失败");
		} else {
			
			return output("0", null, "修改成功");
		}
	}
	@Override
	public Map<String, Object> modifyAdminUserAndPassword(AdminUser adminUser) throws Exception {
		
		String[] fieldNames = {"username","password","contactMobile","contactName","groupId"};
		ValidationResult ValidResult = null;
		for (String fieldName: fieldNames) {
			
			ValidResult = ValidationUtils.validateProperty(adminUser, fieldName);
			if (ValidResult.isHasErrors()) {
				
				return output("1", null, 
						ValidResult.getErrorMsg().get(fieldName));
			}
		}
		
		int hasAdminUser = this.hasAdminUserByUsername(adminUser.getUsername());
        if(hasAdminUser == 0) {
        	return output("1", null, "修改失败，此账号不存在");
        }
        
		adminUser.setPassword(passwordEncoder.encodePassword(adminUser.getPassword(), adminUser.getUsername()));
		adminUser.setUpdateTime(getTimeStampsLength10());
		
		final int result = adminUserDAO.modifyAdminUserAndPassword(adminUser);
		if (result == 0) {
			
			return output("1", null, "修改失败");
		} else {
			
			return output("0", null, "修改成功");
		}
	}
	@Override
	public int hasAdminUserByUsername(String username) {
		
		return adminUserDAO.hasAdminUserByUsername(username);
	}
	@Override
	public AdminUser getAdminUserByUsername(String username) {
		
		return adminUserDAO.getAdminUserByUsername(username);
	}
	@Override
	public AdminUser getAdminUserAuthentication(AdminUserAuthentication adminUserAuthentication) {
		
		return adminUserDAO.getAdminUserAuthentication(adminUserAuthentication);
	}
	@Override
	public Map<String, Object> modifyPassword(AdminUserAuthentication adminUserAuthentication) throws Exception {
		
		String[] fieldNames = {"username","password","newPassword"};
		ValidationResult ValidResult = null;
		for (String fieldName: fieldNames) {
			
			ValidResult = ValidationUtils.validateProperty(adminUserAuthentication, fieldName);
			if (ValidResult.isHasErrors()) {
				
				return output("1", null, 
						ValidResult.getErrorMsg().get(fieldName));
			}
		}

		adminUserAuthentication.setPassword(passwordEncoder.encodePassword(adminUserAuthentication.getPassword(), adminUserAuthentication.getUsername()));
		
		AdminUser checkAdminUser = this.getAdminUserAuthentication(adminUserAuthentication);
		if (checkAdminUser == null || "".equals(checkAdminUser.getUsername()) || 
				!checkAdminUser.getPassword().equals(adminUserAuthentication.getPassword())) {
			
			return output("1", null, "账号或密码错误");
		}
		
		adminUserAuthentication.setPassword(passwordEncoder.encodePassword(adminUserAuthentication.getNewPassword(), adminUserAuthentication.getUsername()));
		
		/*if (checkAdminUser.getStatus() == 0) {
			
			return output("1", null, "此账号已停止使用");
		}*/
		
		final int result = adminUserDAO.modifyPassword(adminUserAuthentication);
		if (result == 0) {
			
			return output("1", null, "修改失败");
		} else {
			
			return output("0", null, "修改成功");
		}
	}
	@Override
	public void createAdminRoleAccess(AdminRoleAccess adminRoleAccess) throws Exception {
		
		adminRoleAccessDAO.createAdminRoleAccess(adminRoleAccess);
	}
	@Override
	public List<String> getAdminRoleAccessByUsername(String username) {
		
		return adminRoleAccessDAO.getAdminRoleAccessByUsername(username);
	}
	@Override
	public List<HashMap<String,Object>> getAdminGroups() {

		return adminGroupDAO.getAdminGroups();
	}
	@Override
	public List<AdminUser> getAdminUsers(AdminUser adminUser) {
		
		return adminUserDAO.getAdminUsers(adminUser);
	}

	
	
}
