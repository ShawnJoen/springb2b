package com.spring.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.security.core.context.SecurityContextHolder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

public class Common {
	
	final public static String getLogInUsername() {
		
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}
	
	final public static boolean isLogIn(String username) {
		
		return !username.equals("anonymousUser");
	}
	
	final public static boolean isNotLogIn(String username) {
		
		return username.equals("anonymousUser");
	}
	
	final public static Map<String, Object> output(String code, Object object, String message) {
		
		Map<String, Object> map = new HashMap<>();
		map.put("code", code);
		map.put("data", object);
		map.put("message", message);
		return map;
	}
	
	final public static String upperCase(String str) { 
		
	    char[] ch = str.toCharArray();  
	    if (ch[0] >= 'a' && ch[0] <= 'z') {  
	    	
	        ch[0] = (char) (ch[0] - 32);  
	    }  
	    return new String(ch);  
	}
	
	final public static String getTimeStamps() {
		
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());
	}
	
	final public static <T> List<T> compareDifferentArray(T[] baseArray, T[] differentArray) {
	
		List<T> baseList = Arrays.asList(baseArray);
		List<T> resultList = new ArrayList<>();
		for (T element : differentArray) {
			
			if (!baseList.contains(element)) {
			
				resultList.add(element);    
			}
		}
		  
		return resultList;    
	}
	
	final public static String serializeAndFilterValueObject(String filterName, Object voClassObject, Set<String> filterSet) 
			throws JsonProcessingException {
	
		String jsonString = null;
		final ObjectMapper mapper = new ObjectMapper();
		try {
			
			FilterProvider filterProvider = new SimpleFilterProvider().addFilter(filterName, 
					SimpleBeanPropertyFilter.serializeAllExcept(filterSet));
			
			jsonString = mapper.writer(filterProvider).writeValueAsString(voClassObject);
		} finally {

		}
		
		return jsonString;
	}
}