import java.io.FileNotFoundException;
import java.util.*;

public class FrequentWords {
	public static void main(String[] args) {
		// Set text in a string
		Scanner in = new Scanner(System.in);
		System.out.print("Please enter filename: ");
		String fileName = in.next();
		System.out.println();
		int maxCount = 0;

		try {
			List<String> file = Utilities.readAFile(fileName.trim());
			String[] words = new String[file.size() - 1];
			// Create a TreeMap to hold words as key and count as value
			Map<String, Integer> map = new TreeMap<>();

			for (int x = 0; x < file.size() - 1; x++) {
				words[x] = file.get(x);
			}

			for (int i = 0; i < words.length; i++) {
				String key = words[i].toLowerCase();

				if (key.length() > 0) {
					if (!map.containsKey(key) && !key.contains(" ") && !key.contains("\n") && !key.contains("\t")) {
						map.put(key, 1);


					} else if (!key.contains(" ") && !key.contains("\n") && !key.contains("\t")){
						int value = map.get(key);
						value++;
						map.put(key, value);
						if(value > maxCount) {
							maxCount = value;
						}

					}
				}
			}

			System.out.print("Words that appear " + maxCount + " times: ");
			for (String s : map.keySet()) {
				if(map.get(s) == maxCount) {
					System.out.print(s + " ");
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("Incorrect file name: " + fileName);
			e.printStackTrace();
			
		} finally {
			in.close();
		}

	}
}
