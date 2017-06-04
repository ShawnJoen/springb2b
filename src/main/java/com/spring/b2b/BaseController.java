package com.spring.b2b;

import com.github.pagehelper.PageHelper;

public class BaseController {
	
	protected void setPageHelper(Integer pageNum, Integer pageSize) {
		
		if (pageSize == 0 || pageSize > 100) {
			
			pageSize = 20;
		}
		
		PageHelper.startPage(pageNum, pageSize);
	}
}