package com.su.product;

import com.su.annotation.NotEmpty;

/**
 * 应答报文头
 * @author su
 */
public abstract class AnswerHead  {
	@NotEmpty(message = "request_type can't be empty")
	protected String request_type; 						/** 请求类型 **/
	@NotEmpty(message = "agent_code can't be empty")
	protected String agent_code;							/** 商户号 固定37424055 **/
	@NotEmpty(message = "trn_code can't be empty")
	protected String trn_code;								/** 代理商户号 **/
	@NotEmpty
	protected String front_traceno;						/** 前端流水号， 当天不重复 **/
	@NotEmpty
	protected String front_date;							/** MMDD **/
	@NotEmpty
	protected String front_time;							/** HH24MISS **/
	
	public abstract String toXML() ;
	
	
	
	
	
	//getter and setter
	public String getRequest_type() {
		return request_type;
	}
	public void setRequest_type(String request_type) {
		this.request_type = request_type;
	}
	public String getAgent_code() {
		return agent_code;
	}
	public void setAgent_code(String agent_code) {
		this.agent_code = agent_code;
	}
	public String getTrn_code() {
		return trn_code;
	}
	public void setTrn_code(String trn_code) {
		this.trn_code = trn_code;
	}
	public String getFront_traceno() {
		return front_traceno;
	}
	public void setFront_traceno(String front_traceno) {
		this.front_traceno = front_traceno;
	}
	public String getFront_date() {
		return front_date;
	}
	public void setFront_date(String front_date) {
		this.front_date = front_date;
	}
	public String getFront_time() {
		return front_time;
	}
	public void setFront_time(String front_time) {
		this.front_time = front_time;
	}
	
	
	
	
}
