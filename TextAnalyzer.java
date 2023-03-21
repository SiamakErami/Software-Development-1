import java.io.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.*;
import javax.swing.*;

public class TextAnalyzer extends JFrame {

    private JTextArea textArea;

    public TextAnalyzer() { 
        super("Text Analyzer");

        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane);
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getAnalyze();
    }

    private void getAnalyze() {
        try {
            File file = new File("C:\\Users\\14072\\OneDrive\\Desktop\\The Raven.txt");
            Scanner scanner = new Scanner(file);

            Map<String, Integer> wordFreq = new HashMap<>();

            while (scanner.hasNext()) {
                String word = scanner.next().toLowerCase();
                if (word.chars().allMatch(Character::isAlphabetic)) {
                    if (!wordFreq.containsKey(word)) {
                        wordFreq.put(word, 1);
                    } else {
                        int count = wordFreq.get(word);
                        wordFreq.put(word, count + 1);
                    }
                }
            }

            List<Map.Entry<String, Integer>> sortFreq = new ArrayList<>(wordFreq.entrySet());
            sortFreq.sort((a, b) -> b.getValue().compareTo(a.getValue()));

            for (Map.Entry<String, Integer> entry : sortFreq) {
                textArea.append(entry.getKey() + ": " + entry.getValue() + "\n");
            }

            scanner.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TextAnalyzer().setVisible(true);
        });
    }
}


	


