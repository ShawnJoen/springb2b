package com.spring.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.model.admin.AdminUser;
import com.spring.model.admin.AdminUserAuthentication;

@Repository
public class AdminUserDAOImpl implements AdminUserDAO {
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public String getTime() {

		return sqlSession.selectOne("com.spring.mappers.AdminUserMapper.getTime");
	}
	@Override
	public void createAdminUser(AdminUser adminUser) throws Exception {
	
		sqlSession.insert("com.spring.mappers.AdminUserMapper.createAdminUser", adminUser);
	}
	@Override
	public AdminUser getAdminUserByUsername(String username) {
		
		return sqlSession.selectOne("com.spring.mappers.AdminUserMapper.getAdminUserByUsername", username);
	}
	@Override
	public AdminUser getAdminUserAuthentication(AdminUserAuthentication adminUserAuthentication) {
		
		return sqlSession.selectOne("com.spring.mappers.AdminUserMapper.getAdminUserAuthentication", adminUserAuthentication);
	}
	@Override
	public int modifyPassword(AdminUserAuthentication adminUserAuthentication) throws Exception {
		
		return sqlSession.update("com.spring.mappers.AdminUserMapper.modifyPassword", adminUserAuthentication);
	}
}
