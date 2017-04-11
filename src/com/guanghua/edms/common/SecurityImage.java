package com.guanghua.edms.common;
import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Random;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
public class SecurityImage {
	/**
	 * 生成验证码的图片的格式
	 * @param secturityCode 验证码字符
	 * @return 返回图片
	 */
	public static BufferedImage createImage(String secturityCode) {
		// 验证码的长度
		int codeLength = secturityCode.length();
		// 字体的大小
		int fSize = 15;
		int fWidth = fSize + 1;
		// 图片的宽度
		int width = codeLength * fWidth + 6;
		// 图片的高度
		int height = fSize * 2 + 1;
		// 图片
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		java.awt.Graphics g = image.createGraphics();
		// 设置背景颜色
		g.setColor(Color.WHITE);
		// 设置填充
		g.fillRect(0, 0, width, height);
		// 设置边框颜色
		g.setColor(Color.LIGHT_GRAY);
		// 设置字体样式
		g.setFont(new Font("Arial", Font.BOLD, height - 2));
		// 绘制边框
		g.drawRect(0, 0, width - 1, height - 1);
		// 绘制噪点
		Random rand = new Random();
		// 设置噪点颜色
		g.setColor(Color.LIGHT_GRAY);
		for (int i = 0; i < codeLength * 6; i++) {
			int x = rand.nextInt(width);
			int y = rand.nextInt(height);
			// 绘制1*1大小的矩形
			g.drawRect(x, y, 1, 1);
		}
		// 绘制验证码
		int codeY = height - 10;
		// 设置字体颜色和样式
		g.setColor(new Color(19, 148, 246));
		g.setFont(new Font("Georgia", Font.BOLD, fSize));
		for (int i = 0; i < codeLength; i++) {
			g.drawString(String.valueOf(secturityCode.charAt(i)), i * 16 + 5, codeY);
		}
		// 关闭资源
		g.dispose();
		return image;
	}

	/**
	 * 返回验证码的图片流的格式
	 * @param securityCode 验证码
	 * @return 图片流
	 */
	public static ByteArrayInputStream getImageAsInputStream(String securityCode){
		BufferedImage image = createImage(securityCode);
		return converImageToInputStream(image);
	}
	
	/**
	 * 将BufferedImage转化为ByteArrayInputStream
	 * @param image 图片
	 * @return 返回一个ByteArrayInputStream流
	 */
	public static ByteArrayInputStream converImageToInputStream(BufferedImage image){
		ByteArrayInputStream inputStream = null;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		JPEGImageEncoder jpeg = JPEGCodec.createJPEGEncoder(bos);
		try {
			jpeg.encode(image);
			byte[] bts = bos.toByteArray();
			inputStream = new ByteArrayInputStream(bts);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return inputStream;		
	}
}
