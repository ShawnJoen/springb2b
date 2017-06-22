package com.spring.dao.admin.user;

import java.util.List;
import com.spring.vo.admin.AdminGroupVO;

public interface AdminGroupDAO {
	
	void createAdminGroup(AdminGroupVO adminGroupVO) throws Exception;
	List<AdminGroupVO> getAdminGroupSelectBox();
	List<AdminGroupVO> getAdminGroups();
	AdminGroupVO getAdminGroup(int groupId);
	int modifyAdminGroup(AdminGroupVO adminGroupVO) throws Exception;
	int deleteAdminGroup(AdminGroupVO adminGroupVO) throws Exception;
}