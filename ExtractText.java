package com.netbin.tools;

import java.io.*;
import java.util.*;

public class ExtractText {
	// VARIABLES
	private static String fileToRead;
	private static String wrapperKey;
	private static FileInputStream readFile = null;
	private static int f = 0;
	private static ExtractText start;
	private static String fileText = "";
	private static String messageText = "";
	
	// CONSTRUCTORS
	public ExtractText(String file, String key) {
		fileToRead = file;
		wrapperKey = key;
	}
	
	// MAIN
	public static void main(String[] args) throws IOException {
		// ARGUMENT HANDLING
		if (args.length == 2) {
			start = new ExtractText(args[0], args[1]);
		}else {
			System.out.println("Please try again with the valid syntax:\njava com.netbin.tools.ExtractText (fileToParse) (ex/term tag)");
			return;
		}
		
		// FILE PARSING
		try{
			readFile = new FileInputStream(fileToRead);
			
			while ((f = readFile.read()) != -1) fileText += (char)f; // make unmodified string
			//System.out.println("File Contents: "+fileText); // <-- PRINT UNMODIFIED FILE CONTENT *** DONT DO THIS
			fileText = extractText(fileText, wrapperKey);
			
			System.out.println("Modified output: "+fileText); // print modified String
			
		}finally { // CLOSE FILE
			if (readFile != null) readFile.close();
		}
	}
	
	public static String extractText(String fileToParse, String key) {
		int startKeyIndex = -1, endKeyIndex = -1;
		if (fileToParse.indexOf(key) != -1) {
			startKeyIndex = fileToParse.indexOf(key);
			if (fileToParse.indexOf(key, startKeyIndex + key.length()-1) != -1) 
				endKeyIndex = fileToParse.indexOf(key, startKeyIndex + key.length()-1);
		}
		if (startKeyIndex != -1 && endKeyIndex != -1) return fileToParse.substring(startKeyIndex + key.length(), endKeyIndex);
		return "";
	}
	
	private static String removeKeys(String text, String key) {
		return text.substring(key.length(),text.length()-key.length());
	}
	
}