package com.spring.service.admin;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.spring.dao.admin.AdminGroupDAO;
import com.spring.dao.admin.AdminRoleAccessDAO;
import com.spring.dao.admin.AdminUserDAO;
import com.spring.dao.admin.OperationRecordDAO;
import com.spring.model.admin.AdminGroup;
import com.spring.model.admin.AdminRoleAccess;
import com.spring.model.admin.AdminUser;
import com.spring.model.admin.AdminUserAuthentication;
import com.spring.model.admin.OperationRecord;
import com.spring.util.Common;
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
	private OperationRecordDAO operationRecordDAO;
	@Autowired
    private Md5PasswordEncoder passwordEncoder;
	@Autowired
    private MessageSource messageSource;
	
	@Transactional("transaction")
	@Override
	public Map<String, Object> createAdminUser(AdminUser adminUser, Locale locale) throws Exception {

		final String[] fieldNames = {"username","password","contactMobile","contactName","groupId"};
		ValidationResult ValidResult = null;
		for (String fieldName: fieldNames) {
			
			ValidResult = ValidationUtils.validateProperty(adminUser, fieldName);
			if (ValidResult.isHasErrors()) {
				
				return output("1", null, 
						ValidResult.getErrorMsg().get(fieldName));
			}
		}

		final int hasAdminUser = this.hasAdminUserByUsername(adminUser.getUsername());
        if(hasAdminUser > 0) {
        	return output("1", null, messageSource.getMessage("id_already_used", null, locale));
        }

		adminUser.setPassword(passwordEncoder.encodePassword(adminUser.getPassword(), adminUser.getUsername()));
		
		final long timeStamps = getTimeStampsLength10();
		adminUser.setCreateTime(timeStamps);

		adminUserDAO.createAdminUser(adminUser);
		
		this.createOperationRecord("创建管理员  账号：" + adminUser.getUsername(), timeStamps);

		return output("0", null, messageSource.getMessage("create_seccess", null, locale));
	}
	@Transactional("transaction")
	@Override
	public Map<String, Object> modifyAdminUser(AdminUser adminUser, Locale locale) throws Exception {

		final String[] fieldNames = {"username","contactMobile","contactName","groupId"};
		ValidationResult ValidResult = null;
		for (String fieldName: fieldNames) {
			
			ValidResult = ValidationUtils.validateProperty(adminUser, fieldName);
			if (ValidResult.isHasErrors()) {
				
				return output("1", null, 
						ValidResult.getErrorMsg().get(fieldName));
			}
		}
		
		final int hasAdminUser = this.hasAdminUserByUsername(adminUser.getUsername());
        if(hasAdminUser == 0) {
        	return output("1", null, messageSource.getMessage("id_not_found", null, locale));
        }
        
        final long timeStamps = getTimeStampsLength10();
		adminUser.setUpdateTime(timeStamps);
		
		final int result = adminUserDAO.modifyAdminUser(adminUser);
		if (result == 0) {
			
			return output("1", null, messageSource.getMessage("modify_fail", null, locale));
		} else {
			
			this.createOperationRecord("修改管理员信息  账号：" + adminUser.getUsername(), timeStamps);
			
			return output("0", null, messageSource.getMessage("modify_seccess", null, locale));
		}
	}
	@Transactional("transaction")
	@Override
	public Map<String, Object> modifyAdminUserAndPassword(AdminUser adminUser, Locale locale) throws Exception {
		
		final String[] fieldNames = {"username","password","contactMobile","contactName","groupId"};
		ValidationResult ValidResult = null;
		for (String fieldName: fieldNames) {
			
			ValidResult = ValidationUtils.validateProperty(adminUser, fieldName);
			if (ValidResult.isHasErrors()) {
				
				return output("1", null, 
						ValidResult.getErrorMsg().get(fieldName));
			}
		}
		
		final int hasAdminUser = this.hasAdminUserByUsername(adminUser.getUsername());
        if(hasAdminUser == 0) {
        	return output("1", null, messageSource.getMessage("id_not_found", null, locale));
        }
        
		adminUser.setPassword(passwordEncoder.encodePassword(adminUser.getPassword(), adminUser.getUsername()));
		
		final long timeStamps = getTimeStampsLength10();
		adminUser.setUpdateTime(timeStamps);
		
		final int result = adminUserDAO.modifyAdminUserAndPassword(adminUser);
		if (result == 0) {
			
			return output("1", null, messageSource.getMessage("modify_fail", null, locale));
		} else {
			
			this.createOperationRecord("修改管理员密码和信息  账号：" + adminUser.getUsername(), timeStamps);
			
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
	@Transactional("transaction")
	@Override
	public Map<String, Object> modifyPassword(AdminUserAuthentication adminUserAuthentication, Locale locale) throws Exception {
		
		final String[] fieldNames = {"username","password","newPassword"};
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
			
			this.createOperationRecord("修改管理员密码  账号：" + adminUserAuthentication.getUsername(), getTimeStampsLength10());
			
			return output("0", null, messageSource.getMessage("modify_seccess", null, locale));
		}
	}
	@Transactional("transaction")
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
	@Transactional("transaction")
	@Override
	public Map<String, Object> deleteAdminUser(AdminUser adminUser, Locale locale) throws Exception {
		
		final long timeStamps = getTimeStampsLength10();
		adminUser.setUpdateTime(timeStamps);
		
		final int result = adminUserDAO.deleteAdminUser(adminUser);
		if (result == 0) {
			
			return output("1", null, messageSource.getMessage("delete_fail", null, locale));
		} else {
			
			this.createOperationRecord("删除管理员  账号：" + adminUser.getUsername(), timeStamps);
			
			return output("0", null, messageSource.getMessage("delete_seccess", null, locale));
		}
	}
	@Transactional("transaction")
	@Override
	public Map<String, Object> createAdminGroup(AdminGroup adminGroup, Locale locale) throws Exception {

		final String[] fieldNames = {"groupName"};
		ValidationResult ValidResult = null;
		for (String fieldName: fieldNames) {
			
			ValidResult = ValidationUtils.validateProperty(adminGroup, fieldName);
			if (ValidResult.isHasErrors()) {
				
				return output("1", null, 
						ValidResult.getErrorMsg().get(fieldName));
			}
		}
		
		adminGroupDAO.createAdminGroup(adminGroup);
		
		this.createOperationRecord("创建管理组  名称：" + adminGroup.getGroupName(), getTimeStampsLength10());
		
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
	@Transactional("transaction")
	@Override
	public Map<String, Object> modifyAdminGroup(AdminGroup adminGroup, Locale locale) throws Exception {
		
		final String[] fieldNames = {"groupName"};
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
			
			this.createOperationRecord("修改管理组  名称：" + adminGroup.getGroupName(), getTimeStampsLength10());
			
			return output("0", null, messageSource.getMessage("modify_seccess", null, locale));
		}
	}
	@Transactional("transaction")
	@Override
	public Map<String, Object> deleteAdminGroup(AdminGroup adminGroup, Locale locale) throws Exception {
	
		final int result = adminGroupDAO.deleteAdminGroup(adminGroup);
		if (result == 0) {
			
			return output("1", null, messageSource.getMessage("delete_fail", null, locale));
		} else {

			this.createOperationRecord("删除管理组  编号：" + adminGroup.getGroupId(), getTimeStampsLength10());
			
			return output("0", null, messageSource.getMessage("delete_seccess", null, locale));
		}
	}
	@Override
	public List<OperationRecord> getOperationRecords(OperationRecord operationRecord) {

		return operationRecordDAO.getOperationRecords(operationRecord);
	}
	@Override
	public void createOperationRecord(String content, long createTime) throws Exception {
		
		operationRecordDAO.createOperationRecord(
				new OperationRecord(
					Common.getLogInUsername(), 
					content, 
					createTime
				)
			);
	}
	
}