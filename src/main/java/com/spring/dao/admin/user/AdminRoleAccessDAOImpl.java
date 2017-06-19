package com.spring.dao.admin.user;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.dto.admin.AdminRoleAccess;

@Repository
public class AdminRoleAccessDAOImpl implements AdminRoleAccessDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
	static final  String ADMIN_ROLE_ACCESS_MAPPER = "com.spring.mappers.AdminRoleAccessMapper.";
	
	@Override
	public void createAdminRoleAccess(List<AdminRoleAccess> adminRoleAccess) throws Exception {
		
		sqlSession.insert(ADMIN_ROLE_ACCESS_MAPPER + "createAdminRoleAccess", adminRoleAccess);
	}
	
	@Override
	public List<String> getAdminRoleAccessByGroupId(int groupId) {
				
		return sqlSession.selectList(ADMIN_ROLE_ACCESS_MAPPER + "getAdminRoleAccessByGroupId", groupId);
	}

	@Override
	public void deleteAdminRoleAccess(int groupId) throws Exception {
		
		sqlSession.insert(ADMIN_ROLE_ACCESS_MAPPER + "deleteAdminRoleAccess", groupId);
	}
	
}
