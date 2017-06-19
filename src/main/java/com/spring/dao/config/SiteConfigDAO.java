package com.spring.dao.config;

import java.util.List;
import com.spring.dto.config.SiteConfig;

public interface SiteConfigDAO {

	int modifySiteConfig(SiteConfig siteConfig) throws Exception;
	List<SiteConfig> getSiteConfigs(SiteConfig siteConfig);
}