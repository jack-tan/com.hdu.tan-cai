package chapter30.part1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class HandleAclient implements Runnable {
	private Socket socket;
	private DataInputStream inputFromClient;
	private DataOutputStream outputToClient;

	public HandleAclient(Socket socket) {
		// TODO Auto-generated constructor stub
		this.socket = socket;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			inputFromClient = new DataInputStream(socket.getInputStream());
			outputToClient = new DataOutputStream(socket.getOutputStream());
			while (!Thread.interrupted()) {
				double radius = inputFromClient.readDouble();
				double area = radius * radius * Math.PI;
				outputToClient.writeDouble(area);
			}
		} catch (IOException e) {
			System.err.println(e);
		}

	}

}
