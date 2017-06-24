package com.spring.b2b.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController extends EnvController {
	
	private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

	@RequestMapping(value = "/admin/statistics.do", method = RequestMethod.GET)
	public String statistics() {
		
		return "admin/statistics";
	}
		
	
	/*
import static com.spring.util.Common.*;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.spring.dao.admin.user.AdminGroupDAO;
import com.spring.dao.admin.user.AdminRoleAccessDAO;
import com.spring.dao.admin.user.AdminUserDAO;
import com.spring.util.validation.ValidationResult;
import com.spring.util.validation.ValidationUtils;
import com.spring.vo.admin.AdminGroupVO;
import com.spring.vo.admin.AdminUserAuthenticationVO;

	 * @Autowired
	private AdminUserDAO adminUserDAO;
	@Autowired
	private AdminRoleAccessDAO adminRoleAccessDAO;
	@Autowired
	private AdminGroupDAO adminGroupDAO;
	@RequestMapping(value = "/admin/index2.do")
	public @ResponseBody Map<String, Object> index2(
			Locale locale) throws Exception {
		
		final AdminGroupVO adminGroup = adminGroupDAO.getAdminGroup(1);
		//获取指定管理组权限
		final List<String> adminRoleAccess = adminRoleAccessDAO.getAdminRoleAccessByGroupId(1);
		adminGroup.setAdminRoleAccess(adminRoleAccess);
		
		final Set<String> filterSet = new HashSet<>();
		filterSet.add("status");
		filterSet.add("groupName");
		final String jsonData = serializeAndFilterValueObject("adminGroupVO", adminGroup, filterSet);

		return output("1", jsonData, "");
	}
	*/
}