package hw7;
import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Hasher {
	public static int countLines(File file) throws FileNotFoundException {
		int count = 1;
		Scanner counter = new Scanner(file);
		Pattern comment = Pattern.compile("/\\*.*?\\*/");
		Matcher match = comment.matcher(counter.nextLine());
		while (counter.hasNextLine()) {
			counter.nextLine();
			if (!match.find()) {
				count++;
			}
		}
		return count;
	}
	public static int countKeys(Hashtable<Integer, String> hash,File file) throws FileNotFoundException {
		int count = 0;
		String driver = "";
		Scanner counter = new Scanner(file);
		while (counter.hasNextLine()) {
			driver += counter.nextLine() + " "; }
		String[] keywords = driver.split(" ");
		for (int i =0; i < keywords.length; i++) {
			if (hash.containsValue(keywords[i])) {
				count++;
			}
		}
		counter.close();
		return count;
	}
	
}