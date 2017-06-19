package com.spring.b2b;

import org.springframework.beans.factory.annotation.Autowired;
import com.github.pagehelper.PageHelper;
import com.spring.service.config.PropertiesService;

public class BaseController {
	
	@Autowired
    public PropertiesService propertiesService;
	
	protected void setPageHelper(Integer pageNum, Integer pageSize) {
		
		if (pageSize == 0 || pageSize > 100) {
			
			pageSize = 20;
		}
		
		PageHelper.startPage(pageNum, pageSize);
	}
}