package com.spring.dto.finance;

public class Payment {

	private String paymentCode;
	private String paymentName;
	private String paymentConfig;
	private int paymentStatus;
	private String paymentType;
	private int sort;
	public Payment() {
		super();
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
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	
}