package com.guanghua.edms.common;

import com.guanghua.edms.common.web.CustomException;

public class UserInvalidException extends CustomException {

	public UserInvalidException(String msg) {
		super(msg);
	}

}
