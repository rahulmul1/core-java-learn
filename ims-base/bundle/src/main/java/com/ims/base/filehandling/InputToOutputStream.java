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
	 */
	public static void main(String[] args) {

		String fileInput = "H:/IMS PROJECT/Created Xmls/Employee.xml";
		String fileOutput = "H:/IMS PROJECT/Created Xmls/InputToOutput.xml";
		try {
			convertInputToOutputStream(fileInput, fileOutput);
			FileInputStreamExample.readFileComplete(fileOutput);

			convertInputToOutputStream2(fileInput, fileOutput);
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
	public static void convertInputToOutputStream(String fileInput,
			String fileOutput) throws IOException {

		System.out
				.println("Begin convertInputToOutputStream(fileInput,fileOutput)");
		InputStream inputStream = null;
		OutputStream outputStream = null;

		try {
			inputStream = new FileInputStream(new File(fileInput));
			outputStream = new FileOutputStream(new File(fileOutput));

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
	public static void convertInputToOutputStream2(String fileInput,
			String fileOutput) throws IOException {
		InputStream inputStream = null;
		OutputStream outputStream = null;
		System.out
				.println("Begin convertInputToOutputStream2(fileInput,fileOutput)");

		try {
			inputStream = new FileInputStream(new File(fileInput));
			outputStream = new FileOutputStream(new File(fileOutput));

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