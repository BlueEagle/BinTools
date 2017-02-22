package com.netbin.tools;

import java.io.*;
import java.util.*;

public class ExtractText {
	private static String fileToRead;
	private static String wrapperKey;
	private static FileInputStream readFile = null;
	private static ArrayList<Integer> history = new ArrayList<Integer>();
	private static int f = 0;
	private static boolean isStarted = false;
	private static boolean isRunning = false;
	private static int matched = 0;
	private static ExtractText start;
    private static int bytesParsed = 0;
	
	public ExtractText(String file, String key) {
		fileToRead = file;
		wrapperKey = key;
		for(int i = 0; i < wrapperKey.length(); i++) history.add(-1);
	}
	
	public static void main(String[] args) throws IOException {
		if (args.length == 2) {
			start = new ExtractText(args[0], args[1]);
		}else {
			System.out.println("Please try again with the valid syntax:\njava com.netbin.tools.ExtractText (fileToParse) (ex/term tag)");
			return;
		}
		
		try {
			readFile = new FileInputStream(fileToRead);
			System.out.println("File opened successfully!");
			while((f = readFile.read()) != -1) { // read the next byte
				history.add(f);
				history.remove(0); // update the history array
				
				if(matched >= wrapperKey.length()) {
					matched = 0;
					//System.out.println("Key Matched!");
					isRunning = true;
					///*ERROR LINE*///if(isStarted == false) break; // if key is matched again
					 // break the loop
				} // check if the key has been matched
				//   and if it has set (start var = true) an clear matched = 0
				
				if (isRunning == true) System.out.print((char)f);
				
				for(int i = 0; i < wrapperKey.length(); i++) {
					if((int) wrapperKey.charAt(i) != history.get(i)) matched++;
				} // check if history matches the wrapper key and count matches.
				//System.out.println(++bytesParsed+" bytes parsed!");
			}
		}finally {
			if (readFile != null) readFile.close();
		}
	}
}