package com.spring.b2b.admin.user;

import java.util.HashMap;
import java.util.List;
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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.spring.b2b.admin.ConfigController;
import com.spring.model.admin.AdminUser;
import com.spring.model.admin.AdminUserAuthentication;
import com.spring.service.admin.AdminUserService;
import static com.spring.util.Common.output;
/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/admin/user")
public class UserController extends ConfigController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	AdminUserService adminUserService;

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

		final String username = super.getLogInUsername();
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
			BindingResult result) throws Exception {
		
		if (result.hasErrors()) {

            return output("1", null, "程序错误");
        }
		
		final String username = super.getLogInUsername();
		if (super.isLogIn(username)) {
			
			adminUserAuthentication.setUsername(username);
		}
		
		return adminUserService.modifyPassword(adminUserAuthentication);
	}
	/*
	 * 创建用户 from
	 * */
	@RequestMapping(value = "/createAdminUser.do", method = RequestMethod.GET)
	public String createAdminUserForm(ModelMap model) {

		model.addAttribute("adminUser", new AdminUser());
		
		List<HashMap<String,Object>> adminGroups = adminUserService.getAdminGroups();
		model.addAttribute("adminGroup", adminGroups);
		
		return "admin/user/createAdminUser";
	}
	/*
	 * 创建用户处理
	 * */
	@RequestMapping(value = "/createAdminUser.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> createAdminUser(@ModelAttribute("adminUser") AdminUser adminUser, 
			BindingResult result) throws Exception {
		
		if (result.hasErrors()) {

            return output("1", null, "程序错误");
        }
		
		return adminUserService.createAdminUser(adminUser);
	}
	/*
	 * 修改用户 from
	 * */
	@RequestMapping(value = "/modifyAdminUser.do", method = RequestMethod.GET)
	public String modifyAdminUserForm(@RequestParam(value = "username", required = false, defaultValue="") String username, 
			ModelMap model) {

		final String logInUsername = super.getLogInUsername();
		if (super.isNotLogIn(logInUsername)) {
			
			return "admin/user/login";
		}
		
		final String toUsername = "".equals(username) ? logInUsername : username;
		
		final AdminUser adminUser = adminUserService.getAdminUserByUsername(toUsername);
		model.addAttribute("adminUser", adminUser);
		
		final List<HashMap<String,Object>> adminGroups = adminUserService.getAdminGroups();
		model.addAttribute("adminGroup", adminGroups);
		
		return "admin/user/modifyAdminUser";
	}
	/*
	 * 修改用户处理
	 * */
	@RequestMapping(value = "/modifyAdminUser.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> modifyAdminUser(@ModelAttribute("adminUser") AdminUser adminUser, 
			BindingResult result) throws Exception {
		
		if (result.hasErrors()) {

            return output("1", null, "程序错误");
        }
		
		if ("".equals(adminUser.getPassword())) {
			
			return adminUserService.modifyAdminUser(adminUser);
		} else {
			
			return adminUserService.modifyAdminUserAndPassword(adminUser);
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
		
		PageInfo<AdminUser> pageInfo = new PageInfo<AdminUser>(adminUsers);
		model.addAttribute("pageInfo", pageInfo);
		
		//搜索框绑定搜索字段
		model.addAttribute("adminUser", adminUser);
		
		return "admin/user/adminUserList";
	}
	/*
	 * 管理员用户删除
	 * */
	/*@RequestMapping(value = "/deleteAdminUser.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> deleteAdminUser(@RequestParam(value = "username", required = false, defaultValue="") String username) throws Exception {

		
		return adminUserService.deleteAdminUser(username);
	}*/
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
}