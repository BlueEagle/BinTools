package com.netbin.tools;

import java.net.*;
import java.io.*;
import java.util.Scanner;

public class PiConnect {
	public static void main(String[] args) throws IOException { // takes (address), (port)
		String address = args[0];
		int port = Integer.parseInt(args[1]);
		try {
			// ESTABLISH A CONNECTION WITH HOST
			System.out.print("Connecting to "+address+":"+port+"... ");
			Socket client = new Socket(address, port);
			System.out.println("Connected!\n");
			
			// CREATE STREAMS
			DataInputStream in = new DataInputStream(client.getInputStream());
			DataOutputStream out = new DataOutputStream(client.getOutputStream());
			
			// ACCEPT Greeting
			System.out.println(in.readUTF()+"\n");
			
			// SEND Command LOOP
			String command;
			Scanner userInput;
			do {
				userInput = new Scanner(System.in);
				System.out.print("> ");
				command = userInput.nextLine();
				out.writeUTF(command);
			} while(!(command.equals("exit") || command.equals("quit") || command.equals("disconnect")));
			
			client.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}