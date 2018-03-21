package com.ims.base.filehandling;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The Class InputToOutputStream.
 * 
 * Converts form input/bytes to output/bytes stream.
 */
public class InputToOutputStream {
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {

		String fileInput = "files/Employee.xml";
		File fileOutput = File.createTempFile("InputToOutput", ".xml");
		try {
			ClassLoader classLoader = new InputToOutputStream().getClass().getClassLoader();
			String inputFileName = classLoader.getResource(fileInput).getFile();
			
			File inputFile = new File(inputFileName);
			convertInputToOutputStream(inputFile, fileOutput);
			FileInputStreamExample.readFileComplete(fileOutput);

			convertInputToOutputStream2(inputFile, fileOutput);
			FileInputStreamExample.readFileComplete(fileOutput);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Reads 20 bytes at a time and writes to a file.
	 *
	 * @param fileInput the file input
	 * @param fileOutput the file output
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void convertInputToOutputStream(File fileInput,
			File fileOutput) throws IOException {

		System.out
				.println("Begin convertInputToOutputStream(fileInput,fileOutput)");
		InputStream inputStream = null;
		OutputStream outputStream = null;

		//String outputFileName = classLoader.getResource(fileOutput).getFile();
		
		try {
			inputStream = new FileInputStream(fileInput);
			outputStream = new FileOutputStream(fileOutput);

			byte[] bytes = new byte[20];
			/**
			 * Reads 20 bytes at a time and stores them into 'bytes'. Returns number of bytes read.
			 * Returns -1 when no bytes are left to read.
			 */
			while ((inputStream.read(bytes)) != -1) {
				outputStream.write(bytes);
				System.out.println("20 Bytes->" + new String(bytes));
			}
			/*
			 * inputStream.read(bytes); String string = new String(bytes);
			 * System.out.println(string);
			 */
		} finally {
			if (inputStream != null) {
				inputStream.close();
			}
			if (outputStream != null) {
				outputStream.close();
			}
		}

		System.out.println("Done!");
	}

	/**
	 * Convert input to output stream2.
	 * Converts complete input bytes to output bytes in 1 go.
	 *
	 * @param fileInput the file input
	 * @param fileOutput the file output
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void convertInputToOutputStream2(File fileInput,
			File fileOutput) throws IOException {
		InputStream inputStream = null;
		OutputStream outputStream = null;
		System.out
				.println("Begin convertInputToOutputStream2(fileInput,fileOutput)");

		try {
			inputStream = new FileInputStream(fileInput);
			outputStream = new FileOutputStream(fileOutput);

			int size = inputStream.available();
			byte b[] = new byte[size];

			inputStream.read(b);
			outputStream.write(b);

		} finally {
			if (inputStream != null) {
				inputStream.close();
			}
			if (outputStream != null) {
				outputStream.close();
			}

		}
		System.out.println("Done!");
	}

}