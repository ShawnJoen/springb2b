package com.spring.dao.admin.user;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.spring.vo.admin.AdminGroupVO;

@Repository
public class AdminGroupDAOImpl implements AdminGroupDAO {
	
	@Autowired
	private SqlSession sqlSession;

	static final String ADMIN_GROUP_MAPPER = "com.spring.mappers.AdminGroupMapper.";
	
	@Override
	public void createAdminGroup(AdminGroupVO adminGroupVO) throws Exception {
		
		sqlSession.insert(ADMIN_GROUP_MAPPER + "createAdminGroup", adminGroupVO);
	}
	@Override
	public List<AdminGroupVO> getAdminGroupSelectBox() {
		
		return sqlSession.selectList(ADMIN_GROUP_MAPPER + "getAdminGroupSelectBox");
	}
	@Override
	public List<AdminGroupVO> getAdminGroups() {

		return sqlSession.selectList(ADMIN_GROUP_MAPPER + "getAdminGroups");
	}
	@Override
	public AdminGroupVO getAdminGroup(int groupId) {

		return sqlSession.selectOne(ADMIN_GROUP_MAPPER + "getAdminGroup", groupId);
	}
	@Override
	public int modifyAdminGroup(AdminGroupVO adminGroupVO) throws Exception {

		return sqlSession.update(ADMIN_GROUP_MAPPER + "modifyAdminGroup", adminGroupVO);
	}
	@Override
	public int deleteAdminGroup(AdminGroupVO adminGroupVO) throws Exception {

		return sqlSession.update(ADMIN_GROUP_MAPPER + "deleteAdminGroup", adminGroupVO);
	}
}