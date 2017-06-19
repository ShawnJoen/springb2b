package com.spring.dto.admin;

import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

public class AdminUser {

	private int id;
	
	@Min(value=1, message="{10007}")//��ѡ�������
	private int groupId;
	
	@NotBlank(message="{10001}")//�˺Ų���Ϊ��
	@Length(min=5,max=10,message="{10002}")//�˺ű�����5~12�������
	private String username = "";
	
	@Pattern(regexp="(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,12}",message="{10003}")//���������6~12λ���ֺ���ĸ�����
	private String password = "";
	
	@NotBlank(message="{10004}")//��ϵ�˵绰����Ϊ��
	@Pattern(regexp="1\\d{10}",message="{10005}")//��ϵ�˵绰��ʽ����
	private String contactMobile = "";
	
	@NotBlank(message="{10006}")//��ϵ�����Ʋ���Ϊ��
	private String contactName = "";
	private String loginTime;
	private String updateTime;
	private String createTime;
	private int status;
	private String loginIp;
	private int isDel;
	
	//����admin_group
	private String groupName;
	//������Ա�б�����
	private String keywords = "";
	
	public AdminUser() {
		super();
	}
	public AdminUser(String username, String contactMobile, String contactName, String createTime, int status) {
		this.username = username;
		this.contactMobile = contactMobile;
		this.contactName = contactName;
		this.createTime = createTime;
		this.status = status;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getContactMobile() {
		return contactMobile;
	}
	public void setContactMobile(String contactMobile) {
		this.contactMobile = contactMobile;
	}
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	public String getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getIsDel() {
		return isDel;
	}
	public void setIsDel(int isDel) {
		this.isDel = isDel;
	}
	public String getLoginIp() {
		return loginIp;
	}
	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
}