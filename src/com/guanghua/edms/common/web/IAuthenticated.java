package com.guanghua.edms.common.web;

import java.io.Serializable;

public interface IAuthenticated extends Serializable {
	
	public static final String AUTHENTICATED_KEY = "globe.authenticated";
	
	public Long getId();
	
	public String getName();
	
	public String getChName();

}
