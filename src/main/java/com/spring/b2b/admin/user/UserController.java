package com.spring.b2b.admin.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import com.github.pagehelper.PageInfo;
import com.spring.b2b.admin.ConfigController;
import com.spring.dto.admin.AdminGroup;
import com.spring.dto.admin.AdminRoleAccess;
import com.spring.dto.admin.AdminUser;
import com.spring.dto.admin.AdminUserAuthentication;
import com.spring.dto.admin.OperationRecord;
import com.spring.util.Common;

import static com.spring.util.Common.output;
/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/admin/user")
public class UserController extends ConfigController {

	private final Logger logger = LoggerFactory.getLogger(UserController.class);
	/*
	 * 登入页 from
	 * */
	@RequestMapping(value = "/login.do", method = RequestMethod.GET)
	public String loginForm(ModelMap model) {

		return "admin/user/login";
	}
	/*
	 * 修改密码页 from
	 * */
	@RequestMapping(value = "/modifyPassword.do", method = RequestMethod.GET)
	public String modifyPasswordForm(ModelMap model) {
		
		model.addAttribute("adminUserAuthentication", new AdminUserAuthentication());

		final String username = Common.getLogInUsername();
		if (super.isNotLogIn(username)) {
			
			return "admin/user/modifyPasswordNotLoggedIn";
		} else {
			
			return "admin/user/modifyPasswordLoggedIn";
		}
	}
	/*
	 * 修改密码处理
	 * */
	@RequestMapping(value = "/modifyPassword.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> modifyPassword(@ModelAttribute("adminUserAuthentication") AdminUserAuthentication adminUserAuthentication, 
			BindingResult result,
			Locale locale) throws Exception {
		
		if (result.hasErrors()) {

            return output("1", null, messageSource.getMessage("program_error", null, locale));
        }
		
		final String username = Common.getLogInUsername();
		if (super.isLogIn(username)) {
			
			adminUserAuthentication.setUsername(username);
		}
		
		return adminUserService.modifyPassword(adminUserAuthentication, locale);
	}
	/*
	 * 创建用户 from
	 * */
	@RequestMapping(value = "/createAdminUser.do", method = RequestMethod.GET)
	public String createAdminUserForm(ModelMap model) {

		model.addAttribute("adminUser", new AdminUser());
		
		List<AdminGroup> adminGroups = adminUserService.getAdminGroupSelectBox();
		model.addAttribute("adminGroup", adminGroups);
		
		return "admin/user/createAdminUser";
	}
	/*
	 * 创建用户处理
	 * */
	@RequestMapping(value = "/createAdminUser.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> createAdminUser(@ModelAttribute("adminUser") AdminUser adminUser, 
			BindingResult result,
			Locale locale) throws Exception {

		if (result.hasErrors()) {

            return output("1", null, messageSource.getMessage("program_error", null, locale));
        }
		
		return adminUserService.createAdminUser(adminUser, locale);
	}
	/*
	 * 修改用户 from
	 * */
	@RequestMapping(value = "/modifyAdminUser.do", method = RequestMethod.GET)
	public String modifyAdminUserForm(@RequestParam(value = "username", required = false, defaultValue="") String username, 
			ModelMap model) {

		final String logInUsername = Common.getLogInUsername();
		if (super.isNotLogIn(logInUsername)) {
			
			return "redirect:/admin/user/login.do";
		}
		
		final String toUsername = "".equals(username) ? logInUsername : username;
		
		final AdminUser adminUser = adminUserService.getAdminUserByUsername(toUsername);
		model.addAttribute("adminUser", adminUser);
		
		final List<AdminGroup> adminGroups = adminUserService.getAdminGroupSelectBox();
		model.addAttribute("adminGroup", adminGroups);
		
		return "admin/user/modifyAdminUser";
	}
	/*
	 * 修改用户处理
	 * */
	@RequestMapping(value = "/modifyAdminUser.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> modifyAdminUser(@ModelAttribute("adminUser") AdminUser adminUser, 
			BindingResult result,
			Locale locale) throws Exception {
		
		if (result.hasErrors()) {

            return output("1", null, messageSource.getMessage("program_error", null, locale));
        }
		
		if ("".equals(adminUser.getPassword())) {
			
			return adminUserService.modifyAdminUser(adminUser, locale);
		} else {
			
			return adminUserService.modifyAdminUserAndPassword(adminUser, locale);
		}
	}
	/*
	 * 管理员用户 列表
	 * */
	@RequestMapping(value = "/adminUserList.do", method = RequestMethod.GET)
	public String adminUserList(@RequestParam(value = "pageNum", required = false, defaultValue="1") Integer pageNum,
            @RequestParam(value = "pageSize", required = false, defaultValue="20") Integer pageSize, 
            @ModelAttribute("adminUser") AdminUser adminUser, 
            ModelMap model) {

		super.setPageHelper(pageNum, pageSize);
		
		List<AdminUser> adminUsers = adminUserService.getAdminUsers(adminUser);
		
		PageInfo<AdminUser> pageInfo = new PageInfo<>(adminUsers);
		model.addAttribute("pageInfo", pageInfo);

		//搜索框绑定搜索字段
		model.addAttribute("adminUser", adminUser);
		
		return "admin/user/adminUserList";
	}
	/*
	 * 管理员用户删除
	 * */
	@RequestMapping(value = "/deleteAdminUser.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> deleteAdminUser(@ModelAttribute("adminUser") AdminUser adminUser, 
			BindingResult result,
			Locale locale) throws Exception {
		
		if (result.hasErrors()) {

            return output("1", null, messageSource.getMessage("program_error", null, locale));
        }
		
		return adminUserService.deleteAdminUser(adminUser, locale);
	}
	/*
	 * 退出登入
	 * */
	@RequestMapping(value="/logout.do", method = RequestMethod.GET)
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {    
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
		
		return "redirect:/admin/user/login.do?logout";
	}
	/*
	 * 创建管理组 from
	 * */
	@RequestMapping(value = "/createAdminGroup.do", method = RequestMethod.GET)
	public String createAdminGroupForm() {

		return "admin/user/createAdminGroup";
	}
	/*
	 * 创建管理组处理
	 * */
	@RequestMapping(value = "/createAdminGroup.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> createAdminGroup(@ModelAttribute("adminGroup") AdminGroup adminGroup, 
			BindingResult result,
			Locale locale) throws Exception {
		
		if (result.hasErrors()) {

            return output("1", null, messageSource.getMessage("program_error", null, locale));
        }
		
		return adminUserService.createAdminGroup(adminGroup, locale);
	}
	/*
	 * 修改管理组 from
	 * */
	@RequestMapping(value = "/modifyAdminGroup.do", method = RequestMethod.GET)
	public String modifyAdminGroupForm(@RequestParam(value = "groupId", required = false, defaultValue="0") Integer groupId, 
			ModelMap model) {
		
		if (groupId == 0) {
			
			return "redirect:/admin/user/adminGroupList";
		}
		
		final AdminGroup adminGroup = adminUserService.getAdminGroup(groupId);
		model.addAttribute("adminGroup", adminGroup);
		
		return "admin/user/modifyAdminGroup";
	}
	/*
	 * 修改管理组 处理
	 * */
	@RequestMapping(value = "/modifyAdminGroup.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> modifyAdminGroup(@ModelAttribute("adminGroup") AdminGroup adminGroup, 
			@RequestParam(value = "roleCode[]", required = false) String[] roleCode,
			BindingResult result,
			Locale locale) throws Exception {
		
		if (result.hasErrors()) {

            return output("1", null, messageSource.getMessage("program_error", null, locale));
        }

		final List<AdminRoleAccess> selectedRoleCode = new ArrayList<>();
		if (roleCode != null) {

			for (String role : roleCode) {
				
				AdminRoleAccess adminRoleAccess = new AdminRoleAccess();
				adminRoleAccess.setGroupId(adminGroup.getGroupId());
				adminRoleAccess.setRole(role);
				
				selectedRoleCode.add(adminRoleAccess);
			}
		}

		return adminUserService.modifyAdminGroup(adminGroup, selectedRoleCode, locale);
	}
	/*
	 * 管理组 列表
	 * */
	@RequestMapping(value = "/adminGroupList.do", method = RequestMethod.GET)
	public String adminGroupList(@RequestParam(value = "pageNum", required = false, defaultValue="1") Integer pageNum,
            @RequestParam(value = "pageSize", required = false, defaultValue="20") Integer pageSize, 
            ModelMap model) {

		super.setPageHelper(pageNum, pageSize);
		
		List<AdminGroup> adminGroups = adminUserService.getAdminGroups();
		
		PageInfo<AdminGroup> pageInfo = new PageInfo<>(adminGroups);
		model.addAttribute("pageInfo", pageInfo);
		
		return "admin/user/adminGroupList";
	}
	/*
	 * 管理组 删除
	 * */
	@RequestMapping(value = "/deleteAdminGroup.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> deleteAdminGroup(@ModelAttribute("adminGroup") AdminGroup adminGroup, 
			BindingResult result,
			Locale locale) throws Exception {
		
		if (result.hasErrors()) {

            return output("1", null, messageSource.getMessage("program_error", null, locale));
        }
		
		return adminUserService.deleteAdminGroup(adminGroup, locale);
	}
	/*
	 * 管理员用户 列表
	 * */
	@RequestMapping(value = "/operationRecordList.do", method = RequestMethod.GET)
	public String operationRecordList(@RequestParam(value = "pageNum", required = false, defaultValue="1") Integer pageNum,
            @RequestParam(value = "pageSize", required = false, defaultValue="20") Integer pageSize, 
            @ModelAttribute("operationRecord") OperationRecord operationRecord, 
            ModelMap model) {

		super.setPageHelper(pageNum, pageSize);
		
		List<OperationRecord> operationRecords = adminUserService.getOperationRecords(operationRecord);
		
		PageInfo<OperationRecord> pageInfo = new PageInfo<>(operationRecords);
		model.addAttribute("pageInfo", pageInfo);

		//搜索框绑定搜索字段
		model.addAttribute("operationRecord", operationRecord);
		
		return "admin/user/operationRecordList";
	}
	
	
	
}