package com.spring.vo.admin;

import java.util.Date;

public class OperationRecordVO {
	
	private int id;
	private String username;
	private String content;
	private Date createTime;
	
	//管理人员列表搜索
	private String keywords = "";
	
	public OperationRecordVO() {
		super();
	}
	public OperationRecordVO(String username, String content) {
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
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	
}