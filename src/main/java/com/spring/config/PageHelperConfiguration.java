package com.spring.config;
/*
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.pagehelper.PageHelper;

@Configuration
public class PageHelperConfiguration {
	
    private static final Logger log = LoggerFactory.getLogger(PageHelperConfiguration.class);
    @Bean
    public PageHelper pageHelper() {
    	
        log.info("MyBatis PageHelper");

        Properties properties = new Properties();
        properties.setProperty("offsetAsPageNum", "true");
        //ʹ��RowBounds��ҳ�����count��ѯ,Ҳ�����Ƿ��ѯ����������
        properties.setProperty("rowBoundsWithCount", "true");
        properties.setProperty("reasonable", "true");
        //ͨ������pageSize=0����RowBounds.limit = 0�ͻ��ѯ��ȫ���Ľ����
        properties.setProperty("pageSizeZero", "true");
        
        PageHelper pageHelper = new PageHelper();
        pageHelper.setProperties(properties);
        return pageHelper;
    }
}*/