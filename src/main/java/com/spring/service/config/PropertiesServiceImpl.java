package com.spring.service.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.spring.config.PropertyConfigurer;

@Service("propertiesServiceImpl")
public class PropertiesServiceImpl implements PropertiesService {
	
	@Autowired
    private PropertyConfigurer propertyConfigurer;
	
    @Override
    public String getProperty(String key) {
        return propertyConfigurer.getProperty(key);
    }

    @Override
    public String getProperty(String key, String defaultValue) {
        return propertyConfigurer.getProperty(key, defaultValue);
    }
}