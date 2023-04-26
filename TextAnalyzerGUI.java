import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class TextAnalyzerGUI extends JFrame {
	private JTextArea resultArea;
	private Connection connection;

	public TextAnalyzerGUI() {
		super("Text Analyzer");

		resultArea = new JTextArea();
		getContentPane().add(new JScrollPane(resultArea), BorderLayout.CENTER);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 400);
		setVisible(true);

		String url = "jdbc:mysql://localhost:3306/wordoccurences";
		String username = "root";
		String password = "1326";
		
		try {
			
			connection = DriverManager.getConnection(url, username, password);
		
		} catch (SQLException ex) {
			resultArea.setText("Error: " + ex.getMessage());
		}

		String filePath = "C:\\Users\\14072\\OneDrive\\Desktop\\The Raven.txt";
		readFile(filePath);
	}

	protected void readFile(String filePath) {
		try {
			Map<String, Integer> wordCounts = new HashMap<>();
			BufferedReader reader = new BufferedReader(new FileReader(filePath));
			String line;
			while ((line = reader.readLine()) != null) {
			String[] words = line.split("\\W+");
			for (String word : words) {
				if (!word.isEmpty()) {
					if (wordCounts.containsKey(word)) {
						wordCounts.put(word, wordCounts.get(word) + 1);
					} else {
						wordCounts.put(word, 1);
					}
					}
				}
			}
			reader.close();
			List<Map.Entry<String, Integer>> sortedEntries = new ArrayList<>(wordCounts.entrySet());
			sortedEntries.sort(Collections.reverseOrder(Map.Entry.comparingByValue()));
			resultArea.setText("");
			
			for (Map.Entry<String, Integer> entry : sortedEntries) {
				
				resultArea.append(entry.getKey() + ": " + entry.getValue() + "\n");
				
				String sql = "INSERT INTO wordcount (word, count) VALUES (?, ?)";
				
				try (PreparedStatement statement = connection.prepareStatement(sql)) {
					statement.setString(1, entry.getKey());
					statement.setInt(2, entry.getValue());
					statement.executeUpdate();
				} catch (SQLException ex) {
					resultArea.setText("Error: " + ex.getMessage());
				}
			}
		} catch (IOException ex) {
			resultArea.setText("Error: " + ex.getMessage());
		}
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> new TextAnalyzerGUI());
	}

	public JTextArea getResultArea() {
		return resultArea;
	}
}
