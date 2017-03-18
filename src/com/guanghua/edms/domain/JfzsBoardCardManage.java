package com.guanghua.edms.domain;
public class JfzsBoardCardManage {
	private Long cardId;//CARD_ID;
    private Long subRackId;//SUB_RACK_ID
	private Integer occupySlotNum;//OCCUPY_SLOT_NUM
	private String manufacturer;//MANUFACTURER
	private String purpose;//PURPOSE
	private String category;//CATEGORY
	private String model;//MODEL
	private String assetNo;//ASSET_NO
	private Integer posIdx;//POS_IDX
	private String changeDate;//CHANGE_DATE
	
	//@Id
	//@Column(name = "CARD_ID", unique = true,nullable = false)
	public Long getCardId() {
		return cardId;
	}
	public void setCardId(Long cardId) {
		this.cardId = cardId;
	}
	//@Column(name = "SUB_RACK_ID")
	public Long getSubRackId() {
		return subRackId;
	}
	public void setSubRackId(Long subRackId) {
		this.subRackId = subRackId;
	}
	//@Column(name = "OCCUPY_SLOT_NUM")
	public Integer getOccupySlotNum() {
		return occupySlotNum;
	}
	public void setOccupySlotNum(Integer occupySlotNum) {
		this.occupySlotNum = occupySlotNum;
	}
	//@Column(name = "MANUFACTURER")
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	//@Column(name = "PURPOSE")
	public String getPurpose() {
		return purpose;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	//@Column(name = "CATEGORY")
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	//@Column(name = "MODEL")
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	//@Column(name = "ASSET_NO")
	public String getAssetNo() {
		return assetNo;
	}
	public void setAssetNo(String assetNo) {
		this.assetNo = assetNo;
	}
	//@Column(name = "POS_IDX")
	public Integer getPosIdx() {
		return posIdx;
	}
	public void setPosIdx(Integer posIdx) {
		this.posIdx = posIdx;
	}
	//@Column(name = "CHANGE_DATE")
	public String getChangeDate() {
		return changeDate;
	}
	public void setChangeDate(String changeDate) {
		this.changeDate = changeDate;
	}
	//@Override
	public String toString() {
		return "JfzsBoardCardManage [cardId=" + cardId + ", subRackId="
				+ subRackId + ", occupySlotNum=" + occupySlotNum
				+ ", manufacturer=" + manufacturer + ", purpose=" + purpose
				+ ", category=" + category + ", model=" + model + ", assetNo="
				+ assetNo + ", posIdx=" + posIdx + ", changeDate=" + changeDate
				+ "]";
	}
}
