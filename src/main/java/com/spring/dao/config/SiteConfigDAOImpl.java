package com.spring.dao.config;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.spring.vo.config.SiteConfigVO;

@Repository
public class SiteConfigDAOImpl implements SiteConfigDAO {

	@Autowired
	private SqlSession sqlSession;
	
	static final String SITE_CONFIG_MAPPER = "com.spring.mappers.SiteConfigMapper.";
	
	@Override
	public int modifySiteConfig(SiteConfigVO siteConfigVO) throws Exception {
		
		return sqlSession.update(SITE_CONFIG_MAPPER + "modifySiteConfig", siteConfigVO);
	}
	@Override
	public List<SiteConfigVO> getSiteConfigs(SiteConfigVO siteConfigVO) {
		
		return sqlSession.selectList(SITE_CONFIG_MAPPER + "getSiteConfigs", siteConfigVO);
	}
}