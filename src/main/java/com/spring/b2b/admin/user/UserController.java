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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import com.github.pagehelper.PageInfo;
import com.spring.b2b.admin.EnvController;
import com.spring.service.admin.OperationRecordService;
import static com.spring.util.Common.*;
import com.spring.util.validation.ValidationResult;
import com.spring.util.validation.ValidationUtils;
import com.spring.vo.admin.AdminGroupVO;
import com.spring.vo.admin.AdminRoleAccessVO;
import com.spring.vo.admin.AdminUserVO;
import com.spring.vo.admin.OperationRecordVO;
import com.spring.vo.admin.AdminUserAuthenticationVO;

@Controller
@RequestMapping("/admin/user")
public class UserController extends EnvController {

	private final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
    private OperationRecordService operationRecordService;
	@Autowired
    private MessageSource messageSource;
	
	/*
	 * 登入页 from
	 * */
	@RequestMapping(value = "/login.do", method = RequestMethod.GET)
	public String loginForm(ModelMap model) {

		return "admin/user/login";
	}
	/*
	 * 修改密码页 from 未登录
	 * */
	@RequestMapping(value = "/modifyPasswordNotLoggedIn.do", method = RequestMethod.GET)
	public String modifyPasswordForm(ModelMap model) {
		
		model.addAttribute("adminUserAuthentication", new AdminUserAuthenticationVO());

		return "admin/user/modifyPasswordNotLoggedIn";
	}
	/*
	 * 修改密码页 from 已登录
	 * */
	@RequestMapping(value = "/modifyPassword.do", method = RequestMethod.GET)
	public String modifyPasswordLoggedInForm(ModelMap model) {
		
		model.addAttribute("adminUserAuthentication", new AdminUserAuthenticationVO());
			
		return "admin/user/modifyPasswordLoggedIn";
	}
	/*
	 * 修改密码处理
	 * */
	@RequestMapping(value = "/modifyPassword.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> modifyPassword(@ModelAttribute("adminUserAuthentication") AdminUserAuthenticationVO adminUserAuthenticationVO, 
			BindingResult result,
			Locale locale) throws Exception {
		
		if (result.hasErrors()) {

            return output("1", null, messageSource.getMessage("program_error", null, locale));
        }
		
		final ValidationResult ValidResult = ValidationUtils.validation(adminUserAuthenticationVO);
		if (ValidResult.isHasErrors()) {
			
			return output("1", null, ValidResult.getErrorMessage());
		}
		
		final String username = getLogInUsername();
		if (isLogIn(username)) {
			
			adminUserAuthenticationVO.setUsername(username);
		}
		
		return adminUserService.modifyPassword(adminUserAuthenticationVO, locale);
	}
	/*
	 * 创建用户 from
	 * */
	@RequestMapping(value = "/createAdminUser.do", method = RequestMethod.GET)
	public String createAdminUserForm(ModelMap model) {

		model.addAttribute("adminUser", new AdminUserVO());
		
		List<AdminGroupVO> adminGroupVOs = adminUserService.getAdminGroupSelectBox();
		model.addAttribute("adminGroup", adminGroupVOs);
		
		return "admin/user/createAdminUser";
	}
	/*
	 * 创建用户处理
	 * */
	@RequestMapping(value = "/createAdminUser.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> createAdminUser(@ModelAttribute("adminUser") AdminUserVO adminUserVO, 
			BindingResult result,
			Locale locale) throws Exception {

		if (result.hasErrors()) {

            return output("1", null, messageSource.getMessage("program_error", null, locale));
        }
		
		final ValidationResult ValidResult = ValidationUtils.validation(adminUserVO);
		if (ValidResult.isHasErrors()) {
			
			return output("1", null, ValidResult.getErrorMessage());
		}
		
		return adminUserService.createAdminUser(adminUserVO, locale);
	}
	/*
	 * 修改用户 from
	 * */
	@RequestMapping(value = "/modifyAdminUser.do", method = RequestMethod.GET)
	public String modifyAdminUserForm(@RequestParam(value = "username", required = false, defaultValue="") String username, 
			ModelMap model) {

		final String logInUsername = getLogInUsername();
		if (isNotLogIn(logInUsername)) {
			
			return "redirect:/admin/user/login.do";
		}
		
		final String toUsername = "".equals(username) ? logInUsername : username;
		
		final AdminUserVO adminUserVO = adminUserService.getAdminUserByUsername(toUsername);
		model.addAttribute("adminUser", adminUserVO);
		
		final List<AdminGroupVO> adminGroupVOs = adminUserService.getAdminGroupSelectBox();
		model.addAttribute("adminGroup", adminGroupVOs);
		
		return "admin/user/modifyAdminUser";
	}
	/*
	 * 修改用户处理
	 * */
	@RequestMapping(value = "/modifyAdminUser.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> modifyAdminUser(@ModelAttribute("adminUser") AdminUserVO adminUserVO, 
			BindingResult result,
			Locale locale) throws Exception {
		
		if (result.hasErrors()) {

            return output("1", null, messageSource.getMessage("program_error", null, locale));
        }
		
		final ValidationResult ValidResult = ValidationUtils.validation(adminUserVO);
		if (ValidResult.isHasErrors()) {
			
			return output("1", null, ValidResult.getErrorMessage());
		}

		if ("".equals(adminUserVO.getPassword())) {
			
			return adminUserService.modifyAdminUser(adminUserVO, locale);
		} else {
			
			return adminUserService.modifyAdminUserAndPassword(adminUserVO, locale);
		}
	}
	/*
	 * 管理员用户 列表
	 * */
	@RequestMapping(value = "/adminUserList.do", method = RequestMethod.GET)
	public String adminUserList(@RequestParam(value = "pageNum", required = false, defaultValue="1") Integer pageNum,
            @RequestParam(value = "pageSize", required = false, defaultValue="20") Integer pageSize, 
            @ModelAttribute("adminUser") AdminUserVO adminUserVO, 
            ModelMap model) {

		super.setPageHelper(pageNum, pageSize);
		
		List<AdminUserVO> adminUserVOs = adminUserService.getAdminUsers(adminUserVO);
		
		PageInfo<AdminUserVO> pageInfo = new PageInfo<>(adminUserVOs);
		model.addAttribute("pageInfo", pageInfo);

		//搜索框绑定搜索字段
		model.addAttribute("adminUser", adminUserVO);
		
		return "admin/user/adminUserList";
	}
	/*
	 * 管理员用户删除
	 * */
	@RequestMapping(value = "/deleteAdminUser.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> deleteAdminUser(@ModelAttribute("adminUser") AdminUserVO adminUserVO, 
			BindingResult result,
			Locale locale) throws Exception {
		
		if (result.hasErrors()) {

            return output("1", null, messageSource.getMessage("program_error", null, locale));
        }
		
		return adminUserService.deleteAdminUser(adminUserVO, locale);
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
	public @ResponseBody Map<String, Object> createAdminGroup(@ModelAttribute("adminGroup") AdminGroupVO adminGroupVO, 
			BindingResult result,
			Locale locale) throws Exception {
		
		if (result.hasErrors()) {

            return output("1", null, messageSource.getMessage("program_error", null, locale));
        }
		
		final ValidationResult ValidResult = ValidationUtils.validation(adminGroupVO);
		if (ValidResult.isHasErrors()) {
			
			return output("1", null, ValidResult.getErrorMessage());
		}
		
		return adminUserService.createAdminGroup(adminGroupVO, locale);
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
		
		final AdminGroupVO adminGroupVO = adminUserService.getAdminGroup(groupId);
		model.addAttribute("adminGroup", adminGroupVO);
		
		return "admin/user/modifyAdminGroup";
	}
	/*
	 * 修改管理组 处理
	 * */
	@RequestMapping(value = "/modifyAdminGroup.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> modifyAdminGroup(@ModelAttribute("adminGroup") AdminGroupVO adminGroupVO, 
			@RequestParam(value = "roleCode[]", required = false) String[] roleCode,
			BindingResult result,
			Locale locale) throws Exception {
		
		if (result.hasErrors()) {

            return output("1", null, messageSource.getMessage("program_error", null, locale));
        }
		
		final ValidationResult ValidResult = ValidationUtils.validation(adminGroupVO);
		if (ValidResult.isHasErrors()) {
			
			return output("1", null, ValidResult.getErrorMessage());
		}

		final List<AdminRoleAccessVO> selectedRoleCodeVO = new ArrayList<>();
		if (roleCode != null) {

			for (String role : roleCode) {
				
				AdminRoleAccessVO adminRoleAccessVO = new AdminRoleAccessVO();
				adminRoleAccessVO.setGroupId(adminGroupVO.getGroupId());
				adminRoleAccessVO.setRole(role);
				
				selectedRoleCodeVO.add(adminRoleAccessVO);
			}
		}

		return adminUserService.modifyAdminGroup(adminGroupVO, selectedRoleCodeVO, locale);
	}
	/*
	 * 管理组 列表
	 * */
	@RequestMapping(value = "/adminGroupList.do", method = RequestMethod.GET)
	public String adminGroupList(@RequestParam(value = "pageNum", required = false, defaultValue="1") Integer pageNum,
            @RequestParam(value = "pageSize", required = false, defaultValue="20") Integer pageSize, 
            ModelMap model) {

		super.setPageHelper(pageNum, pageSize);
		
		List<AdminGroupVO> adminGroupVOs = adminUserService.getAdminGroups();
		
		PageInfo<AdminGroupVO> pageInfo = new PageInfo<>(adminGroupVOs);
		model.addAttribute("pageInfo", pageInfo);
		
		return "admin/user/adminGroupList";
	}
	/*
	 * 管理组 删除
	 * */
	@RequestMapping(value = "/deleteAdminGroup.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> deleteAdminGroup(@ModelAttribute("adminGroup") AdminGroupVO adminGroupVO, 
			BindingResult result,
			Locale locale) throws Exception {
		
		if (result.hasErrors()) {

            return output("1", null, messageSource.getMessage("program_error", null, locale));
        }
		
		return adminUserService.deleteAdminGroup(adminGroupVO, locale);
	}
	/*
	 * 管理员用户 列表
	 * */
	@RequestMapping(value = "/operationRecordList.do", method = RequestMethod.GET)
	public String operationRecordList(@RequestParam(value = "pageNum", required = false, defaultValue="1") Integer pageNum,
            @RequestParam(value = "pageSize", required = false, defaultValue="20") Integer pageSize, 
            @ModelAttribute("operationRecord") OperationRecordVO operationRecordVO, 
            ModelMap model) {

		super.setPageHelper(pageNum, pageSize);
		
		List<OperationRecordVO> operationRecordVOs = operationRecordService.getOperationRecords(operationRecordVO);
		
		PageInfo<OperationRecordVO> pageInfo = new PageInfo<>(operationRecordVOs);
		model.addAttribute("pageInfo", pageInfo);

		//搜索框绑定搜索字段
		model.addAttribute("operationRecord", operationRecordVO);
		
		return "admin/user/operationRecordList";
	}
}