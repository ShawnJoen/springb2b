package com.spring.domain;

import java.util.Date;

public class AdminUserVO {

	private int id;
	private int rid;
	private String login_account;
	private char contact_mobile;
	private String contact_name;
	private char crypt;
	private char pw;
	private long login_time;
	private long create_time;
	private int status;
	private String login_ip;
	
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
	public String getLogin_account() {
		return login_account;
	}
	public void setLogin_account(String login_account) {
		this.login_account = login_account;
	}
	public char getContact_mobile() {
		return contact_mobile;
	}
	public void setContact_mobile(char contact_mobile) {
		this.contact_mobile = contact_mobile;
	}
	public String getContact_name() {
		return contact_name;
	}
	public void setContact_name(String contact_name) {
		this.contact_name = contact_name;
	}
	public char getCrypt() {
		return crypt;
	}
	public void setCrypt(char crypt) {
		this.crypt = crypt;
	}
	public char getPw() {
		return pw;
	}
	public void setPw(char pw) {
		this.pw = pw;
	}
	public long getLogin_time() {
		return login_time;
	}
	public void setLogin_time(long login_time) {
		this.login_time = login_time;
	}
	public long getCreate_time() {
		return create_time;
	}
	public void setCreate_time(long create_time) {
		this.create_time = create_time;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getLogin_ip() {
		return login_ip;
	}
	public void setLogin_ip(String login_ip) {
		this.login_ip = login_ip;
	}
	@Override
	public String toString() {
		return "AdminUserVO [id=" + id + ", rid=" + rid + ", login_account=" + login_account + ", contact_mobile="
				+ contact_mobile + ", contact_name=" + contact_name + ", crypt=" + crypt + ", pw=" + pw
				+ ", login_time=" + login_time + ", create_time=" + create_time + ", status=" + status + ", login_ip="
				+ login_ip + "]";
	}
}
