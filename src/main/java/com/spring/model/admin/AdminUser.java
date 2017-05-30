package com.spring.model.admin;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

public class AdminUser {

	private int id;
	private int rid;
	
	@NotBlank(message="�˺Ų���Ϊ��")
	@Length(min=5,max=10,message="�˺ű�����5~12�������")
	private String username = "";
	
	@Pattern(regexp="(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,12}",message="���������6~12λ���ֺ���ĸ�����")
	private String password;
	
	@NotBlank(message="��ϵ�˵绰����Ϊ��")
	@Pattern(regexp="1\\d{10}",message="��ϵ�˵绰��ʽ����")
	private String contactMobile = "";
	
	@NotBlank(message="��ϵ�����Ʋ���Ϊ��")
	private String contactName = "";
	
	private long loginTime;
	private long createTime;
	private int status;
	private String loginIp;
	//private Set<AdminRoleAccess> adminRoleAccess = new HashSet<>();
	
	public AdminUser() {
		super();
	}
	public AdminUser(String username, String contactMobile, String contactName, long createTime, int status) {
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
	public int getRid() {
		return rid;
	}
	public void setRid(int rid) {
		this.rid = rid;
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
	public long getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(long loginTime) {
		this.loginTime = loginTime;
	}
	public long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getLoginIp() {
		return loginIp;
	}
	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}
	/*public Set<AdminRoleAccess> getAdminRoleAccess() {
		return adminRoleAccess;
	}
	public void setAdminRoleAccess(Set<AdminRoleAccess> adminRoleAccess) {
		this.adminRoleAccess = adminRoleAccess;
	}*/
	
	
	
	
}
