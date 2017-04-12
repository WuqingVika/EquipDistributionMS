package com.guanghua.edms.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 设备管理员
 * @author wuqingvika
 *
 */
public class UserInfo implements Serializable{
	private static final long serialVersionUID = 1L;
	private Long userId;
	private String userName;
	private String password;
	private String workNo;
	private Integer sp;//是否为超级管理员
	private String code;//验证码
	private String email;//邮箱
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	/*public String getuState() {
		return uState;
	}

	public void setuState(String uState) {
		this.uState = uState;
	}*/
	private Integer uState;//状态
	
	public Integer getuState() {
		return uState;
	}

	public void setuState(Integer uState) {
		this.uState = uState;
	}

	public Integer getSp() {
		return sp;
	}

	public void setSp(Integer sp) {
		this.sp = sp;
	}

	public String getWorkNo() {
		return workNo;
	}
	
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public void setWorkNo(String workNo) {
		this.workNo = workNo;
	}
	
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", password=" + password + "]";
	}
	
}
