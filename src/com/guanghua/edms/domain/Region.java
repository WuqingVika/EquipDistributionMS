package com.guanghua.edms.domain;

import java.io.Serializable;

public class Region  implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long regionId;
	private String regionName;
	private String address;
	private String propertyRight;
	private Integer reFloor;
	private String reUsage;
	private String reState;
	private String reAddress;
	public Long getRegionId() {
		return regionId;
	}
	public void setRegionId(Long regionId) {
		this.regionId = regionId;
	}
	public String getRegionName() {
		return regionName;
	}
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPropertyRight() {
		return propertyRight;
	}
	public void setPropertyRight(String propertyRight) {
		this.propertyRight = propertyRight;
	}
	public Integer getReFloor() {
		return reFloor;
	}
	public void setReFloor(Integer reFloor) {
		this.reFloor = reFloor;
	}
	public String getReUsage() {
		return reUsage;
	}
	public void setReUsage(String reUsage) {
		this.reUsage = reUsage;
	}
	public String getReState() {
		return reState;
	}
	public void setReState(String reState) {
		this.reState = reState;
	}
	public String getReAddress() {
		return reAddress;
	}
	public void setReAddress(String reAddress) {
		this.reAddress = reAddress;
	}
	
}
