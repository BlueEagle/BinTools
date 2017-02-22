package com.netbin.tools;

import java.net.*;
import java.io.*;
import java.util.*;

public class PiConnectServer extends Thread {
	// ESTABLISH INITIAL LISTENING SOCKET TO ACCEPT INCOMING CONNECTIONS
	ServerSocket listeningSocket;
	public PiConnectServer(int port) throws IOException {
		listeningSocket = new ServerSocket(port);
	}
	
	public void run() {
		while(true) {
			try {
				// Connection handler
				System.out.print("\nWaiting for a connection on port "+listeningSocket.getLocalPort()+"... ");
				Socket server = listeningSocket.accept();
				System.out.println("Connected!");
				System.out.println("Client: "+server.getRemoteSocketAddress()+"\n");
				
				// CREATE STREAMS
				DataInputStream in = new DataInputStream(server.getInputStream());
				DataOutputStream out = new DataOutputStream(server.getOutputStream());
				
				// SEND Greeting
				out.writeUTF("Thank you for connecting to "+server.getLocalSocketAddress()+"!");
				
				// OTHER THINGS
				String command;
				String[] commandArray;
				
				// ENTER CONNECTION LOOP
				do {
					// READ command into command String
					command = in.readUTF();
					System.out.println("User entered command: "+command);
					commandArray = CommandList.parseCommand(command);
					CommandList.execute(commandArray);
					
				}while(!(command.toLowerCase().equals("exit") || command.toLowerCase().equals("quit") || command.toLowerCase().equals("disconnect")));
				
				// CLOSE CONNECTION
				server.close();
			} catch(IOException e) {
				e.printStackTrace();
				break;
			}
		}
	}
	
	// THREAD RUNNER < Essentially just executes the program in a new thread.
	public static void main(String[] args) {
		int port = Integer.parseInt(args[0]);
		try {
			Thread t = new PiConnectServer(port);
			t.start();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}