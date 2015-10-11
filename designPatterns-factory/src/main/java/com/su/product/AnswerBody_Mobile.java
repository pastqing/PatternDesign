package com.su.product;

public class AnswerBody_Mobile extends AnswerHead {
	
	private String unit_name;		/** 交费公司名称 **/
	private String cust_id;			/** 缴费号码 **/
	private String cust_name;		/** 姓名 **/
	private String cust_addr;		/** 地址 **/
	private String fee_firmon;		/** 欠费起始月份 **/
	private String fee_amount;		/** 应缴金额 **/
	private String pay_mode_desc;	/** 缴费方式 **/
	private String pay_amount;		/** 缴费金额 **/
	private String dsftishi;			/** 缴费方式提示 **/
	private String dsf_filler;		/** 隐含项目 **/
	
	public String toXML() {
		System.out.println("AnswerBody_Mobile to xml");
		return null;
	}
	
	
	//getter and setter
	
	public String getUnit_name() {
		return unit_name;
	}
	public void setUnit_name(String unit_name) {
		this.unit_name = unit_name;
	}
	public String getCust_id() {
		return cust_id;
	}
	public void setCust_id(String cust_id) {
		this.cust_id = cust_id;
	}
	public String getCust_name() {
		return cust_name;
	}
	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}
	public String getCust_addr() {
		return cust_addr;
	}
	public void setCust_addr(String cust_addr) {
		this.cust_addr = cust_addr;
	}
	public String getFee_firmon() {
		return fee_firmon;
	}
	public void setFee_firmon(String fee_firmon) {
		this.fee_firmon = fee_firmon;
	}
	public String getFee_amount() {
		return fee_amount;
	}
	public void setFee_amount(String fee_amount) {
		this.fee_amount = fee_amount;
	}
	public String getPay_mode_desc() {
		return pay_mode_desc;
	}
	public void setPay_mode_desc(String pay_mode_desc) {
		this.pay_mode_desc = pay_mode_desc;
	}
	public String getPay_amount() {
		return pay_amount;
	}
	public void setPay_amount(String pay_amount) {
		this.pay_amount = pay_amount;
	}
	public String getDsftishi() {
		return dsftishi;
	}
	public void setDsftishi(String dsftishi) {
		this.dsftishi = dsftishi;
	}
	public String getDsf_filler() {
		return dsf_filler;
	}
	public void setDsf_filler(String dsf_filler) {
		this.dsf_filler = dsf_filler;
	}
}
