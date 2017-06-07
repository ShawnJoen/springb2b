package com.spring.model.admin;

public class OperationRecord {
	
	private int id;
	private String username;
	private String content;
	private long createTime;
	
	//管理人员列表搜索
	private String keywords = "";
	public OperationRecord() {
		super();
	}
	public OperationRecord(String username, String content, long createTime) {
		super();
		this.username = username;
		this.content = content;
		this.createTime = createTime;
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
	public long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	
}