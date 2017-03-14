package com.guanghua.edms.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;

public class JfzsEquipment implements Serializable{
	private Long equipId;//EQUIP_ID 
	private Long cabinetId;//CABINET_ID
	private Integer cabinetLayerNum;//CABINET_LAYER_NUM
	private Integer occupyLayerNum;//OCCUPY_LAYER_NUM
	private String nuNum;//NU_NUM
	private String equipName;//EQUIP_NAME
	private String manufacturer;//MANUFACTURER
	private String cabinetSurface;//CABINET_SURFACE
	private Integer specId;
	private String category;
	private String model;
	private String assertNo;
	private Integer subRackCount;//SUB_RACK_COUNT
	private String versionInfo;
	public JfzsEquipment() {
		
	}
	@Id
	//@GeneratedValue(generator = "SEQ", strategy = GenerationType.AUTO)
	@Column(name = "EQUIP_ID", unique = true,nullable = false)
	public Long getEquipId() {
		return equipId;
	}
	public void setEquipId(Long equipId) {
		this.equipId = equipId;
	}
	@Column(name="CABINET_ID")
	public Long getCabinetId() {
		return cabinetId;
	}
	public void setCabinetId(Long cabinetId) {
		this.cabinetId = cabinetId;
	}
	@Column(name="CABINET_LAYER_NUM")
	public Integer getCabinetLayerNum() {
		return cabinetLayerNum;
	}
	public void setCabinetLayerNum(Integer cabinetLayerNum) {
		this.cabinetLayerNum = cabinetLayerNum;
	}
	@Column(name="OCCUPY_LAYER_NUM")
	public Integer getOccupyLayerNum() {
		return occupyLayerNum;
	}
	public void setOccupyLayerNum(Integer occupyLayerNum) {
		this.occupyLayerNum = occupyLayerNum;
	}
	@Column(name="NU_NUM")
	public String getNuNum() {
		return nuNum;
	}
	public void setNuNum(String nuNum) {
		this.nuNum = nuNum;
	}
	@Column(name="EQUIP_NAME")
	public String getEquipName() {
		return equipName;
	}
	public void setEquipName(String equipName) {
		this.equipName = equipName;
	}
	@Column(name="MANUFACTURER")
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	@Column(name="CABINET_SURFACE")
	public String getCabinetSurface() {
		return cabinetSurface;
	}
	public void setCabinetSurface(String cabinetSurface) {
		this.cabinetSurface = cabinetSurface;
	}
	@Column(name="SPEC_ID")
	public Integer getSpecId() {
		return specId;
	}
	public void setSpecId(Integer specId) {
		this.specId = specId;
	}
	@Column(name="CATEGORY")
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	@Column(name="MODEL")
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	@Column(name="ASSET_NO")
	public String getAssertNo() {
		return assertNo;
	}
	public void setAssertNo(String assertNo) {
		this.assertNo = assertNo;
	}
	@Column(name="SUB_RACK_COUNT")
	public Integer getSubRackCount() {
		return subRackCount;
	}
	public void setSubRackCount(Integer subRackCount) {
		this.subRackCount = subRackCount;
	}
	@Column(name="VERSION_INFO")
	public String getVersionInfo() {
		return versionInfo;
	}
	public void setVersionInfo(String versionInfo) {
		this.versionInfo = versionInfo;
	}
	@Override
	public String toString() {
		return "JFZS_Equipment [equipId=" + equipId + ", cabinetId="
				+ cabinetId + ", cabinetLayerNum=" + cabinetLayerNum
				+ ", occupyLayerNum=" + occupyLayerNum + ", nuNum=" + nuNum
				+ ", equipName=" + equipName + ", manufacturer=" + manufacturer
				+ ", cabinetSurface=" + cabinetSurface + ", specId=" + specId
				+ ", category=" + category + ", model=" + model + ", assertNo="
				+ assertNo + ", subRackCount=" + subRackCount
				+ ", versionInfo=" + versionInfo + "]";
	}
}
