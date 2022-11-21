package hw7;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Scanner;

public class Keywords {
	public Keywords(){}
	public static Hashtable<Integer, String> createList(File file) throws FileNotFoundException{
		Hashtable<Integer, String> input = new Hashtable<Integer,String>();
		try (Scanner read = new Scanner(file)) {
			int index = 0;
			while (read.hasNextLine()) {
				String keyword = read.next();
				input.put(index, keyword);
				index++;
			}
		}
		return input;
}
}
