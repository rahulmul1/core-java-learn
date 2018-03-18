package com.ims.base.filehandling;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;

public class IosUtilsExamples {

	public static void main(String[] args) {
		
		InputStream is = null;
		File file = null;
		try{
			file = new File("H:/IMS PROJECT/Employee.xml");
			is = new FileInputStream(file);
			String string = IOUtils.toString(is);
			System.out.println(string);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				if (is != null)
					is.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
}
