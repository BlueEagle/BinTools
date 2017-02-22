package com.netbin.tools;

import java.net.*;
import java.io.*;

public class ServerTest extends Thread {
	private ServerSocket myServerSocket = null;
	
	public ServerTest(int port) throws IOException {
		myServerSocket = new ServerSocket(port);
	}
	
	public void run() {
		while(true) {
			try {
				System.out.println("Waiting for client on port "+myServerSocket.getLocalPort()+"...");
				Socket server = myServerSocket.accept();
				System.out.println("Connected to "+server.getRemoteSocketAddress());
				server.close();
				
			} catch(IOException e) {
				e.printStackTrace();
				break;
			}
		}
	}
	
	public static void main(String[] args) {
		int port = Integer.parseInt(args[0]);
		try {
			Thread t = new ServerTest(port);
			t.start();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}