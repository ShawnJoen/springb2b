package com.spring.b2b.admin;

import org.springframework.security.core.context.SecurityContextHolder;
import com.spring.b2b.ViewController;

public class ConfigController extends ViewController {
	/*
	 * 无需登入的u ri
	 * */
	//protected final String[] allowAction = {"1","2","3"};
	//String[] ss = {"1","2","3"};
	//String s = "2";
	//boolean isContains = Arrays.asList(ss).contains(s);
	//protected final List<String> allowAction = new ArrayList<String>();
	//Collections.addAll(all , "A","B","C","D","E");
	
	//Collections.addAll(allowAction, "1");
			
		//final HttpSession session = getSession();

	//protected allowAction[] = ['Public/login', 'Public/repwd', 'Public/_login'];
	
	/*protected boolean checkAuth(final HttpServletRequest request) {

		if(Arrays.asList(allowAction).contains(request.getRequestURI())) {
			
			return false;
		} else {
			
			return true;
		}
	}*/
	
	protected String getLogInUsername() {
		
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}
	
	protected boolean isLogIn(String username) {
		
		return !username.equals("anonymousUser");
	}
	
	protected boolean isNotLogIn(String username) {
		
		return username.equals("anonymousUser");
	}
}
