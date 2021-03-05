import java.io.FileNotFoundException;
import java.util.List;
import java.util.*;

public class SpellChecker {
	public static void main(String[] args) {
		final String DICTIONARY_NAME = "wordsEn.txt";
		final int WORD_PER_ROW = 10;
		Scanner in = new Scanner(System.in);
		List<String> missSpelled = new ArrayList<String>();
		Map<String, Integer> freq = new TreeMap<>();
		boolean inList = false;
		boolean first = true;
		int max = 0;
		int count = 0;

		System.out.print("Please enter file name: ");
		String fileName = in.next();
		System.out.println();

		try {
			List<String> dictionary = Utilities.readAFile(DICTIONARY_NAME);
			List<String> file = Utilities.readAFile(fileName.trim());
			
			for (int x = 0; x < file.size() - 1; x++) {
				for (int y = 0; y < dictionary.size(); y++) {
					String fileS = file.get(x).toString().toLowerCase();
					String dicY = dictionary.get(y).toString().toLowerCase();

					if (fileS.compareTo(dicY) == 0 && !fileS.contains(" ") && !fileS.contains("\n")
							&& !fileS.contains("\t")) {
						if (!freq.containsKey(fileS)) {
							freq.put(fileS, 1);
							inList = true;
						} else if (freq.containsKey(fileS)) {
							int value = freq.get(fileS);
							value++;
							freq.put(fileS, value);
							if (value > max) {
								max = value;
							}
							inList = true;
						}
					}

					if (y == dictionary.size() - 1) {
						if (!inList && !fileS.contains(" ") && !fileS.contains("\n") && !fileS.contains("\t")) {
							missSpelled.add(file.get(x).toString());
						} else {
							inList = false;
						}

					}

				}

			}

		

			for (String s : freq.keySet()) {
				if (freq.get(s) == max && first) {
					System.out.print("most frequent words: \n[" + s);
					first = false;
					count++;

				} else if (freq.get(s) == max && !first) {
					if(WORD_PER_ROW == count) {
						System.out.print(", " + s + "\n");
						count = 0;
					} else {
						System.out.print(", " + s);
						count++;
					}
					
				}

			}
			if (!first) {
				System.out.print("]\n\n");
			}
			first = true;
			count = 0;

			for (int x = max; x > 0; x--) {
				for (String s : freq.keySet()) {
					if (freq.get(s) == x && first) {
						System.out.print("frequency: " + freq.get(s) + "\n[" + s);
						first = false;

					} else if (freq.get(s) == x && !first) {
						if(WORD_PER_ROW == count) {
							System.out.print(", \n" + s);
							count = 0;
						} else {
							System.out.print(", " + s);
							count++;
						}
					}

				}
				if (!first) {
					System.out.print("]\n\n");
				}
				first = true;
				count = 0;
			}
			
			System.out.println("misspelled words");
			System.out.println(missSpelled.toString());


		} catch (FileNotFoundException e) {
			System.out.println("File not found");
			e.printStackTrace();
		} finally {
			in.close();
		}

	}

}
