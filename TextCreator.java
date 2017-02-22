package com.netbin.tools;

import java.io.*;

class TextCreator{
	public static void main(String[] args){
		File test = new File("test.txt");
		FileOutputStream fOut = new FileOutputStream(test);
		OutputStreamWriter out = new OutputStreamWriter(fOut,"UTF-8");
		out.write("This is a test.");
		out.close();
		fOut.close();
		test.close();
	}
}