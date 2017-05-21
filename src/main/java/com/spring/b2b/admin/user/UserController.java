package com.spring.b2b.admin.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@RequestMapping(value = "/admin/user/login.do", method = RequestMethod.GET)
	public String login() {
		return "admin/user/login";
	}
	
	/*@RequestMapping("/admin/user/jlogin.do")
	public @ResponseBody ProductVO jlogin() {
		ProductVO vo = new ProductVO("샘풀상품", 3000);
		return vo;
	}*/
}
