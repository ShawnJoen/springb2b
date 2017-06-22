package com.spring.dao.config;

import java.util.List;
import com.spring.vo.config.SiteConfigVO;

public interface SiteConfigDAO {

	int modifySiteConfig(SiteConfigVO siteConfigVO) throws Exception;
	List<SiteConfigVO> getSiteConfigs(SiteConfigVO siteConfigVO);
}