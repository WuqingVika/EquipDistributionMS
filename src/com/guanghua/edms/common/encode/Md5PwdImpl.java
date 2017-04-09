package com.guanghua.edms.common.encode;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Hex;

public class Md5PwdImpl implements Md5Pwd {
	//加密
	public String encode(String password){
		String algorithm="MD5";//算法
		//加盐
		//password="wqwq"+password;
		MessageDigest instance=null;
		try {//MessageDigest.getInstance()参数是算法
			 instance = MessageDigest.getInstance(algorithm);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//加密
		byte[] digest = instance.digest(password.getBytes());
		//再十六进制加密下
		char[] encodeHex = Hex.encodeHex(digest);
		
		return new String(encodeHex);
	}
}
