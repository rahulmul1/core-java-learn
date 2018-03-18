package com.ims.base.filehandling;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import com.ims.base.filehandlingchar.FileReaderWriterExample;

// TODO: Auto-generated Javadoc
/**
 * The Class FileInputStreamExample.
 */
public class FileInputStreamExample {

	/**
	 * The main method.
	 * 
	 * @param args
	 *            the arguments
	 * @throws Exception
	 *             the exception
	 */
	public static void main(String args[]) throws Exception {

		readFileComplete("H:/IMS PROJECT/Created Xmls/Employee.xml");
		
		readFileCompleteWithBytes("H:/IMS PROJECT/Created Xmls/Employee.xml");
		
		allOperationFIS();
		
		/**
		 * Demonstrates Char stream vs byte stream.
		 * Check the output difference for the next 4 methods.
		 * 
		 * Byte stream is not able to read the "utf8file.txt" while it reads notutf8file.txt correctly.
		 * Char stream is able to read utf-8 file.
		 * 
		 */
		readFileComplete("H:/IMS PROJECT/Created Xmls/utf8file.txt");
		
		readFileComplete("H:/IMS PROJECT/Created Xmls/notutf8file.txt");
		
		FileReaderWriterExample.readFileComplete("H:/IMS PROJECT/Created Xmls/utf8file.txt");
		
		FileReaderWriterExample.readFileComplete("H:/IMS PROJECT/Created Xmls/notutf8file.txt");
		
		/**
		 * 
		 * Both byte and char stream here are not able to read docx. But there is a separate way 
		 * of reading the docx file through byte stream. docx cant be read thru char stream.
		 * 
		 */
		readFileComplete("H:/IMS PROJECT/Created Xmls/ReadWithByteStream.docx");
		
		FileReaderWriterExample.readFileComplete("H:/IMS PROJECT/Created Xmls/ReadWithByteStream.docx");
	}

	/**
	 * All operation fis.
	 * 
	 * Method explains all operations/methods of input stream/file input stream.
	 * 
	 * @throws FileNotFoundException
	 *             the file not found exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	private static void allOperationFIS() throws FileNotFoundException,
			IOException {
		
		System.out.println("Begin allOperationFIS()");
		
		int size;
		InputStream f = new FileInputStream(
				"H:/IMS PROJECT/Created Xmls/Employee.xml");

		System.out.println("Inside Main");
		System.out.println("Total Available Bytes: " + (size = f.available()));
		int n = size / 40;
		System.out.println("First " + n
				+ " bytes of the file one read() at a time");
		for (int i = 0; i < n; i++) {
			System.out.print((char) f.read());
		}
		System.out.println("\nStill Available: " + f.available());
		System.out.println("Reading the next " + n + " with one read(b[])");
		byte b[] = new byte[n];
		// reads n bytes and assigns it to b
		if (f.read(b) != n) {
			System.err.println("couldn't read " + n + " bytes.");
		}
		for (int i = 0; i < b.length; i++) {
			System.out.print((char) b[i]);
		}
		System.out.println();

		System.out.println(new String(b));
		System.out.println(new String(b, 0, n));
		System.out.println("\nStill Available: " + (size = f.available()));
		System.out.println("Skipping half of remaining bytes with skip()");
		f.skip(size / 2);
		System.out.println("Still Available: " + f.available());
		System.out.println("Reading " + n / 2 + " into the end of array");
		if (f.read(b, n / 2, n / 2) != n / 2) {
			System.err.println("couldn't read " + n / 2 + " bytes.");
		}
		System.out.println(new String(b, 0, b.length));
		System.out.println("\nStill Available: " + f.available());
		f.close();
	}

	/**
	 * Read file complete.
	 * 
	 * Methods helps to read any file and display entire file on console
	 * Reads 1 byte at a time. Therefore loop until the byte is -1.
	 * 
	 * @param path
	 *            the path
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static void readFileComplete(String path) throws IOException {
		
		System.out.println("Begin readFileComplete(String path)");
		
		InputStream is = new FileInputStream(path);
		int content = is.read();
		while (content != -1) {
			System.out.print((char) content);
			content = is.read();
		}
		System.out.println();
		is.close();
		System.out.println("End readFileComplete(String path)");
		
	}
	
	/**
	 * Converts input stream/file/bytes to string.
	 * Reads total available bytes in 1 go and displays on console after converting bytes to string.
	 *
	 * @param path the path
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void readFileCompleteWithBytes(String path) throws IOException {
		
		System.out.println("Begin readFileCompleteWithBytes(String path)");
		
		InputStream is = new FileInputStream(path);
		int available = is.available();
		System.out.println("Available Bytes in file ->" + available);
		byte b[] = new byte[available];
		is.read(b);
		//dosnt work
		String string = b.toString();
		System.out.println(string);
		
		//works
		String string2 = new String(b);
		System.out.println(string2);
		System.out.println();
		is.close();
	}

	
}