package chapter30.part1;

import java.awt.BorderLayout;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class MultiThreadServer extends JFrame {
	private JTextArea jta = new JTextArea();
	private ExecutorService exec = Executors.newCachedThreadPool();
	private ServerSocket serverSocket;
	private int clientNo = 1;

	public MultiThreadServer() {
		// TODO Auto-generated constructor stub
		setLayout(new BorderLayout());
		add(new JScrollPane(jta), BorderLayout.CENTER);

		setTitle("MultiThreadServer");
		setSize(500, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		try {
			serverSocket = new ServerSocket(8000);
			jta.append("MultiThreadServer started at " + new Date() + '\n');
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		exec.execute(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					while (!Thread.interrupted()) {

						Socket socket = serverSocket.accept();
						jta.append(("Starting thread for client " + clientNo + " at " + new Date() + '\n'));
						InetAddress inetAddress = socket.getInetAddress();
						jta.append("Client " + clientNo + "'s host name is " + inetAddress.getHostName() + "\n");
						jta.append("Client " + clientNo + "'s host Address is " + inetAddress.getHostAddress() + "\n");
						exec.execute(new HandleAclient(socket));
						clientNo++;
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.err.println(e);
					System.out.println("°²È«ÍË³ö");
				}
			}
		});
	}

	public static void main(String[] args) {
		new MultiThreadServer();
	}

}
