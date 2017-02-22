package com.netbin.tools;

import java.io.*;

public class AddWatermark {
	private String inputFile;
	private String outputFile;
	private String watermarkFile = "watermark.txt";
	
	public AddWatermark() {
		inputFile = "test.txt";
		outputFile = "testCopy.txt";
	}
	public AddWatermark(String arg1) {
		inputFile = arg1;
		outputFile = arg1.substring(0,arg1.length()-4) +"Copy" +arg1.substring(arg1.length()-4,arg1.length());
	}
	public AddWatermark(String arg1, String arg2) {
		inputFile = arg1;
		outputFile = arg2;
	}
	
	public static void main(String[] args) throws IOException
	{
		AddWatermark run;
		if(args.length==0) {
			//System.out.println("no arguments");
			run = new AddWatermark();
		}
		if(args.length==1) {
			//System.out.println("one argument");
			run = new AddWatermark(args[0]);
		}
		if(args.length==2) {
			//System.out.println("two arguments");
			run = new AddWatermark(args[0], args[1]);
		}else
			run = new AddWatermark();
		
		FileInputStream in = null;
		FileOutputStream out = null;
		FileInputStream watermark = null;
		
		try {
			in = new FileInputStream(run.inputFile);
			out = new FileOutputStream(run.outputFile);
			watermark = new FileInputStream(run.watermarkFile);
			
			int c;
			while((c = in.read()) != -1) {
				out.write(c);
				//System.out.println(c);
			}
			
			int d;
			while((d = watermark.read()) != -1) {
				out.write(d);
				//System.out.println(d);
			}
			
			System.out.println("File copied successfully!");
		}finally {
			if (in != null) in.close();
			if (out != null) out.close();
			if (watermark != null) watermark.close();
		}
	}
}