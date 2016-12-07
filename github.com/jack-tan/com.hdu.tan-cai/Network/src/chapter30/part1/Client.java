package chapter30.part1;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Client extends JFrame {
	private JTextField jtf = new JTextField();
	private JTextArea jta = new JTextArea();
	private DataInputStream fromServer;
	private DataOutputStream toServer;

	public Client() {
		// TODO Auto-generated constructor stub
		JPanel p = new JPanel();
		p.setLayout(new BorderLayout());
		p.add(new JLabel("Enter radius"), BorderLayout.WEST);
		p.add(jtf, BorderLayout.CENTER);
		setLayout(new BorderLayout());
		add(p, BorderLayout.NORTH);
		add(new JScrollPane(jta), BorderLayout.CENTER);

		jtf.addActionListener(new TextFieldListener());

		setTitle("Client");
		setSize(500, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		try {
			Socket socket = new Socket("localhost", 8000);
			fromServer = new DataInputStream(socket.getInputStream());
			toServer = new DataOutputStream(socket.getOutputStream());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			jta.append(e.toString() + '\n');
		}
	}

	class TextFieldListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			try {
				double radius = Double.parseDouble(jtf.getText().trim());

				toServer.writeDouble(radius);
				toServer.flush();
				double area = fromServer.readDouble();
				jta.append("Radius is " + radius + "\n");
				jta.append("Area received from the server is " + area + "\n");
			} catch (IOException e) {
				System.err.println(e);
			}
		}
	}
	public static void main(String[] args) {
		new Client();
	}

}
