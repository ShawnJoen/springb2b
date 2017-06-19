package com.spring.service.admin.user;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.spring.dao.admin.user.AdminGroupDAO;
import com.spring.dao.admin.user.AdminRoleAccessDAO;
import com.spring.dao.admin.user.AdminRoleDAO;
import com.spring.dao.admin.user.AdminUserDAO;
import com.spring.dto.admin.AdminGroup;
import com.spring.dto.admin.AdminRole;
import com.spring.dto.admin.AdminRoleAccess;
import com.spring.dto.admin.AdminUser;
import com.spring.dto.admin.AdminUserAuthentication;
import com.spring.service.admin.OperationRecordService;
import com.spring.util.Common;
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
	private AdminRoleDAO adminRoleDAO;
	@Autowired
    private Md5PasswordEncoder passwordEncoder;
	@Autowired
    private OperationRecordService operationRecordService;
	@Autowired
    private MessageSource messageSource;
	
	@Transactional("transaction")
	@Override
	public Map<String, Object> createAdminUser(AdminUser adminUser, Locale locale) throws Exception {

		final int hasAdminUser = this.hasAdminUserByUsername(adminUser.getUsername());
        if(hasAdminUser > 0) {
        	return output("1", null, messageSource.getMessage("id_already_used", null, locale));
        }

		adminUser.setPassword(passwordEncoder.encodePassword(adminUser.getPassword(), adminUser.getUsername()));

		adminUserDAO.createAdminUser(adminUser);
		
		operationRecordService.createOperationRecord("创建管理员  账号：" + adminUser.getUsername());

		return output("0", null, messageSource.getMessage("create_seccess", null, locale));
	}
	@Transactional("transaction")
	@Override
	public Map<String, Object> modifyAdminUser(AdminUser adminUser, Locale locale) throws Exception {

		final int hasAdminUser = this.hasAdminUserByUsername(adminUser.getUsername());
        if(hasAdminUser == 0) {
        	return output("1", null, messageSource.getMessage("id_not_found", null, locale));
        }
        
        final String timeStamps = getTimeStamps();
		adminUser.setUpdateTime(timeStamps);
		
		final int result = adminUserDAO.modifyAdminUser(adminUser);
		if (result == 0) {
			
			return output("1", null, messageSource.getMessage("modify_fail", null, locale));
		} else {
			
			operationRecordService.createOperationRecord("修改管理员信息  账号：" + adminUser.getUsername());
			
			return output("0", null, messageSource.getMessage("modify_seccess", null, locale));
		}
	}
	@Transactional("transaction")
	@Override
	public Map<String, Object> modifyAdminUserAndPassword(AdminUser adminUser, Locale locale) throws Exception {
		
		final int hasAdminUser = this.hasAdminUserByUsername(adminUser.getUsername());
        if(hasAdminUser == 0) {
        	return output("1", null, messageSource.getMessage("id_not_found", null, locale));
        }
        
		adminUser.setPassword(passwordEncoder.encodePassword(adminUser.getPassword(), adminUser.getUsername()));
		
		final String timeStamps = getTimeStamps();
		adminUser.setUpdateTime(timeStamps);
		
		final int result = adminUserDAO.modifyAdminUserAndPassword(adminUser);
		if (result == 0) {
			
			return output("1", null, messageSource.getMessage("modify_fail", null, locale));
		} else {
			
			operationRecordService.createOperationRecord("修改管理员密码和信息  账号：" + adminUser.getUsername());
			
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
		
		adminUserAuthentication.setPassword(passwordEncoder.encodePassword(adminUserAuthentication.getPassword(), adminUserAuthentication.getUsername()));
		
		AdminUser checkAdminUser = this.getAdminUserAuthentication(adminUserAuthentication);
		if (checkAdminUser == null || "".equals(checkAdminUser.getUsername()) || 
				!checkAdminUser.getPassword().equals(adminUserAuthentication.getPassword())) {
			
			return output("1", null, messageSource.getMessage("id_or_pw_error", null, locale));
		}
		
		adminUserAuthentication.setPassword(passwordEncoder.encodePassword(adminUserAuthentication.getNewPassword(), adminUserAuthentication.getUsername()));
		
		final String timeStamps = getTimeStamps();
		adminUserAuthentication.setUpdateTime(timeStamps);

		/*if (checkAdminUser.getStatus() == 0) {
			
			return output("1", null, "此账号已停止使用");
		}*/
		
		final int result = adminUserDAO.modifyPassword(adminUserAuthentication);
		if (result == 0) {
			
			return output("1", null, messageSource.getMessage("modify_fail", null, locale));
		} else {
			
			operationRecordService.createOperationRecord("修改管理员密码  账号：" + adminUserAuthentication.getUsername());
			
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
		
		final String timeStamps = getTimeStamps();
		adminUser.setUpdateTime(timeStamps);
		
		final int result = adminUserDAO.deleteAdminUser(adminUser);
		if (result == 0) {
			
			return output("1", null, messageSource.getMessage("delete_fail", null, locale));
		} else {
			
			operationRecordService.createOperationRecord("删除管理员  账号：" + adminUser.getUsername());
			
			return output("0", null, messageSource.getMessage("delete_seccess", null, locale));
		}
	}
	@Transactional("transaction")
	@Override
	public Map<String, Object> createAdminGroup(AdminGroup adminGroup, Locale locale) throws Exception {
		
		adminGroupDAO.createAdminGroup(adminGroup);
		
		operationRecordService.createOperationRecord("创建管理组  名称：" + adminGroup.getGroupName());
		
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
		
		this.deleteAdminRoleAccess(adminGroup.getGroupId());
		if (selectedRoleCode.size() > 0)
		{
			this.createAdminRoleAccess(selectedRoleCode);
		}
		
		final int result = adminGroupDAO.modifyAdminGroup(adminGroup);
		if (result == 0) {
			
			return output("1", null, messageSource.getMessage("modify_fail", null, locale));
		} else {
			
			operationRecordService.createOperationRecord("修改管理组  名称：" + adminGroup.getGroupName());
			
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

			operationRecordService.createOperationRecord("删除管理组  编号：" + adminGroup.getGroupId());
			
			return output("0", null, messageSource.getMessage("delete_seccess", null, locale));
		}
	}
	@Override
	public Map<String, List<AdminRole>> getAdminRolesForMenu(String pagePath) {
		
		final Map<String, List<AdminRole>> menuAll = new HashMap<>();
		final AdminRole adminRole = new AdminRole(0);
		//一级目录 TOP
		final List<AdminRole> adminTopMenu = adminRoleDAO.getAdminRolesForMenu(adminRole);
		//获取二级menu
		adminRole.setPageDeep(1);
		adminRole.setGroupId(
					this.getGroupIdByUsername(
						Common.getLogInUsername()
					)
				);
		//当前URIpath制成roleCode来获取pageTree
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
			//获取已授权的二级所有权限
			final List<AdminRole> adminRoleAccess = this.getAdminRoleAccessByPageDeepNGroupId(adminRole);
			//当前选中menu一级tree
			final String currentDeep0PageTree = currentPageTree.substring(0, 3);
			final Map<String, String> menuPageTreeURI = new HashMap<>();
			for (AdminRole role : adminRoleAccess) {
				//每一个一级目录只会加一次URI
				String pageTree = role.getPageTree().substring(0, 3);
				if (!menuPageTreeURI.containsKey(pageTree)) {
					
					menuPageTreeURI.put(pageTree, role.getPageURI());
				}
			}
			for (AdminRole role : adminTopMenu) {
				//设置一级目录link(因一级默认无link需要设置默认二级link)
				String pageURI = menuPageTreeURI.get(role.getPageTree());
				if (pageURI != null) {
					
					role.setPageURI(pageURI);
					//选中一级menu
					if (currentDeep0PageTree.equals(role.getPageTree())) {
						
						role.setSelected(true);
					}
				}
			}
			//一级目录 TOP
			menuAll.put("top", adminTopMenu);
			//设置一级当前目录tree
			adminRole.setPageTree(currentDeep0PageTree);
			//二级目录 LEFT
			final List<AdminRole> adminLeftMenu = adminRoleDAO.getAdminRolesForMenu(adminRole);
			//当前选中menu二级tree
			final String currentDeep1PageTree = currentPageTree.substring(0, 5);
			for (AdminRole role : adminLeftMenu) {
				//选中二级menu
				if (currentDeep1PageTree.equals(role.getPageTree())) {
					
					role.setSelected(true);
				}
			}

			menuAll.put("left", adminLeftMenu);
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