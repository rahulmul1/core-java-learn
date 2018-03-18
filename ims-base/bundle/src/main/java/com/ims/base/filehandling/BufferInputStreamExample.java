package com.ims.base.filehandling;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

// TODO: Auto-generated Javadoc
/**
 * The Class BufferInputStreamExample.
 */
public class BufferInputStreamExample {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		try {
			readBufferFile("H:/IMS PROJECT/Created Xmls/Employee.xml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Read buffer file.
	 * The bufferinput stream acts as a wrapper over the file input stream and 
	 * add functionality of increased performance.
	 *
	 * @param filePath the file path
	 * @return the string
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static String readBufferFile(String filePath) throws IOException {
		InputStream fis = null;
		InputStream bis = null;
		try {
			File file = new File(filePath);
			fis = new FileInputStream(file);
			bis = new BufferedInputStream(fis);

			byte b[] = new byte[fis.available()];

			bis.read(b);

			String content = new String(b);
			System.out.println(content);
			return content;

		} finally {
			try {
				fis.close();
				bis.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

	}
}