package com.spring.dao.admin.user;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.dto.admin.AdminGroup;

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
	public List<AdminGroup> getAdminGroupSelectBox() {
		
		return sqlSession.selectList(ADMIN_GROUP_MAPPER + "getAdminGroupSelectBox");
	}
	@Override
	public List<AdminGroup> getAdminGroups() {

		return sqlSession.selectList(ADMIN_GROUP_MAPPER + "getAdminGroups");
	}
	@Override
	public AdminGroup getAdminGroup(int groupId) {

		return sqlSession.selectOne(ADMIN_GROUP_MAPPER + "getAdminGroup", groupId);
	}
	@Override
	public int modifyAdminGroup(AdminGroup adminGroup) throws Exception {

		return sqlSession.update(ADMIN_GROUP_MAPPER + "modifyAdminGroup", adminGroup);
	}
	@Override
	public int deleteAdminGroup(AdminGroup adminGroup) throws Exception {

		return sqlSession.update(ADMIN_GROUP_MAPPER + "deleteAdminGroup", adminGroup);
	}
}