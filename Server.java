import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.*;

public class Server {
	private JFrame frame;
	private JLabel statusLabel;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				Server window = new Server();
				window.frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public Server() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblServerStatus = new JLabel("Server Status:");
		lblServerStatus.setBounds(35, 29, 99, 14);
		frame.getContentPane().add(lblServerStatus);

		statusLabel = new JLabel("Stopped");
		statusLabel.setBounds(144, 29, 117, 14);
		frame.getContentPane().add(statusLabel);

		JButton btnStart = new JButton("Start");
		btnStart.addActionListener(e -> startServer());
		btnStart.setBounds(35, 65, 89, 23);
		frame.getContentPane().add(btnStart);

		JButton btnStop = new JButton("Stop");
		btnStop.addActionListener(e -> stopServer());
		btnStop.setBounds(144, 65, 89, 23);
		frame.getContentPane().add(btnStop);
	}

	private void startServer() {
		new Thread(() -> {
			try {
				ServerSocket serverSocket = new ServerSocket(1234);
				statusLabel.setText("Running");
				while (true) {
					Socket clientSocket = serverSocket.accept();
					new Thread(new ClientHandler(clientSocket)).start();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}).start();
	}

	private void stopServer() {
		statusLabel.setText("Stopped");
	}

	private class ClientHandler implements Runnable {

		private Socket clientSocket;

		public ClientHandler(Socket clientSocket) {
			this.clientSocket = clientSocket;
		}

		public void run() {
			try {
				PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
				BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

				String inputLine;

				while ((inputLine = in.readLine()) != null) {
					int n = Integer.parseInt(inputLine);
					if (isPrime(n)) {
						out.println("Prime");
					} else {
						out.println("Not Prime");
					}
				}

				out.close();
				in.close();
				clientSocket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		private boolean isPrime(int n) {
			if (n <= 1) {
				return false;
			}
			for (int i = 2; i <= Math.sqrt(n); i++) {
				if (n % i == 0) {
					return false;
				}
			}
			return true;
		}
	}
}
