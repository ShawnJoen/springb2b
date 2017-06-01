package com.spring.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.spring.model.admin.AdminRoleAccess;

@Repository
public class AdminRoleAccessDAOImpl implements AdminRoleAccessDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
	static final  String ADMIN_ROLE_ACCESS_MAPPER = "com.spring.mappers.AdminRoleAccessMapper.";
	
	@Override
	public void createAdminRoleAccess(AdminRoleAccess adminRoleAccess) throws Exception {
		
		sqlSession.insert(ADMIN_ROLE_ACCESS_MAPPER + "createAdminRoleAccess", adminRoleAccess);
	}
	
	@Override
	public List<String> getAdminRoleAccessByUsername(String username) {
				
		return sqlSession.selectList(ADMIN_ROLE_ACCESS_MAPPER + "getAdminRoleAccessByUsername", username);
	}
}
