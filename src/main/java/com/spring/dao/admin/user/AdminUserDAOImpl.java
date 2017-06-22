package com.spring.dao.admin.user;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.spring.vo.admin.AdminUserVO;
import com.spring.vo.admin.AdminUserAuthenticationVO;

@Repository
public class AdminUserDAOImpl implements AdminUserDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
	static final String ADMIN_USER_MAPPER = "com.spring.mappers.AdminUserMapper.";

	@Override
	public String getTime() {

		return sqlSession.selectOne(ADMIN_USER_MAPPER + "getTime");
	}
	@Override
	public void createAdminUser(AdminUserVO adminUserVO) throws Exception {
	
		sqlSession.insert(ADMIN_USER_MAPPER + "createAdminUser", adminUserVO);
	}
	@Override
	public int hasAdminUserByUsername(String username) {

		return sqlSession.selectOne(ADMIN_USER_MAPPER + "hasAdminUserByUsername", username);
	}
	@Override
	public AdminUserVO getAdminUserByUsername(String username) {

		return sqlSession.selectOne(ADMIN_USER_MAPPER + "getAdminUserByUsername", username);
	}
	
	@Override
	public int getGroupIdByUsername(String username) {
		
		return sqlSession.selectOne(ADMIN_USER_MAPPER + "getGroupIdByUsername", username);
	}
	@Override
	public AdminUserVO getAdminUserAuthentication(AdminUserAuthenticationVO adminUserAuthenticationVO) {
		
		return sqlSession.selectOne(ADMIN_USER_MAPPER + "getAdminUserAuthentication", adminUserAuthenticationVO);
	}
	@Override
	public int modifyPassword(AdminUserAuthenticationVO adminUserAuthenticationVO) throws Exception {
		
		return sqlSession.update(ADMIN_USER_MAPPER + "modifyPassword", adminUserAuthenticationVO);
	}
	@Override
	public int modifyAdminUser(AdminUserVO adminUserVO) throws Exception {
		
		return sqlSession.update(ADMIN_USER_MAPPER + "modifyAdminUser", adminUserVO);
	}
	@Override
	public int modifyAdminUserAndPassword(AdminUserVO adminUserVO) throws Exception {
		
		return sqlSession.update(ADMIN_USER_MAPPER + "modifyAdminUserAndPassword", adminUserVO);
	}
	@Override
	public List<AdminUserVO> getAdminUsers(AdminUserVO adminUserVO) {
		
		return sqlSession.selectList(ADMIN_USER_MAPPER + "getAdminUsers", adminUserVO);
	}
	@Override
	public int deleteAdminUser(AdminUserVO adminUserVO) throws Exception {
		
		return sqlSession.update(ADMIN_USER_MAPPER + "deleteAdminUser", adminUserVO);
	}
}