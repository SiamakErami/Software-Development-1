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
            URL url = new URL("https://www.gutenberg.org/files/1065/1065-h/1065-h.htm");
            InputStream input = url.openStream();
            Scanner scanner = new Scanner(input);

            Map<String, Integer> wordFreq = new HashMap<>();
            boolean counting = false;

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.contains("<h1>")) {
                    counting = true;
                } else if (line.contains("nevermore")) {
                    break;
                }
                if (counting) {
                    String[] words = line.split("\\s+");
                    for (String word : words) {
                        if (word.toLowerCase().matches("[a-z]+")) {
                            if (!wordFreq.containsKey(word)) {
                                wordFreq.put(word, 1);
                            } else {
                                int count = wordFreq.get(word);
                                wordFreq.put(word, count + 1);
                            }
                        }
                        if (word.equalsIgnoreCase("once")) {
                            counting = true;
                        }
                    }
                }
            }

            List<Map.Entry<String, Integer>> sortFreq = new ArrayList<>(wordFreq.entrySet());
            sortFreq.sort((a, b) -> b.getValue().compareTo(a.getValue()));
            for (Map.Entry<String, Integer> entry : sortFreq) {
                textArea.append(entry.getKey() + ": " + entry.getValue() + "\n");
            }
            scanner.close();
            input.close();

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


	


