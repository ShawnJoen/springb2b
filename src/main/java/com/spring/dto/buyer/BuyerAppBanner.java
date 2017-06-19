package com.spring.dto.buyer;

import java.util.Date;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.NotBlank;

public class BuyerAppBanner {
	
	private int adId;
	@NotBlank(message="{10009}")//广告名称不能为空
	private String adName = "";
	private String adImage = "";
	@Min(value=0,message="{10010}")//排序编号必须输入0~255范围数值
	@Max(value=255,message="{10010}")//排序编号必须输入0~255范围数值
	private int sort;
	@Min(value=0,message="{10011}")//请选择状态
	@Max(value=1,message="{10011}")//请选择状态
	private int status = 1;
	private Date createTime;
	private int isDel;
	
	public int getAdId() {
		return adId;
	}
	public void setAdId(int adId) {
		this.adId = adId;
	}
	public String getAdName() {
		return adName;
	}
	public void setAdName(String adName) {
		this.adName = adName;
	}
	public String getAdImage() {
		return adImage;
	}
	public void setAdImage(String adImage) {
		this.adImage = adImage;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public int getIsDel() {
		return isDel;
	}
	public void setIsDel(int isDel) {
		this.isDel = isDel;
	}
}