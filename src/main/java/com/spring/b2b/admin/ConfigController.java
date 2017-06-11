package com.spring.b2b.admin;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.ModelAttribute;
import com.spring.b2b.ViewController;
import com.spring.dto.admin.AdminRole;
import com.spring.service.admin.AdminUserService;

public class ConfigController extends ViewController {
	
	final private List<String> forbidMenuList = Arrays.asList(
			"/admin/user/login.do", "/admin/user/logout.do", "/admin/user/modifyPassword.do");
	
	@Autowired
	protected HttpServletRequest request;
	
	@Autowired
	protected AdminUserService adminUserService;
	@Autowired
	protected MessageSource messageSource;
	
	@ModelAttribute("adminMenu")
	protected Map<String, List<AdminRole>> getAdminMenu() {
		
		if ("GET".equals(request.getMethod())) {
			
			final String pagePath = request.getServletPath();

			if (forbidMenuList
					.parallelStream()
					.filter(path -> path.equals(pagePath))
					.count() == 0) {
				
				//输出管理员目录 给 ModelMap
				return adminUserService.getAdminRolesForMenu(pagePath);
			}
		}

		return null;
	}
	
	protected boolean isLogIn(String username) {
		
		return !username.equals("anonymousUser");
	}
	
	protected boolean isNotLogIn(String username) {
		
		return username.equals("anonymousUser");
	}
	
	/*protected String getLogInUsername() {
		
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}*/
}
