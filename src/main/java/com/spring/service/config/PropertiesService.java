package com.spring.service.config;

public interface PropertiesService {
    String getProperty(String key);
    String getProperty(String key, String defaultValue);
}