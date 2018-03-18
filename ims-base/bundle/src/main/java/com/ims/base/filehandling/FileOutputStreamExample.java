package com.ims.base.filehandling;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * The Class FileOutputStreamExample.
 */
public class FileOutputStreamExample {

	/**
	 * The main method.
	 * 
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {

		String content = "This is the text content1";
		String outputFile = "H:/IMS PROJECT/Created Xmls/FOS.xml";

		writeStringToFile(content, outputFile);
		
		try {
			FileInputStreamExample.readFileComplete(outputFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Write string to file.
	 * 
	 * Writes a string to file. Converts string to bytes/outputstream/file 1st and then writes bytes to file.
	 * Creates the file if it not exist.
	 * 
	 * @param content
	 *            the content
	 * @param outputFile
	 *            the output file
	 */
	public static void writeStringToFile(String content, String outputFile) {
		File file;
		FileOutputStream fop = null;
		try {

			file = new File(outputFile);
			fop = new FileOutputStream(file);

			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			// get the content in bytes
			byte[] contentInBytes = content.getBytes();

			fop.write(contentInBytes);
			fop.flush();
			fop.close();

			System.out.println("Done");

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fop != null) {
					fop.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}