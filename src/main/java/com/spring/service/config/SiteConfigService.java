package com.spring.service.config;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import com.spring.vo.config.SiteConfigVO;

public interface SiteConfigService {

	Map<String, Object> modifySiteConfig(List<SiteConfigVO> siteConfigVOs, Locale locale) throws Exception;
	List<SiteConfigVO> getSiteConfigs(SiteConfigVO siteConfigVO);
}