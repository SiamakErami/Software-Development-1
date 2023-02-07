
import java.io.*;
import java.util.*;

public class WordFrequency {
	
	private static Map<String, Integer> wordMap;
	
	public static void main(String[] args) throws IOException {
		
		
		
		ArrayList<String> lines = readText("Raven.txt");
		wordFrequency(lines);
		
		//System.out.println("Word Frequency");
		//System.out.printf("%-20s %s%n", "Word","Frequency");
		
		wordMap.forEach( (key, value) -> {
			System.out.printf("%-20s %s%n", key, value);

		});

	}
	
	public static void wordFrequency(ArrayList<String> lines) {
		wordMap = new TreeMap<>();
		
		for(String line : lines) {
			String words [] = line.replaceAll("[^a-zA-Z0-9 ]", "").toLowerCase().split("\\s+");
			for(String w : words) {
				if(wordMap.get(w) == null) {
				wordMap.put(w,1);
				
			} else {
				wordMap.put(w, wordMap.get(w) + 1);
			}
		}
		}
	}
	
	public static ArrayList<String> readText(String fileName){
		ArrayList<String> lines = new ArrayList<>();
		
		try {
			/*File file = new File("C:\\Users\\14072\\OneDrive\\Desktop\\Raven.txt");
			Scanner p = new Scanner(file);*/
			BufferedReader br = new BufferedReader(new FileReader("Raven.txt"));
			/*String line = br.readLine();*/
			Scanner p = new Scanner(br);
			while(p.hasNextLine()) {
				lines.add(p.nextLine());
			}
			
			p.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return lines;
		
		
		
		
		
		
		
		
	}
	}

