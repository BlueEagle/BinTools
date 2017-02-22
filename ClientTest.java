package com.netbin.tools;

import java.net.*;
import java.io.*;

public class ClientTest {
	public static void main(String[] args) throws IOException {
		String serverName = args[0];
		int port = Integer.parseInt(args[1]);
		try {
			System.out.println("Connecting to "+serverName+" on port "+port);
			Socket client = new Socket(serverName, port);
			System.out.println("Connected to "+client.getRemoteSocketAddress());
			client.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}