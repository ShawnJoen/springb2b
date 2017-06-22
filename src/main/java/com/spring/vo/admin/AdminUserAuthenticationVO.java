package com.spring.vo.admin;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

public class AdminUserAuthenticationVO {
	
	@NotBlank(message="{10001}")//账号不能为空
	@Length(min=5,max=10,message="{10002}")//账号必须由5~12个字组成
	private String username = "";
	
	@Pattern(regexp="(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,12}",message="{10003}")//密码必须是6~12位数字和字母的组合
	private String password;
	
	@Pattern(regexp="(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,12}",message="{10003}")//密码必须是6~12位数字和字母的组合
	private String newPassword = "";
	
	private int status;
	
	private String updateTime;
	
	public AdminUserAuthenticationVO() {
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
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	
}