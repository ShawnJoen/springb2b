package com.spring.dao.admin.user;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.spring.vo.admin.AdminRoleAccessVO;

@Repository
public class AdminRoleAccessDAOImpl implements AdminRoleAccessDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
	static final  String ADMIN_ROLE_ACCESS_MAPPER = "com.spring.mappers.AdminRoleAccessMapper.";
	
	@Override
	public void createAdminRoleAccess(List<AdminRoleAccessVO> adminRoleAccessVO) throws Exception {
		
		sqlSession.insert(ADMIN_ROLE_ACCESS_MAPPER + "createAdminRoleAccess", adminRoleAccessVO);
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