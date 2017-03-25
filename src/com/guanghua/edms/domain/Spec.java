package com.guanghua.edms.domain;

import java.io.Serializable;
/**
 * 2017/03/25 15:11
 * @author wuqingvika
 *
 */
public class Spec  implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long specId;
	private String specName;
	public Long getSpecId() {
		return specId;
	}
	public void setSpecId(Long specId) {
		this.specId = specId;
	}
	public String getSpecName() {
		return specName;
	}
	public void setSpecName(String specName) {
		this.specName = specName;
	}
	
}
