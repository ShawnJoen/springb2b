package com.spring.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.context.SecurityContextHolder;

public class Common {
	
	public static String getLogInUsername() {
		
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}
	
	public static Map<String, Object> output(String code, Object object, String message) {
		
		Map<String, Object> map = new HashMap<>();
		map.put("code", code);
		map.put("data", object);
		map.put("message", message);
		return map;
	}
	
	public static long getTimeStamps() {
		
		return new Date().getTime();
	}
	
	public static long getTimeStampsLength10() {
		
		return Long.parseLong(
				String.valueOf(
						com.spring.util.Common.getTimeStamps()
						).substring(0, 10)
				);
	}
}