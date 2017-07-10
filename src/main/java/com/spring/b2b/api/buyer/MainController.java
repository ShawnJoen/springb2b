package com.spring.b2b.api.buyer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.b2b.admin.EnvController;

@Controller
public class MainController extends EnvController {

	private static final Logger logger = LoggerFactory.getLogger(MainController.class);

	@RequestMapping(value = "/buyer/main.do", method = RequestMethod.GET)
	public String main() {

		
		
		
		return "admin/main";
	}
}