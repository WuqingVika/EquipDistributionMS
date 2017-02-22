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
@Entity  
@Table(name = "user_info")  
public class User implements Serializable{
	private static final long serialVersionUID = 1L;
	private String userId;
	private String userName;
	private String password;
	private String workNo;
	
	@Column(name="WORK_NO")
	public String getWorkNo() {
		return workNo;
	}
	public void setWorkNo(String workNo) {
		this.workNo = workNo;
	}
	 @Id  
	 @Column(name = "USER_ID")  
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	@Column(name = "USER_NAME") 
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Column(name = "PASSWORD") 
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
