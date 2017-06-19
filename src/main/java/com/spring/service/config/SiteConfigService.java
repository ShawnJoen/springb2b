package com.spring.service.config;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import com.spring.dto.config.SiteConfig;

public interface SiteConfigService {

	Map<String, Object> modifySiteConfig(List<SiteConfig> siteConfigs, Locale locale) throws Exception;
	List<SiteConfig> getSiteConfigs(SiteConfig siteConfig);
}