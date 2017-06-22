package com.spring.vo.goods;

import javax.validation.constraints.Min;
import org.hibernate.validator.constraints.NotBlank;

public class GoodsStandardVO {

	//@Pattern(regexp="\\d+",message="{10020}")//商品编号必须为数值
	private String goodsStandardId = "";
	
	@NotBlank(message="{10014}")//商品分类不能为空
	private String cateId;
	
	//@NotBlank(message="{10015}")//箱入数不能为空
	@Min(value=1,message="{10016}")//请输入箱入数
	private int boxNum = 0;
	
	@NotBlank(message="{10017}")//商品名称不能为空
	private String goodsName = "";
	
	@NotBlank(message="{10018}")//商品规格不能为空
	private String goodsNorms = "";
	private String goodsBrief = "";
	
	//@NotBlank(message="{10019}")//商品图不能为空
	private String goodsImages = "";
	private String goodsBarcode = "";
	private int goodsStatus;
	private int isDel;
	private String createTime;
	private String updateTime;
	
	//录入时间搜索
	private String startCreateDate = "";
	private String endCreateDate = "";

	public GoodsStandardVO() {
		super();
	}
	
	public String getGoodsStandardId() {
		return goodsStandardId;
	}
	public void setGoodsStandardId(String goodsStandardId) {
		this.goodsStandardId = goodsStandardId;
	}
	public String getCateId() {
		return cateId;
	}
	public void setCateId(String cateId) {
		this.cateId = cateId;
	}
	public int getBoxNum() {
		return boxNum;
	}
	public void setBoxNum(int boxNum) {
		this.boxNum = boxNum;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getGoodsNorms() {
		return goodsNorms;
	}
	public void setGoodsNorms(String goodsNorms) {
		this.goodsNorms = goodsNorms;
	}
	public String getGoodsBrief() {
		return goodsBrief;
	}
	public void setGoodsBrief(String goodsBrief) {
		this.goodsBrief = goodsBrief;
	}
	public String getGoodsImages() {
		return goodsImages;
	}
	public void setGoodsImages(String goodsImages) {
		this.goodsImages = goodsImages;
	}
	public String getGoodsBarcode() {
		return goodsBarcode;
	}
	public void setGoodsBarcode(String goodsBarcode) {
		this.goodsBarcode = goodsBarcode;
	}
	public int getGoodsStatus() {
		return goodsStatus;
	}
	public void setGoodsStatus(int goodsStatus) {
		this.goodsStatus = goodsStatus;
	}
	public int getIsDel() {
		return isDel;
	}
	public void setIsDel(int isDel) {
		this.isDel = isDel;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public String getStartCreateDate() {
		return startCreateDate;
	}
	public void setStartCreateDate(String startCreateDate) {
		this.startCreateDate = startCreateDate;
	}
	public String getEndCreateDate() {
		return endCreateDate;
	}
	public void setEndCreateDate(String endCreateDate) {
		this.endCreateDate = endCreateDate;
	}
	
}