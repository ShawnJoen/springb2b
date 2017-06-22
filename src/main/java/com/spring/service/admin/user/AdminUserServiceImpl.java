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
import com.spring.service.admin.OperationRecordService;
import com.spring.util.Common;
import com.spring.vo.admin.AdminGroupVO;
import com.spring.vo.admin.AdminRoleVO;
import com.spring.vo.admin.AdminRoleAccessVO;
import com.spring.vo.admin.AdminUserVO;
import com.spring.vo.admin.AdminUserAuthenticationVO;

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
	public Map<String, Object> createAdminUser(AdminUserVO adminUser, Locale locale) throws Exception {

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
	public Map<String, Object> modifyAdminUser(AdminUserVO adminUser, Locale locale) throws Exception {

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
	public Map<String, Object> modifyAdminUserAndPassword(AdminUserVO adminUser, Locale locale) throws Exception {
		
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
	public AdminUserVO getAdminUserByUsername(String username) {
		
		return adminUserDAO.getAdminUserByUsername(username);
	}
	@Override
	public AdminUserVO getAdminUserAuthentication(AdminUserAuthenticationVO adminUserAuthentication) {
		
		return adminUserDAO.getAdminUserAuthentication(adminUserAuthentication);
	}
	@Transactional("transaction")
	@Override
	public Map<String, Object> modifyPassword(AdminUserAuthenticationVO adminUserAuthentication, Locale locale) throws Exception {
		
		adminUserAuthentication.setPassword(passwordEncoder.encodePassword(adminUserAuthentication.getPassword(), adminUserAuthentication.getUsername()));
		
		AdminUserVO checkAdminUser = this.getAdminUserAuthentication(adminUserAuthentication);
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
	public void createAdminRoleAccess(List<AdminRoleAccessVO> adminRoleAccess) throws Exception {
		
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
	public List<AdminGroupVO> getAdminGroupSelectBox() {

		return adminGroupDAO.getAdminGroupSelectBox();
	}
	@Override
	public List<AdminUserVO> getAdminUsers(AdminUserVO adminUser) {
		
		return adminUserDAO.getAdminUsers(adminUser);
	}
	@Transactional("transaction")
	@Override
	public Map<String, Object> deleteAdminUser(AdminUserVO adminUser, Locale locale) throws Exception {
		
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
	public Map<String, Object> createAdminGroup(AdminGroupVO adminGroup, Locale locale) throws Exception {
		
		adminGroupDAO.createAdminGroup(adminGroup);
		
		operationRecordService.createOperationRecord("创建管理组  名称：" + adminGroup.getGroupName());
		
		return output("0", null, messageSource.getMessage("create_seccess", null, locale));
	}
	@Override
	public List<AdminGroupVO> getAdminGroups() {
		
		return adminGroupDAO.getAdminGroups();
	}
	@Override
	public AdminGroupVO getAdminGroup(int groupId) {
		
		final AdminGroupVO adminGroup = adminGroupDAO.getAdminGroup(groupId);
		
		//获取指定管理组权限
		final List<String> adminRoleAccess = this.getAdminRoleAccessByGroupId(groupId);
		adminGroup.setAdminRoleAccess(adminRoleAccess);
		/*
		 * 权限列表
		 * */
		AdminRoleVO adminRole = new AdminRoleVO();
		//一级
		final List<AdminRoleVO> adminRoles = this.getAdminRoles(adminRole);
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
	public Map<String, Object> modifyAdminGroup(AdminGroupVO adminGroup, List<AdminRoleAccessVO> selectedRoleCode, Locale locale) throws Exception {
		
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
	public Map<String, Object> deleteAdminGroup(AdminGroupVO adminGroup, Locale locale) throws Exception {
	
		final int result = adminGroupDAO.deleteAdminGroup(adminGroup);
		if (result == 0) {
			
			return output("1", null, messageSource.getMessage("delete_fail", null, locale));
		} else {

			operationRecordService.createOperationRecord("删除管理组  编号：" + adminGroup.getGroupId());
			
			return output("0", null, messageSource.getMessage("delete_seccess", null, locale));
		}
	}
	@Override
	public Map<String, List<AdminRoleVO>> getAdminRolesForMenu(String pagePath) {
		
		final Map<String, List<AdminRoleVO>> menuAll = new HashMap<>();
		final AdminRoleVO adminRole = new AdminRoleVO(0);
		//一级目录 TOP
		final List<AdminRoleVO> adminTopMenu = adminRoleDAO.getAdminRolesForMenu(adminRole);
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
			final List<AdminRoleVO> adminRoleAccess = this.getAdminRoleAccessByPageDeepNGroupId(adminRole);
			//当前选中menu一级tree
			final String currentDeep0PageTree = currentPageTree.substring(0, 3);
			final Map<String, String> menuPageTreeURI = new HashMap<>();
			for (AdminRoleVO role : adminRoleAccess) {
				//每一个一级目录只会加一次URI
				String pageTree = role.getPageTree().substring(0, 3);
				if (!menuPageTreeURI.containsKey(pageTree)) {
					
					menuPageTreeURI.put(pageTree, role.getPageURI());
				}
			}
			for (AdminRoleVO role : adminTopMenu) {
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
			final List<AdminRoleVO> adminLeftMenu = adminRoleDAO.getAdminRolesForMenu(adminRole);
			//当前选中menu二级tree
			final String currentDeep1PageTree = currentPageTree.substring(0, 5);
			for (AdminRoleVO role : adminLeftMenu) {
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
	public List<AdminRoleVO> getAdminRoleAccessByPageDeepNGroupId(AdminRoleVO adminRole) {
		
		return adminRoleDAO.getAdminRoleAccessByPageDeepNGroupId(adminRole);
	}
	@Override
	public int getGroupIdByUsername(String username) {
		
		return adminUserDAO.getGroupIdByUsername(username);
	}
	@Override
	public List<AdminRoleVO> getAdminRoles(AdminRoleVO adminRole) {

		return adminRoleDAO.getAdminRoles(adminRole);
	}
	@Override
	public String getPageTreeByRoleCode(AdminRoleVO adminRole) {
		
		return adminRoleDAO.getPageTreeByRoleCode(adminRole);
	}
}