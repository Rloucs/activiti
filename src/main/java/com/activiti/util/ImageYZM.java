package com.activiti.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.activiti.constant.ConstantVO;

public class ImageYZM {
	public static final String[] strTab =new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "a", "b", "c",
			"d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y",
			"z", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", 
			"V", "W", "X", "Y", "Z"};
	private int fontHeight = 25;
	private	int width=105;
	private	int height=30;
	private int xx=21;
	private int yy=28;
	public  String getYZM(HttpServletResponse resp) throws IOException {
		StringBuilder sbd = new StringBuilder();
		Random random = new Random();
		BufferedImage buffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics grap = buffer.getGraphics();
		grap.setColor(Color.white);
		grap.fillRect(0, 0, width, height);
		Font font = new Font("Fixedsys", Font.BOLD, fontHeight);
		grap.setFont(font);
		grap.setColor(Color.BLACK);
		grap.drawLine(0, 0, width-1, height-1);
		
//		for(int i=0;i<40;i++) {
//			int x = random.nextInt(width);
//			int y = random.nextInt(height);
//			int x1 = random.nextInt(12);
//			int y1 = random.nextInt(12);
//			grap.drawRect(x, y, x-x1, y-y1);
//		}
//	
		for(int i=0;i<4;i++) {
			int tem = random.nextInt(62);
			int red = random.nextInt(255);
			int yellow = random.nextInt(255);
			int blue = random.nextInt(255);
			grap.setColor(new Color(red, yellow, blue));
			grap.drawString(strTab[tem], (i+1)*xx, yy);
			sbd.append(strTab[tem]);
		}
		resp.setHeader("Pragma", "no-cache");
		resp.setHeader("Cache-control", "no-cache");
		resp.setDateHeader("Expires", 0);
	    resp.setContentType("image/jpeg");
	    ServletOutputStream  sout = resp.getOutputStream();
	    ImageIO.write(buffer, "jpeg", sout);
	    sout.close();
		String text = sbd.toString();
		return text;
	}
	
	public static void main(String[] args) {
//		String str = getYZM();
//		System.out.println(str);
	}
}
