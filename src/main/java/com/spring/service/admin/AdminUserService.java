package com.spring.service.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import com.spring.model.admin.AdminRoleAccess;
import com.spring.model.admin.AdminUser;
import com.spring.model.admin.AdminUserAuthentication;

public interface AdminUserService {
	/*
	 * ��������Ա
	 * */
	Map<String, Object> createAdminUser(AdminUser adminUser) throws Exception;
	/*
	 * ���˻���ѯ���ڴ˹���Ա���
	 * */
	int hasAdminUserByUsername(String username);
	/*
	 * ���˻���ȡ����Ա��Ϣ
	 * */
	AdminUser getAdminUserByUsername(String username);
	/*
	 * ���˻���ȡ����Ա���� �˻�,���� �޸������ �Ƚ�������ʱ��
	 * */
	AdminUser getAdminUserAuthentication(AdminUserAuthentication adminUserAuthentication);
	/*
	 * �޸Ĺ���Ա����
	 * */
	Map<String, Object> modifyPassword(AdminUserAuthentication adminUserAuthentication) throws Exception;
	/*
	 * ���ù���Աҳ�����Ȩ��
	 * */
	void createAdminRoleAccess(AdminRoleAccess adminRoleAccess) throws Exception;
	/*
	 * ��ȡ����Աҳ�����Ȩ���б�
	 * */
	List<String> getAdminRoleAccessByUsername(String username);
	/*
	 * ��ȡ�������б�
	 * */
	List<HashMap<String,Object>> getAdminGroups();
	/*
	 * �޸Ĺ���Ա��Ϣ��������
	 * */
	Map<String, Object> modifyAdminUser(AdminUser adminUser) throws Exception;
	/*
	 * �޸Ĺ���Ա��Ϣ��������
	 * */
	Map<String, Object> modifyAdminUserAndPassword(AdminUser adminUser) throws Exception;
	/*
	 * ������Ա�б�
	 * */
	List<AdminUser> getAdminUsers(AdminUser adminUser);
	
}