package com.netbin.tools;

import java.io.*;

public class CopyFile {
	public static void main(String[] args) throws IOException
	{
		FileInputStream in = null;
		FileOutputStream out = null;
		
		int[] secretMessage = {42, 42, 42, 42, 42, 42, 42, 66, 105,
		110, 97, 114, 121, 110, 105, 110, 106, 97, 32, 104, 97, 115,
		32, 99, 108, 97, 105, 109, 101, 100, 32, 116, 104, 105, 115,
		32, 102, 105, 108, 101, 33, 42, 42, 42, 42, 42, 42, 42};
		
		try {
			in = new FileInputStream("test.jpg");
			out = new FileOutputStream("testCopy.txt");
			
			int c;
			while((c = in.read()) != -1) {
				out.write(c);
				//System.out.println(c);
			}
			
			for(int secretLetter:secretMessage)
				out.write(secretLetter);
			
			System.out.println("File copied successfully!");
		}finally {
			if (in != null) in.close();
			if (out != null) out.close();
		}
	}
}