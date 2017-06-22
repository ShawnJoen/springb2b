package com.spring.dto.admin;

import java.util.Date;

public class OperationRecord {
	
	private int id;
	private String username;
	private String content;
	private Date createTime;
	
	public OperationRecord() {
		super();
	}
	public OperationRecord(String username, String content) {
		super();
		this.username = username;
		this.content = content;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}