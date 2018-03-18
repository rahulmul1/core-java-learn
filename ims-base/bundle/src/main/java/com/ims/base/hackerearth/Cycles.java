package com.ims.base.hackerearth;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import org.apache.commons.lang.StringUtils;

public class Cycles {
	
	private void findcycle(String fileName) {
		InputStream is = null;
		Reader reader = null;
		BufferedReader br =null;
		String line = StringUtils.EMPTY;
		try {
			is = new FileInputStream(fileName);
			reader = new InputStreamReader(is);
			br = new BufferedReader(reader);
			
			while((line = br.readLine()) !=null){
				String[] arr = StringUtils.splitByWholeSeparator(line, " ");
				
				
				
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
