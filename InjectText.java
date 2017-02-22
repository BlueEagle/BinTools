package com.netbin.tools;

import java.io.*;

public class InjectText {
	private String inputFile;
	private String outputFile;
	private static String textToInject;
	private static String key;

	public InjectText(String arg1, String arg2) {
		inputFile = arg1;
		outputFile = arg1.substring(0,arg1.length()-4) +"Copy" +arg1.substring(arg1.length()-4,arg1.length());
		textToInject = arg2;
	}
	
	public InjectText(String arg1, String arg2, String arg3) {
		this(arg1, arg2);
		key = arg3;
		textToInject = key + textToInject + key;
	}
	
	public static void main(String[] args) throws IOException
	{
		InjectText start;
		
		if(args.length == 2) {
			start = new InjectText(args[0], args[1]);
		} else if (args.length == 3) {
			start = new InjectText(args[0], args[1], args[2]);
		}else {
			System.out.println("Please try again with the valid syntax:\njava com.netbin.tools.InjectText (fileToInject) (textToInject) [key]");
			return;
		}
		
		FileInputStream in = null;
		FileOutputStream out = null;
		
		try {
			in = new FileInputStream(start.inputFile);
			out = new FileOutputStream(start.outputFile);
			
			int c;
			while((c = in.read()) != -1) {
				out.write(c);
			}
			
			for(int i = 0; i < textToInject.length(); i++)
				out.write((int)textToInject.charAt(i));
		}finally {
			if (in != null) in.close();
			if (out != null) out.close();
			System.out.println("File copied successfully!");
		}
	}
}