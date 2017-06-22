package com.spring.dao.admin.user;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.spring.vo.admin.AdminRoleVO;

@Repository
public class AdminRoleDAOImpl implements AdminRoleDAO {

	@Autowired
	private SqlSession sqlSession;
	
	static final String ADMIN_ROLE_MAPPER = "com.spring.mappers.AdminRoleMapper.";
	
	@Override
	public List<AdminRoleVO> getAdminRolesForMenu(AdminRoleVO adminRoleVO) {
		
		return sqlSession.selectList(ADMIN_ROLE_MAPPER + "getAdminRolesForMenu", adminRoleVO);
	}

	@Override
	public List<AdminRoleVO> getAdminRoles(AdminRoleVO adminRoleVO) {
		
		return sqlSession.selectList(ADMIN_ROLE_MAPPER + "getAdminRoles", adminRoleVO);
	}

	@Override
	public List<AdminRoleVO> getAdminRoleAccessByPageDeepNGroupId(AdminRoleVO adminRoleVO) {
		
		return sqlSession.selectList(ADMIN_ROLE_MAPPER + "getAdminRoleAccessByPageDeepNGroupId", adminRoleVO);
	}

	@Override
	public String getPageTreeByRoleCode(AdminRoleVO adminRoleVO) {
		
		return sqlSession.selectOne(ADMIN_ROLE_MAPPER + "getPageTreeByRoleCode", adminRoleVO);
	}
}