package com.spring.service.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;

import com.spring.dao.admin.AdminGroupDAO;
import com.spring.dao.admin.AdminRoleAccessDAO;
import com.spring.dao.admin.AdminUserDAO;
import com.spring.model.admin.AdminGroup;
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
	@Autowired
    private MessageSource messageSource;
	
	@Override
	public Map<String, Object> createAdminUser(AdminUser adminUser, Locale locale) throws Exception {

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
        	return output("1", null, messageSource.getMessage("id_already_used", null, locale));
        }

		adminUser.setPassword(passwordEncoder.encodePassword(adminUser.getPassword(), adminUser.getUsername()));
		adminUser.setCreateTime(getTimeStampsLength10());
		
		adminUserDAO.createAdminUser(adminUser);
		
		return output("0", null, messageSource.getMessage("create_seccess", null, locale));
	}
	@Override
	public Map<String, Object> modifyAdminUser(AdminUser adminUser, Locale locale) throws Exception {

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
        	return output("1", null, messageSource.getMessage("id_not_found", null, locale));
        }
        
		adminUser.setUpdateTime(getTimeStampsLength10());
		
		final int result = adminUserDAO.modifyAdminUser(adminUser);
		if (result == 0) {
			
			return output("1", null, messageSource.getMessage("modify_fail", null, locale));
		} else {
			
			return output("0", null, messageSource.getMessage("modify_seccess", null, locale));
		}
	}
	@Override
	public Map<String, Object> modifyAdminUserAndPassword(AdminUser adminUser, Locale locale) throws Exception {
		
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
        	return output("1", null, messageSource.getMessage("id_not_found", null, locale));
        }
        
		adminUser.setPassword(passwordEncoder.encodePassword(adminUser.getPassword(), adminUser.getUsername()));
		adminUser.setUpdateTime(getTimeStampsLength10());
		
		final int result = adminUserDAO.modifyAdminUserAndPassword(adminUser);
		if (result == 0) {
			
			return output("1", null, messageSource.getMessage("modify_fail", null, locale));
		} else {
			
			return output("0", null, messageSource.getMessage("modify_seccess", null, locale));
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
	public Map<String, Object> modifyPassword(AdminUserAuthentication adminUserAuthentication, Locale locale) throws Exception {
		
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
			
			return output("1", null, messageSource.getMessage("id_or_pw_error", null, locale));
		}
		
		adminUserAuthentication.setPassword(passwordEncoder.encodePassword(adminUserAuthentication.getNewPassword(), adminUserAuthentication.getUsername()));
		
		/*if (checkAdminUser.getStatus() == 0) {
			
			return output("1", null, "此账号已停止使用");
		}*/
		
		final int result = adminUserDAO.modifyPassword(adminUserAuthentication);
		if (result == 0) {
			
			return output("1", null, messageSource.getMessage("modify_fail", null, locale));
		} else {
			
			return output("0", null, messageSource.getMessage("modify_seccess", null, locale));
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
	public List<AdminGroup> getAdminGroupSelectBox() {

		return adminGroupDAO.getAdminGroupSelectBox();
	}
	@Override
	public List<AdminUser> getAdminUsers(AdminUser adminUser) {
		
		return adminUserDAO.getAdminUsers(adminUser);
	}
	@Override
	public Map<String, Object> deleteAdminUser(AdminUser adminUser, Locale locale) throws Exception {
		
		adminUser.setUpdateTime(getTimeStampsLength10());
		final int result = adminUserDAO.deleteAdminUser(adminUser);
		if (result == 0) {
			
			return output("1", null, messageSource.getMessage("delete_fail", null, locale));
		} else {
			
			return output("0", null, messageSource.getMessage("delete_seccess", null, locale));
		}
	}
	@Override
	public Map<String, Object> createAdminGroup(AdminGroup adminGroup, Locale locale) throws Exception {

		String[] fieldNames = {"groupName"};
		ValidationResult ValidResult = null;
		for (String fieldName: fieldNames) {
			
			ValidResult = ValidationUtils.validateProperty(adminGroup, fieldName);
			if (ValidResult.isHasErrors()) {
				
				return output("1", null, 
						ValidResult.getErrorMsg().get(fieldName));
			}
		}
		
		adminGroupDAO.createAdminGroup(adminGroup);
		
		return output("0", null, messageSource.getMessage("create_seccess", null, locale));
	}
	@Override
	public List<AdminGroup> getAdminGroups() {
		
		return adminGroupDAO.getAdminGroups();
	}
	@Override
	public AdminGroup getAdminGroup(int groupId) {
		
		return adminGroupDAO.getAdminGroup(groupId);
	}
	@Override
	public Map<String, Object> modifyAdminGroup(AdminGroup adminGroup, Locale locale) throws Exception {
		
		String[] fieldNames = {"groupName"};
		ValidationResult ValidResult = null;
		for (String fieldName: fieldNames) {
			
			ValidResult = ValidationUtils.validateProperty(adminGroup, fieldName);
			if (ValidResult.isHasErrors()) {
				
				return output("1", null, 
						ValidResult.getErrorMsg().get(fieldName));
			}
		}
		
		final int result = adminGroupDAO.modifyAdminGroup(adminGroup);
		if (result == 0) {
			
			return output("1", null, messageSource.getMessage("modify_fail", null, locale));
		} else {
			
			return output("0", null, messageSource.getMessage("modify_seccess", null, locale));
		}
	}
	@Override
	public Map<String, Object> deleteAdminGroup(AdminGroup adminGroup, Locale locale) throws Exception {
	
		final int result = adminGroupDAO.deleteAdminGroup(adminGroup);
		if (result == 0) {
			
			return output("1", null, messageSource.getMessage("delete_fail", null, locale));
		} else {
			
			return output("0", null, messageSource.getMessage("delete_seccess", null, locale));
		}
	}
	
	
	
}
