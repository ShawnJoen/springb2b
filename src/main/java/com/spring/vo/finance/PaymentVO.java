package com.spring.vo.finance;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("paymentVO")
public class PaymentVO implements Serializable {

	private static final long serialVersionUID = -6419094382720377571L;
	private String paymentCode;
	private String paymentName;
	private String paymentConfig;
	private int paymentStatus = -1;
	private String paymentType;
	private String paymentIcon;
	private String isDefault;
	private int sort;
	public PaymentVO() {
		super();
	}
	
	public PaymentVO(int paymentStatus) {
		super();
		this.paymentStatus = paymentStatus;
	}

	public String getPaymentCode() {
		return paymentCode;
	}
	public void setPaymentCode(String paymentCode) {
		this.paymentCode = paymentCode;
	}
	public String getPaymentName() {
		return paymentName;
	}
	public void setPaymentName(String paymentName) {
		this.paymentName = paymentName;
	}
	public String getPaymentConfig() {
		return paymentConfig;
	}
	public void setPaymentConfig(String paymentConfig) {
		this.paymentConfig = paymentConfig;
	}
	public int getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(int paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	public String getPaymentIcon() {
		return paymentIcon;
	}
	public void setPaymentIcon(String paymentIcon) {
		this.paymentIcon = paymentIcon;
	}
	public String getIsDefault() {
		return isDefault;
	}
	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
}