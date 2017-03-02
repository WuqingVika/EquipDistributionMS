package com.guanghua.edms.util;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.OutputStream;
import java.util.Hashtable;
import java.util.Random;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

/**
 * 二维码工具类
 * 
 */
public class QRCodeUtil {
	private static final String CHARSET = "utf-8";
	private static final String FORMAT_NAME = "JPG";
	// 二维码尺寸宽
	private static final int QRCODE_SIZE = 300;
	// 二维码尺寸宽
	private static final int IMAGE_SIZE_W = 300;
	// 二维码尺寸高
	private static final int IMAGE_SIZE_H = 430;
	// title位置高
	private static final int TITLE_POSITION_H = 330;
	// title位置高
	private static final int TITLE_POSITION_H_1 = 350;
	// title位置高
//	private static final int TITLE_POSITION_H_2 = 370;
	// title位置高
//	private static final int TITLE_POSITION_H_3 = 390;
	// title位置高
	//modify by jgyang 2014-11-27 begine
	//private static final int TITLE_POSITION_H_4 = 410;
	private static final int TITLE_POSITION_H_4 = 370;
	//modify by jgyang 2014-11-27 end
	// title font size
	private static final int TITLE_FONT_SIZE = 20;
	// LOGO宽度
	private static final int WIDTH = 60;
	// LOGO高度
	private static final int HEIGHT = 60;

