package com.spring.b2b.admin;

import org.springframework.security.core.context.SecurityContextHolder;
import com.spring.b2b.ViewController;

public class ConfigController extends ViewController {
	
	/*protected String getLogInUsername() {
		
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}*/
	
	protected boolean isLogIn(String username) {
		
		return !username.equals("anonymousUser");
	}
	
	protected boolean isNotLogIn(String username) {
		
		return username.equals("anonymousUser");
	}
}
