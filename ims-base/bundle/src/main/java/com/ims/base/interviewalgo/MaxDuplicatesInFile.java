package com.ims.base.interviewalgo;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public class MaxDuplicatesInFile {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		try {
			
			countMaxDuplicates();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void countMaxDuplicates() throws IOException {
		File file = new File(
				"H:/IMS PROJECT/Created Xmls/MaxDuplicatesInFile.txt");
		InputStream is = new FileInputStream(file);
		BufferedInputStream bis = new BufferedInputStream(is);
		try {
			byte[] b = new byte[bis.available()];
			bis.read(b);
			String a = new String(b);
			String[] splitString = a.split(" ");
			Map<String, Integer> map = new TreeMap<>();
			Arrays.sort(splitString);
			int j = 0;
			int count = 0;
			for (int i = 0; i < splitString.length; i++) {
				if (splitString[i].equals(splitString[j])) {
					count++;
				} else {
					map.put(splitString[j], count);
					count = 1;
					splitString[++j] = splitString[i];
				}
			}
			System.out.println(map);
		} finally {
			bis.close();
			is.close();
		}
	}
	
	
}
