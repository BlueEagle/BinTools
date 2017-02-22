package com.netbin.tools;

import java.io.*;
import java.net.*;
import java.util.*;

enum Command {
	START("start"), PAUSE("pause"), PLAY("play"), SEND("send");
	private String command;
	Command(String c) {
		command = c;
	}
	String getString() {
		return command;
	}
}

public class CommandList {
	private static Command firstArgument;
	
	// CONSTRUCTOR
	public CommandList(){}
	
	// METHODS
	public static String[] parseCommand(String command) {
		command = command.toLowerCase(); // FORCE LOWERCASE
		ArrayList<String> commandArray = new ArrayList<String>(); // MAKE ARRAYLIST
		for (int i = 0; i < command.length()-1; i++) { // ITERATE THROUGH CHARS IN COMMAND STRING
			if (command.charAt(i) == ' ') { // IF THE CHAR IS ' '
				commandArray.add(command.substring(0,i)); // ADD FIRST SEGMENT OF STRING BEFORE SPACE
				command = command.substring(i+1);
				i = 0;
			}
		}
		String[] returnArray = new String[commandArray.size()];
		commandArray.toArray(returnArray);
		return returnArray;
	}
	
	public static void execute(String[] command) {
		// match command agrument 1 to ENUM equivalent.
		/*
		if (command[0].toLowercase().equals("start")) firstArgument = firstArgument.START;
		else if (command[0].toLowercase().equals("pause")) firstArgument = firstArgument.PAUSE;
		else if (command[0].toLowercase().equals("play")) firstArgument = firstArgument.PLAY;
		else if (command[0].toLowercase().equals("send")) firstArgument = firstArgument.SEND;
		*/
		
		for (Command c : Command.values()) {
			if (command[0].equals(c.getString())) firstArgument = c;
		}
		
		switch(firstArgument) {
			case START:
				System.out.println("Starting music...");
			case PAUSE:
				System.out.println("Pausing music...");
			case PLAY:
				System.out.println("Resuming music...");
			case SEND:
				System.out.println("Sending file...");
		}
	}
	
	public static void writeFile(String fileToSend, DataOutputStream out) {
		
		
		//int[] file = fileToByteArray(fileToSend);
	}
	
	public static ArrayList<Integer> fileToByteArray(String fileToSend) throws IOException {
		FileInputStream in = new FileInputStream(fileToSend); // FILEINPUT STREAM
		int f;
		ArrayList<Integer> arrayListToReturn = new ArrayList<Integer>();
		while((f = in.read()) != -1) {
			arrayListToReturn.add(f);
		}
		return arrayListToReturn;
	}
}