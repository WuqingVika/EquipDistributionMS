package com.guanghua.edms.domain;

import java.io.Serializable;

public class JfzsSubRack implements Serializable{
	private Long subRackId;
	private Long equipId;
	private Integer orderNo;
	private Integer slotCount;
	private String label;
	public JfzsSubRack() {
	}
	//@Id
	//@Column(name = "SUB_RACK_ID", unique = true,nullable = false)
	public Long getSubRackId() {
		return subRackId;
	}
	public void setSubRackId(Long subRackId) {
		this.subRackId = subRackId;
	}
	//@Column(name = "EQUIP_ID")
	public Long getEquipId() {
		return equipId;
	}
	public void setEquipId(Long equipId) {
		this.equipId = equipId;
	}
	//@Column(name = "ORDER_NO")
	public Integer getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}
	//@Column(name = "SLOT_COUNT")
	public Integer getSlotCount() {
		return slotCount;
	}
	public void setSlotCount(Integer slotCount) {
		this.slotCount = slotCount;
	}
	//@Column(name = "LABEL")
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	//@Override
	public String toString() {
		return "JFZS_SUB_RACK [subRackId=" + subRackId + ", equipId=" + equipId
				+ ", orderNo=" + orderNo + ", slotCount=" + slotCount
				+ ", label=" + label + "]";
	}
	
}
