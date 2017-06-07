package com.spring.service.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import com.spring.model.admin.AdminGroup;
import com.spring.model.admin.AdminRoleAccess;
import com.spring.model.admin.AdminUser;
import com.spring.model.admin.AdminUserAuthentication;
import com.spring.model.admin.OperationRecord;

public interface AdminUserService {
	/*
	 * ��������Ա
	 * */
	Map<String, Object> createAdminUser(AdminUser adminUser, Locale locale) throws Exception;
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
	Map<String, Object> modifyPassword(AdminUserAuthentication adminUserAuthentication, Locale locale) throws Exception;
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
	List<AdminGroup> getAdminGroupSelectBox();
	/*
	 * �޸Ĺ���Ա��Ϣ��������
	 * */
	Map<String, Object> modifyAdminUser(AdminUser adminUser, Locale locale) throws Exception;
	/*
	 * �޸Ĺ���Ա��Ϣ��������
	 * */
	Map<String, Object> modifyAdminUserAndPassword(AdminUser adminUser, Locale locale) throws Exception;
	/*
	 * ������Ա�б�
	 * */
	List<AdminUser> getAdminUsers(AdminUser adminUser);
	/*
	 * ɾ�������˺�
	 * */
	Map<String, Object> deleteAdminUser(AdminUser adminUser, Locale locale) throws Exception;
	/*
	 * ����������
	 * */
	Map<String, Object> createAdminGroup(AdminGroup adminGroup, Locale locale) throws Exception;
	/*
	 * �������б�
	 * */
	List<AdminGroup> getAdminGroups();
	/*
	 * ��������Ϣ
	 * */
	AdminGroup getAdminGroup(int groupId);
	/*
	 * �޸Ĺ�����
	 * */
	Map<String, Object> modifyAdminGroup(AdminGroup adminGroup, Locale locale) throws Exception;
	/*
	 * ɾ��������
	 * */
	Map<String, Object> deleteAdminGroup(AdminGroup adminGroup, Locale locale) throws Exception;
	/*
	 * ����������¼
	 * */
	void createOperationRecord(String content, long createTime) throws Exception;
	/*
	 * ������¼�б�
	 * */
	List<OperationRecord> getOperationRecords(OperationRecord operationRecord);
	
}