package com.spring.dto.config;

import org.hibernate.validator.constraints.NotBlank;

public class ErrorLog {

	private int id;
	private int userId = 0;
	@NotBlank(message="{10021}")//用户类型不能为空
	private String userType = "";
	@NotBlank(message="{10022}")//设备类型不能为空
	private String deviceType = "";
	@NotBlank(message="{10023}")//设备ID不能为空
	private String deviceId = "";
	@NotBlank(message="{10024}")//品牌不能为空
	private String brandName = "";
	@NotBlank(message="{10025}")//系统版本不能为空
	private String osVersion = "";
	@NotBlank(message="{10026}")//APP版本不能为空
	private String appVersion = "";
	@NotBlank(message="{10027}")//错误内容不能为空
	private String error = "";
	private String projectName;
	private String createTime;

	public ErrorLog() {
		super();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getOsVersion() {
		return osVersion;
	}

	public void setOsVersion(String osVersion) {
		this.osVersion = osVersion;
	}

	public String getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
}