import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

public class Client {
	private JFrame frame;
	private JTextField inputField;
	private JLabel statusLabel;
	private JLabel resultLabel;
	private Socket clientSocket;
	private PrintWriter out;
	private BufferedReader in;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				Client window = new Client();
				window.frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public Client() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblEnterANumber = new JLabel("Enter a number:");
		lblEnterANumber.setBounds(35, 29, 99, 14);
		frame.getContentPane().add(lblEnterANumber);

		inputField = new JTextField();
		inputField.setBounds(144, 26, 86, 20);
		frame.getContentPane().add(inputField);
		inputField.setColumns(10);

		JButton btnCheck = new JButton("Check");
		btnCheck.addActionListener(e -> checkPrime());
		btnCheck.setBounds(240, 25, 89, 23);
		frame.getContentPane().add(btnCheck);

		JLabel lblServerStatus = new JLabel("Server Status:");
		lblServerStatus.setBounds(35, 90, 99, 14);
		frame.getContentPane().add(lblServerStatus);

		statusLabel = new JLabel("Disconnected");
		statusLabel.setBounds(144, 90, 117, 14);
		frame.getContentPane().add(statusLabel);

		JLabel lblResult = new JLabel("Result:");
		lblResult.setBounds(35, 140, 46, 14);
		frame.getContentPane().add(lblResult);

		resultLabel = new JLabel("");
		resultLabel.setBounds(144, 140, 117, 14);
		frame.getContentPane().add(resultLabel);
	}

	private void connect() {
		try {
			clientSocket = new Socket("localhost", 1234);
			out = new PrintWriter(clientSocket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			statusLabel.setText("Connected");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void disconnect() {
		try {
			out.close();
			in.close();
			clientSocket.close();
			statusLabel.setText("Disconnected");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void checkPrime() {
		if (clientSocket == null || clientSocket.isClosed()) {
			connect();
		}

		try {
			int n = Integer.parseInt(inputField.getText());
			out.println(n);
			String response = in.readLine();
			resultLabel.setText(response);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(frame, "Invalid input", "Error", JOptionPane.ERROR_MESSAGE);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