	private static BufferedImage createImage(String content, String title, String imgPath,
			boolean needCompress) throws Exception {
		Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
		hints.put(EncodeHintType.CHARACTER_SET, CHARSET);
		hints.put(EncodeHintType.MARGIN, 1);
		BitMatrix bitMatrix = new MultiFormatWriter().encode(content,
				BarcodeFormat.QR_CODE, QRCODE_SIZE, QRCODE_SIZE, hints);
		int width = title==null?QRCODE_SIZE:IMAGE_SIZE_W;
		int height = title==null?QRCODE_SIZE:IMAGE_SIZE_H;
		System.out.println("title create--"+title);
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				if (y < QRCODE_SIZE)
					image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
				else
					image.setRGB(x, y, 0xFFFFFFFF);
			}
		}
		
		if (imgPath == null || "".equals(imgPath)) {
			return image;
		}
		// 插入图片
		QRCodeUtil.insertImage(image, title, imgPath, needCompress);
		return image;
	}

	/**
	 * 插入LOGO
	 * 
	 * @param source
	 *            二维码图片
	 * @param imgPath
	 *            LOGO图片地址
	 * @param needCompress
	 *            是否压缩
	 * @throws Exception
	 */
	private static void insertImage(BufferedImage source, String title, String imgPath,
			boolean needCompress) throws Exception {
		System.out.println("title  insertImg--"+title);
		File file = new File(imgPath);
		if (!file.exists()) {
			System.err.println(""+imgPath+"   该文件不存在！");
			return;
		}
		Image src = ImageIO.read(new File(imgPath));
		int width = src.getWidth(null);
		int height = src.getHeight(null);
		if (needCompress) { // 压缩LOGO
			if (width > WIDTH) {
				width = WIDTH;
			}
			if (height > HEIGHT) {
				height = HEIGHT;
			}
			Image image = src.getScaledInstance(width, height,
					Image.SCALE_SMOOTH);
			BufferedImage tag = new BufferedImage(width, height,
					BufferedImage.TYPE_INT_RGB);
			Graphics g = tag.getGraphics();
			g.drawImage(image, 0, 0, null); // 绘制缩小后的图
			g.dispose();
			src = image;
		}
		// 插入LOGO
		Graphics2D graph = source.createGraphics();
		int x = (QRCODE_SIZE - width) / 2;
		int y = (QRCODE_SIZE - height) / 2;
		graph.drawImage(src, x, y, width, height, null);
		Shape shape = new RoundRectangle2D.Float(x, y, width, width, 6, 6);
		graph.setStroke(new BasicStroke(3f));
		graph.draw(shape);
		
	if (title != null) {
		System.out.println("title:"+title);
	graph.setFont(new Font("宋体", Font.BOLD, TITLE_FONT_SIZE));
	graph.setPaint(Color.black); 
	//String[] titleSplit = title.split(" ");
	//graph.drawString(titleSplit[1], (IMAGE_SIZE_W/4)-(titleSplit[1].length()*TITLE_FONT_SIZE/10), TITLE_POSITION_H_1);
	//graph.drawString(titleSplit[0], (IMAGE_SIZE_W/4)-(titleSplit[0].length()*TITLE_FONT_SIZE/2), TITLE_POSITION_H);
	//graph.drawString(titleSplit[0], 50, TITLE_POSITION_H);
	graph.drawString(title, 50, TITLE_POSITION_H);
	//graph.drawString(titleSplit[1], 50, TITLE_POSITION_H_1);
	//graph.drawString(titleSplit[4], 50, TITLE_POSITION_H_4);
	//modigy by jgyang 2014-11-27    生成二维码的联系人和电话号码取消显示  begine
	//graph.drawString(titleSplit[2], 50, TITLE_POSITION_H_2);
	//graph.drawString(titleSplit[3], 50, TITLE_POSITION_H_3);
	//modigy by jgyang 2014-11-27 end
		}
		
		graph.dispose();
	}

	/**
	 * 生成二维码(内嵌LOGO)
	 * 
	 * @param content
	 *            内容
	 * @param imgPath
	 *            LOGO地址
	 * @param destPath
	 *            存放目录
	 * @param needCompress
	 *            是否压缩LOGO
	 * @throws Exception
	 */
	public static void encode(String content, String title, String imgPath, String destPath,
			boolean needCompress) throws Exception {
		BufferedImage image = QRCodeUtil.createImage(content, title, imgPath,
				needCompress);
		mkdirs(destPath);
		String file = new Random().nextInt(99999999)+".jpg";
		ImageIO.write(image, FORMAT_NAME, new File(destPath+"/"+file));
	}

	/**
	 * 当文件夹不存在时，mkdirs会自动创建多层目录，区别于mkdir．(mkdir如果父目录不存在则会抛出异常)
	 * @author lanyuan
	 * Email: mmm333zzz520@163.com
	 * @date 2013-12-11 上午10:16:36
	 * @param destPath 存放目录
	 */
	public static void mkdirs(String destPath) {
		File file =new File(destPath);    
		//当文件夹不存在时，mkdirs会自动创建多层目录，区别于mkdir．(mkdir如果父目录不存在则会抛出异常)
		if (!file.exists() && !file.isDirectory()) {
			file.mkdirs();
		}
	}

	/**
	 * 生成二维码(内嵌LOGO)
	 * 
	 * @param content
	 *            内容
	 * @param imgPath
	 *            LOGO地址
	 * @param destPath
	 *            存储地址
	 * @throws Exception
	 */
	public static void encode(String content, String imgPath, String destPath)
			throws Exception {
		QRCodeUtil.encode(content, null, imgPath, destPath, false);
	}

	/**
	 * 生成二维码
	 * 
	 * @param content
	 *            内容
	 * @param destPath
	 *            存储地址
	 * @param needCompress
	 *            是否压缩LOGO
	 * @throws Exception
	 */
	public static void encode(String content, String destPath,
			boolean needCompress) throws Exception {
		QRCodeUtil.encode(content, null, null, destPath, needCompress);
	}

	/**
	 * 生成二维码
	 * 
	 * @param content
	 *            内容
	 * @param destPath
	 *            存储地址
	 * @throws Exception
	 */
	public static void encode(String content, String destPath) throws Exception {
		QRCodeUtil.encode(content, null, null, destPath, false);
	}

	/**
	 * 生成二维码(内嵌LOGO)
	 * 
	 * @param content
	 *            内容
	 * @param imgPath
	 *            LOGO地址
	 * @param output
	 *            输出流
	 * @param needCompress
	 *            是否压缩LOGO
	 * @throws Exception
	 */
	public static void encode(String content, String title, String imgPath,
			OutputStream output, boolean needCompress) throws Exception {
		BufferedImage image = QRCodeUtil.createImage(content, title, imgPath,
				needCompress);
		ImageIO.write(image, FORMAT_NAME, output);
	}
	public static void encodeForCabinet(String JIGUI_ID, String title, String imgPath,
			OutputStream output, boolean needCompress) throws Exception {
		JIGUI_ID="typeId=5,resourceId="+JIGUI_ID+",cabinetSurface="+title;
		String decode= new sun.misc.BASE64Encoder().encode(JIGUI_ID.getBytes("UTF-8"));
		BufferedImage image = QRCodeUtil.createImage(decode, title, imgPath,
				needCompress);
		ImageIO.write(image, FORMAT_NAME, output);
	}

	/**
	 * 生成二维码
	 * 
	 * @param content
	 *            内容
	 * @param output
	 *            输出流
	 * @throws Exception
	 */
	public static void encode(String content, String title, OutputStream output)
			throws Exception {
		QRCodeUtil.encode(content, title, null, output, false);
	}

	/**
	 * 解析二维码
	 * 
	 * @param file
	 *            二维码图片
	 * @return
	 * @throws Exception
	 */
	public static String decode(File file) throws Exception {
		BufferedImage image;
		image = ImageIO.read(file);
		if (image == null) {
			return null;
		}
		BufferedImageLuminanceSource source = new BufferedImageLuminanceSource(
				image);
		BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
		Result result;
		Hashtable<DecodeHintType, Object> hints = new Hashtable<DecodeHintType, Object>();
		hints.put(DecodeHintType.CHARACTER_SET, CHARSET);
		result = new MultiFormatReader().decode(bitmap, hints);
		String resultStr = result.getText();
		return resultStr;
	}

	/**
	 * 解析二维码
	 * 
	 * @param path
	 *            二维码图片地址
	 * @return
	 * @throws Exception
	 */
	public static String decode(String path) throws Exception {
		return QRCodeUtil.decode(new File(path));
	}

	public static void main(String[] args) throws Exception {
		String text = "http://218.80.246.162:40000/ywglappUpdate/release/Telecom.apk";
		String title = "i运维下载地址";
		QRCodeUtil.encode(text, title, "c:/test/logo.png", "c:/test/", false);
	}	
}
