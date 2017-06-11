package com.spring.service.admin;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.spring.dao.admin.AdminGroupDAO;
import com.spring.dao.admin.AdminRoleAccessDAO;
import com.spring.dao.admin.AdminRoleDAO;
import com.spring.dao.admin.AdminUserDAO;
import com.spring.dao.admin.OperationRecordDAO;
import com.spring.dto.admin.AdminGroup;
import com.spring.dto.admin.AdminRole;
import com.spring.dto.admin.AdminRoleAccess;
import com.spring.dto.admin.AdminUser;
import com.spring.dto.admin.AdminUserAuthentication;
import com.spring.dto.admin.OperationRecord;
import com.spring.util.Common;
import com.spring.util.validation.ValidationResult;
import com.spring.util.validation.ValidationUtils;
import static com.spring.util.Common.*;
import static java.util.stream.Collectors.*; 

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
	private AdminRoleDAO adminRoleDAO;
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
	public void createAdminRoleAccess(List<AdminRoleAccess> adminRoleAccess) throws Exception {
		
		adminRoleAccessDAO.createAdminRoleAccess(adminRoleAccess);
	}
	@Override
	public void deleteAdminRoleAccess(int groupId) throws Exception {
		
		adminRoleAccessDAO.deleteAdminRoleAccess(groupId);
	}
	@Override
	public List<String> getAdminRoleAccessByGroupId(int groupId) {
		
		return adminRoleAccessDAO.getAdminRoleAccessByGroupId(groupId);
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
		
		final AdminGroup adminGroup = adminGroupDAO.getAdminGroup(groupId);
		
		//获取指定管理组权限
		final List<String> adminRoleAccess = this.getAdminRoleAccessByGroupId(groupId);
		adminGroup.setAdminRoleAccess(adminRoleAccess);
		/*
		 * 权限列表
		 * */
		AdminRole adminRole = new AdminRole();
		//一级
		final List<AdminRole> adminRoles = this.getAdminRoles(adminRole);
		int deep0Count = adminRoles.size();
		for (int deep0 = 0; deep0 < deep0Count; deep0++) {
			
			adminRole.setPageDeep(adminRoles.get(deep0).getPageDeep() + 1);
			adminRole.setPageTree(adminRoles.get(deep0).getPageTree());
			//二级
			adminRoles.get(deep0).setAdminRoles(
						this.getAdminRoles(adminRole)
					);
			
			int deep1Count = adminRoles.get(deep0).getAdminRoles().size();
			for (int deep1 = 0; deep1 < deep1Count; deep1++) {
				
				adminRole.setPageDeep(adminRoles.get(deep0).getAdminRoles().get(deep1).getPageDeep() + 1);
				adminRole.setPageTree(adminRoles.get(deep0).getAdminRoles().get(deep1).getPageTree());
				//三级
				adminRoles.get(deep0).getAdminRoles().get(deep1).setAdminRoles(
							this.getAdminRoles(adminRole)
						);

			}
		}
		
		adminGroup.setAdminRoles(adminRoles);
		
		return adminGroup;
	}
	@Transactional("transaction")
	@Override
	public Map<String, Object> modifyAdminGroup(AdminGroup adminGroup, List<AdminRoleAccess> selectedRoleCode, Locale locale) throws Exception {
		
		final String[] fieldNames = {"groupName"};
		ValidationResult ValidResult = null;
		for (String fieldName: fieldNames) {
			
			ValidResult = ValidationUtils.validateProperty(adminGroup, fieldName);
			if (ValidResult.isHasErrors()) {
				
				return output("1", null, 
						ValidResult.getErrorMsg().get(fieldName));
			}
		}
		
		this.deleteAdminRoleAccess(adminGroup.getGroupId());
		if (selectedRoleCode.size() > 0)
		{
			this.createAdminRoleAccess(selectedRoleCode);
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
	@Override
	public Map<String, List<AdminRole>> getAdminRolesForMenu(String pagePath) {
		
		final Map<String, List<AdminRole>> menuAll = new HashMap<>();
		final AdminRole adminRole = new AdminRole(0);
		//1级目录 TOP
		final List<AdminRole> adminTopMenu = adminRoleDAO.getAdminRolesForMenu(adminRole);
		//获取已授权的2级所有权限
		adminRole.setPageDeep(1);
		adminRole.setGroupId(
					this.getGroupIdByUsername(
						Common.getLogInUsername()
					)
				);
		final List<AdminRole> adminRoleAccess = this.getAdminRoleAccessByPageDeepNGroupId(adminRole);
		final Map<String, String> menuPageTreeURI = new HashMap<>();
		for (AdminRole role : adminRoleAccess) {
			//每一个一级目录只会加一次URI
			String pageTree = role.getPageTree().substring(0, 3);
			if (!menuPageTreeURI.containsKey(pageTree)) {
				
				menuPageTreeURI.put(pageTree, role.getPageURI());
			}
		}
		for (AdminRole role : adminTopMenu) {
			
			String pageURI = menuPageTreeURI.get(role.getPageTree());
			if (pageURI != null) {
				
				role.setPageURI(pageURI);
			}
		}
		//1级目录 TOP
		menuAll.put("top", adminTopMenu);
		//使用urlPath获取pageTree
		adminRole.setRoleCode(
				Common.upperCase(
					Arrays.asList(pagePath.split("[\\/]+"))
						.parallelStream()
						.filter(path -> path.endsWith(".do"))
						.map(path -> path.replace(".do", ""))
						.collect(joining())
					)
				);
		final String currentPageTree = this.getPageTreeByRoleCode(adminRole);
		if(currentPageTree != null) {

			//2级目录 LEFT
			//adminRole.setPageDeep(1);
			adminRole.setPageTree(currentPageTree.substring(0, 3));
			menuAll.put("left", adminRoleDAO.getAdminRolesForMenu(adminRole));
		}
		
		return menuAll;
	}
	@Override
	public List<AdminRole> getAdminRoleAccessByPageDeepNGroupId(AdminRole adminRole) {
		
		return adminRoleDAO.getAdminRoleAccessByPageDeepNGroupId(adminRole);
	}
	@Override
	public int getGroupIdByUsername(String username) {
		
		return adminUserDAO.getGroupIdByUsername(username);
	}
	@Override
	public List<AdminRole> getAdminRoles(AdminRole adminRole) {

		return adminRoleDAO.getAdminRoles(adminRole);
	}
	@Override
	public String getPageTreeByRoleCode(AdminRole adminRole) {
		
		return adminRoleDAO.getPageTreeByRoleCode(adminRole);
	}
	
}