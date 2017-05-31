package com.spring.b2b.admin.user;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import com.spring.b2b.admin.ConfigController;
import com.spring.model.admin.AdminUser;
import com.spring.model.admin.AdminUserAuthentication;
import com.spring.service.admin.AdminUserService;
import static com.spring.util.Common.output;
/**
 * Handles requests for the application home page.
 */
@Controller
public class UserController extends ConfigController {
	
	protected final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	AdminUserService adminUserService;

	/*
	 * 登入页 from
	 * */
	@RequestMapping(value = "/admin/user/login.do", method = RequestMethod.GET)
	public String loginForm(ModelMap model) {
		
		model.addAttribute("adminUser", new AdminUser());
		return "admin/user/login";
	}
	/*
	 * 修改密码页 from
	 * */
	@RequestMapping(value = "/admin/user/modifyPassword.do", method = RequestMethod.GET)
	public String modifyPasswordForm(ModelMap model) {
		
		model.addAttribute("adminUserAuthentication", new AdminUserAuthentication());
		return "admin/user/modifyPassword";
	}
	/*
	 * 修改密码处理
	 * */
	@RequestMapping(value = "/admin/user/modifyPassword.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> modifyPassword(@ModelAttribute("adminUserAuthentication") AdminUserAuthentication adminUserAuthentication, 
			BindingResult result) throws Exception {
		
		if (result.hasErrors()) {

            return output("1", null, "程序错误");
        }
		
		return adminUserService.modifyPassword(adminUserAuthentication);
	}
	/*
	 * 创建用户 from
	 * */
	@RequestMapping(value = "/admin/user/createAdminUser.do", method = RequestMethod.GET)
	public String createAdminUserForm(ModelMap model) {

		model.addAttribute("adminUser", new AdminUser());
		return "admin/user/createAdminUser";
	}
	/*
	 * 创建用户处理
	 * */
	@RequestMapping(value = "/admin/user/createAdminUser.do", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> createAdminUser(@ModelAttribute("adminUser") AdminUser adminUser, 
			BindingResult result) throws Exception {
		
		if (result.hasErrors()) {

            return output("1", null, "程序错误");
        }
		
		return adminUserService.createAdminUser(adminUser);
	}
	/*
	 * 退出登入
	 * */
	@RequestMapping(value="/admin/user/logout.do", method = RequestMethod.GET)
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {    
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
		
		return "redirect:/admin/user/login.do?logout";
	}
	
	
	//if(checkAuth(request))
	//{
		//HttpSession session = getSession();
	//}
	//做个函数 先看是否是 需要登入的 ， 然后 如需登入的 就 在判断 此 控制器是 需登入的还是不是来做处理
	/*if(session.getAttribute("loginInfo") == null) {
		//
		System.out.println("没有值231478" + request.getRequestURI());
	}*/
	
	//System.out.println("22222222222");
	//session.setAttribute("loginInfo", "春林");
	//if(session.getAttributeNames())
	
	//System.out.println(session.getAttribute("name2222"));
	
	/*
	 * 登入处理
	 * */
	/*@RequestMapping("/admin/user/jlogin.do")
	public @ResponseBody Map<String, Object> login() {
		
		Map<String, String> map = new HashMap<>();
		Map<String, Object> map2 = new HashMap<>();
		
		map2.put("code", "0");
		map2.put("msg", "啦啦啦");
		map.put("id", "1");
		map.put("loginAccount", "全春林");
		map.put("contactMobile", "18621325460");
		map.put("contactName", "管理员");
		map.put("loginIp", "192.168.0.1");
		//AdminUserDTO map = new AdminUserDTO("admin", "01021323888", "人", 9999999, 0);
		map2.put("data", map);
		
		return map2;
	}*/
	/*@RequestMapping("/admin/user/login.do")
	public String login(@Valid Authenticate authenticate, BindingResult bindingResult, HttpSession session, Model model) {
		//return "admin/user/login";
		if(bindingResult.hasError()) {
			return "users/login";
		}
		
		User user = userDao.findById(authenticate.getUserId());
		if (user == null) {
			model.addAttribute("errorMessage", "존재하지 않는 사용자입니다.");
			return "users/login";
		}
		
		if(!user.getPassword().equals(authenticate.getPassword())) {
			model.addAttribute("errorMessage", "비밀번호가 틀립니다.");
			return "users/login";
		}
		
		session.setAttribute("userId", user.getUserId());
		return "redirect:/admin/index.do";
	}*/
}