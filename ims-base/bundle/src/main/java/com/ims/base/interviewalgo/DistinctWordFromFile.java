package com.ims.base.interviewalgo;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;
/**
 * Write a program to find all distinct words from the given file.
 * Print only duplicate words once and non duplicate.
 */
public class DistinctWordFromFile {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		
		ClassLoader classLoader = new DistinctWordFromFile().getClass().getClassLoader();
		String fileDir = classLoader.getResource("files/MaxDuplicatesInFile.txt").getFile();
		
		File file = new File(fileDir);
		InputStream is = new FileInputStream(file);
		BufferedInputStream bis = new BufferedInputStream(is);
		try {
			byte b[] = new byte[bis.available()];
			bis.read(b);
			String data = new String(b);
			String[] words = data.split(" ");
			int count=0;

			for (int i = 0; i < words.length; i++) {
				boolean isDistinct = true;
				for (int j = i + 1; j < words.length; j++) {
					if (words[i].compareTo(words[j]) == 0) {
						isDistinct = false;
						break;
					}
				}
				if (isDistinct) {
					System.out.println(words[i]);
					count++;
				}
			}
			System.out.println("Words count = "+count);
			
			System.out.println("Find sorted non duplicate words");
			useSet(words);
		} finally {
			bis.close();
		}
	}

	private static void useSet(String[] words) {
		
		Set<String> set = new TreeSet<>(Arrays.asList(words));
		System.out.println("Size = "+set.size());
		for (String string : set) {
			System.out.println(string);
		}

	}
}
