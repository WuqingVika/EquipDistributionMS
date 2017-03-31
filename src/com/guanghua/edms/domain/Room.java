package com.guanghua.edms.domain;

import java.io.Serializable;

public class Room implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long regionId;
	private Long roomId;
	private String roomNo;
	private Integer roFloor;
	private String roUsage;
	private String propertyRight;
	private String roState;
	public Long getRegionId() {
		return regionId;
	}
	public void setRegionId(Long regionId) {
		this.regionId = regionId;
	}
	public Long getRoomId() {
		return roomId;
	}
	public void setRoomId(Long roomId) {
		this.roomId = roomId;
	}
	public String getRoomNo() {
		return roomNo;
	}
	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}
	public Integer getRoFloor() {
		return roFloor;
	}
	public void setRoFloor(Integer roFloor) {
		this.roFloor = roFloor;
	}
	public String getRoUsage() {
		return roUsage;
	}
	public void setRoUsage(String roUsage) {
		this.roUsage = roUsage;
	}
	public String getPropertyRight() {
		return propertyRight;
	}
	public void setPropertyRight(String propertyRight) {
		this.propertyRight = propertyRight;
	}
	public String getRoState() {
		return roState;
	}
	public void setRoState(String roState) {
		this.roState = roState;
	}
	
}
