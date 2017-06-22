package com.spring.b2b.admin;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.ModelAttribute;
import com.spring.b2b.ViewController;
import com.spring.service.admin.user.AdminUserService;
import com.spring.service.buyer.AdService;
import com.spring.service.goods.GoodsStandardService;
import com.spring.vo.admin.AdminRoleVO;

public class EnvController extends ViewController {
	
	final private List<String> forbidMenuList = Arrays.asList(
			"/admin/user/login.do", "/admin/user/logout.do", "/admin/user/modifyPasswordNotLoggedIn.do", "/admin/user/modifyPassword.do");
	
	@Autowired
	protected HttpServletRequest request;
	@Autowired
	protected AdminUserService adminUserService;
	@Autowired
	protected AdService adService;
	@Autowired
	protected GoodsStandardService goodsStandardService;
	@Autowired
	protected MessageSource messageSource;
	
	@ModelAttribute("adminMenu")
	protected Map<String, List<AdminRoleVO>> getAdminMenu() {
		
		if ("GET".equals(request.getMethod())) {
			
			//当前URIpath
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
}