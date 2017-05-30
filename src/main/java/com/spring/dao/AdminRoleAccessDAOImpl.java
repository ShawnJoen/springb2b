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
	
	@Override
	public void createAdminRoleAccess(AdminRoleAccess adminRoleAccess) throws Exception {
		
		sqlSession.insert("com.spring.mappers.AdminRoleAccessMapper.createAdminRoleAccess", adminRoleAccess);
	}
	
	@Override
	public List<String> getAdminRoleAccessByUsername(String username) {
				
		return sqlSession.selectList("com.spring.mappers.AdminRoleAccessMapper.getAdminRoleAccessByUsername", username);
	}
}
