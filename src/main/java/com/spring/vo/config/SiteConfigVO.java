package com.spring.vo.config;

import org.hibernate.validator.constraints.NotBlank;

public class SiteConfigVO {

	@NotBlank(message="{10012}")//配置编码不能为空
	private String configCode = "";
	private String groupCode;
	private String configName;
	@NotBlank(message="{10013}")//内容不能为空
	private String configVal = "";
	private String editType;
	private int isShow = 1;
	private int sort;
	
	public SiteConfigVO() {
		super();
	}
	public SiteConfigVO(String configCode) {
		this.configCode = configCode;
	}
	public SiteConfigVO(String configCode, String configVal) {
		this.configCode = configCode;
		this.configVal = configVal;
	}
	public String getConfigCode() {
		return configCode;
	}
	public void setConfigCode(String configCode) {
		this.configCode = configCode;
	}
	public String getGroupCode() {
		return groupCode;
	}
	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}
	public String getConfigName() {
		return configName;
	}
	public void setConfigName(String configName) {
		this.configName = configName;
	}
	public String getConfigVal() {
		return configVal;
	}
	public void setConfigVal(String configVal) {
		this.configVal = configVal;
	}
	public String getEditType() {
		return editType;
	}
	public void setEditType(String editType) {
		this.editType = editType;
	}
	public int getIsShow() {
		return isShow;
	}
	public void setIsShow(int isShow) {
		this.isShow = isShow;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
}