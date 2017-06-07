package com.spring.model.admin;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

public class AdminUserAuthentication {
	
	@NotBlank(message="{10001}")//�˺Ų���Ϊ��
	@Length(min=5,max=10,message="{10002}")//�˺ű�����5~12�������
	private String username = "";
	
	@Pattern(regexp="(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,12}",message="{10003}")//���������6~12λ���ֺ���ĸ�����
	private String password;
	
	@Pattern(regexp="(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,12}",message="{10003}")//���������6~12λ���ֺ���ĸ�����
	private String newPassword = "";
	
	private byte status;
	
	public AdminUserAuthentication() {
		super();
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
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public byte getStatus() {
		return status;
	}
	public void setStatus(byte status) {
		this.status = status;
	}
}