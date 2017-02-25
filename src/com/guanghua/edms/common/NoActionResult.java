package com.guanghua.edms.common;

import org.apache.struts2.dispatcher.StrutsResultSupport;

import com.opensymphony.xwork2.ActionInvocation;

public class NoActionResult extends StrutsResultSupport{

	 @Override
    protected void doExecute(String finalLocation, ActionInvocation invocation) throws Exception {
    }

}
