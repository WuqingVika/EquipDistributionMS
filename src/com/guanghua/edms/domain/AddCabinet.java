package com.guanghua.edms.domain;

public class AddCabinet {
	private int roomId;
	private int type;
	private String company;
	private String cabinet_num;
	private String cabinet_name;
	private String cabinet_surface;
	private int spec_id;
	private String assert_no;
	private String layerCount;
	private String pos_x;
	private String pos_y;
	private String label;
	private String power_a;
	public int getRoomId() {
		return roomId;
	}
	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getCabinet_name() {
		return cabinet_name;
	}
	public void setCabinet_name(String cabinet_name) {
		this.cabinet_name = cabinet_name;
	}
	public String getAssert_no() {
		return assert_no;
	}
	public void setAssert_no(String assert_no) {
		this.assert_no = assert_no;
	}
	public String getPower_a() {
		return power_a;
	}
	public void setPower_a(String power_a) {
		this.power_a = power_a;
	}
	public String getPower_b() {
		return power_b;
	}
	public void setPower_b(String power_b) {
		this.power_b = power_b;
	}
	private String power_b;
	public String getCabinet_num() {
		return cabinet_num;
	}
	public void setCabinet_num(String cabinet_num) {
		this.cabinet_num = cabinet_num;
	}
	public String getCabinet_surface() {
		return cabinet_surface;
	}
	public void setCabinet_surface(String cabinet_surface) {
		this.cabinet_surface = cabinet_surface;
	}
	public int getSpec_id() {
		return spec_id;
	}
	public void setSpec_id(int spec_id) {
		this.spec_id = spec_id;
	}
	public String getLayerCount() {
		return layerCount;
	}
	public void setLayerCount(String layerCount) {
		this.layerCount = layerCount;
	}
	public String getPos_x() {
		return pos_x;
	}
	public void setPos_x(String pos_x) {
		this.pos_x = pos_x;
	}
	public String getPos_y() {
		return pos_y;
	}
	public void setPos_y(String pos_y) {
		this.pos_y = pos_y;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
}
