package com.spring.dao.admin;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.dto.admin.AdminRole;

@Repository
public class AdminRoleDAOImpl implements AdminRoleDAO {

	@Autowired
	private SqlSession sqlSession;
	
	static final String ADMIN_ROLE_MAPPER = "com.spring.mappers.AdminRoleMapper.";
	
	@Override
	public List<AdminRole> getAdminRolesForMenu(AdminRole adminRole) {
		
		return sqlSession.selectList(ADMIN_ROLE_MAPPER + "getAdminRolesForMenu", adminRole);
	}

	@Override
	public List<AdminRole> getAdminRoles(AdminRole adminRole) {
		
		return sqlSession.selectList(ADMIN_ROLE_MAPPER + "getAdminRoles", adminRole);
	}

	@Override
	public List<AdminRole> getAdminRoleAccessByPageDeepNGroupId(AdminRole adminRole) {
		
		return sqlSession.selectList(ADMIN_ROLE_MAPPER + "getAdminRoleAccessByPageDeepNGroupId", adminRole);
	}

	@Override
	public String getPageTreeByRoleCode(AdminRole adminRole) {
		
		return sqlSession.selectOne(ADMIN_ROLE_MAPPER + "getPageTreeByRoleCode", adminRole);
	}
	
}