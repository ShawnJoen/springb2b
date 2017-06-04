package com.spring.dao.admin;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.spring.model.admin.AdminGroup;

@Repository
public class AdminGroupDAOImpl implements AdminGroupDAO {
	
	@Autowired
	private SqlSession sqlSession;

	static final String ADMIN_GROUP_MAPPER = "com.spring.mappers.AdminGroupMapper.";
	
	@Override
	public void createAdminGroup(AdminGroup adminGroup) throws Exception {
		
		sqlSession.insert(ADMIN_GROUP_MAPPER + "createAdminGroup", adminGroup);
	}

	@Override
	public List<HashMap<String,Object>> getAdminGroups() {
		
		return sqlSession.selectList(ADMIN_GROUP_MAPPER + "getAdminGroups");
	}
}