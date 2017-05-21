package com.spring.persistence;

import javax.inject.Inject;
import org.apache.ibatis.session.SqlSession;
//import com.spring.domain.AdminUserVO;

public class AdminUserImpl implements AdminUserDAO {
	@Inject
	private SqlSession sqlSession;
	@Override
	public String getTime() {

		return sqlSession.selectOne("com.spring.mapper.AdminUserMapper.getTime");
	}
/*	@Override
	public void createMember(AdminUserVO adminUser) throws Exception {
		sqlSession.insert("com.spring.mapper.AdminUserMapper.insertMember", adminUser);
	}*/
}
