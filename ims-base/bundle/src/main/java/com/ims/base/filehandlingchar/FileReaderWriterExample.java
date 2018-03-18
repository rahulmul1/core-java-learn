package com.ims.base.filehandlingchar;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

import org.apache.commons.lang.StringUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class FileReaderExample.
 */
public class FileReaderWriterExample {

	/**
	 * The main method.
	 * 
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
		String content = "Now is the time for all good men\n"
				+ " to come to the aid of their country\n"
				+ " and pay their due taxes.";
		try {

			writeFileComplete(content,
					"H:/IMS PROJECT/Created Xmls/FileWriter.xml");

			readFileComplete("H:/IMS PROJECT/Created Xmls/FileWriter.xml");
			
			readFileComplete("H:/IMS PROJECT/Created Xmls/utf8file.txt");
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Read file complete with char stream.
	 * 
	 * @param filePath
	 *            the file path
	 * @return the string
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static String readFileComplete(String filePath) throws IOException {
		System.out.println("FileRead Start");
		
		Reader fr = new FileReader(new File(filePath));
		Reader br = new BufferedReader(fr);
		String string = StringUtils.EMPTY;
		try {
			char c[] = new char[1024];
			while (br.read(c) != -1) {
			}

			string = new String(c);
			System.out.println(string);

		} finally {
			br.close();
			fr.close();
		}
		System.out.println("FileRead End");

		return string;

	}

	/**
	 * Write file complete.
	 *
	 * @param content the content
	 * @param outputFilePath the output file path
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void writeFileComplete(String content, String outputFilePath)
			throws IOException {
		System.out.println("FileWrite Start");

		Writer fw = new FileWriter(new File(outputFilePath));
		Writer bfw = null;
		try {
			bfw = new BufferedWriter(fw);
			bfw.write(content);

		} finally {
			/**
			 * First close the wrapper class. Else it throws exception.
			 */
			if (bfw != null) {
				bfw.close();
			}
			if (fw != null) {
				fw.close();
			}

		}
		System.out.println("Filewrite End");

	}

}
