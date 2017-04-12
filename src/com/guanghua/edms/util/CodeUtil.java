package com.guanghua.edms.util;

import java.util.UUID;

/**
 * 2017/4/12
 * @author wuqingvika
 *
 *	生成唯一的邮箱重置码
 */
public class CodeUtil {
	//生成唯一的邮箱重置码
	public static String generateUniqueCode(){
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
}
