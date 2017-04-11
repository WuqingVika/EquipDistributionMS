package com.guanghua.edms.common;

import java.util.Arrays;

public class SecurityCode {
	/**
	 * 验证码的难度级别
	 */
	public enum SecurityCodeLevel {
		Simple, Medium, Hard
	};

	
	/**
	 * 产生默认的验证码
	 * @return
	 */
	public static String getSecturityCode(){
		return getSecutrityCode(4, SecurityCodeLevel.Medium, false);
	}
	
	
	/**
	 * 
	 * 产生任意长度的验证码
	 * @param length 长度
	 * @param level 难度级别
	 * @param isCanRepeat 是否能够出现重复的字符
	 * @return string 验证码
	 */
	public static String getSecutrityCode(int length, SecurityCodeLevel level, boolean isCanRepeat) {
		// 随机抽取len个字符
		int len = length;
		// 字符集合
		char[] codes = { '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
				'k', 'm', 'n', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G',
				'H', 'I', 'J', 'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
		// 根据不同难度截取字符数组
		if (level == SecurityCodeLevel.Simple) {
			codes = Arrays.copyOfRange(codes, 0, 9);
		} else if (level == SecurityCodeLevel.Medium) {
			codes = Arrays.copyOfRange(codes, 0, 33);
		}

		// 字符集和长度
		int n = codes.length;
		// 抛出运行时异常
		if (len > n && isCanRepeat == false) {
			throw new RuntimeException(String.format(
					"调用SecurityCode.getSecurityCode(%1$s,%2$s,%3$s)出现异常，当isCanRepeat为%3$s时，传入参数%1$s不能大于%4$s", len,
					level, isCanRepeat, n));
		}
		//存放抽取出来的字符
		char[] result = new char[len];
		//判断能否出现重复的字符
		if(isCanRepeat){
			for(int i = 0;i<result.length;i++){
				int r = (int)(Math.random()*n);
				//存入result
				result[i] = codes[r];
			}
		}else{
			for(int i=0;i<result.length;i++){
				int r = (int)(Math.random()*n);
				result[i] = codes[r];
				//为确保不同将字符串的最后一个字符放到该位置上，并把长度减去一
				codes[r] = codes[n-1];
				n--;
			}
		}
		return String.valueOf(result);		
	}
}
