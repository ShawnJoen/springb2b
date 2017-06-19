package com.spring.dao.config;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.spring.dto.config.SiteConfig;

@Repository
public class SiteConfigDAOImpl implements SiteConfigDAO {

	@Autowired
	private SqlSession sqlSession;
	
	static final String SITE_CONFIG_MAPPER = "com.spring.mappers.SiteConfigMapper.";
	
	@Override
	public int modifySiteConfig(SiteConfig siteConfig) throws Exception {
		
		return sqlSession.update(SITE_CONFIG_MAPPER + "modifySiteConfig", siteConfig);
	}
	@Override
	public List<SiteConfig> getSiteConfigs(SiteConfig siteConfig) {
		
		return sqlSession.selectList(SITE_CONFIG_MAPPER + "getSiteConfigs", siteConfig);
	}
}