package com.ims.base.Threading;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

class ThreadSum extends Thread{
	public static List<Integer> readData;
	static int size;
	int startindex;
	int endindex;
	static int sum = 0;
	
	public ThreadSum(int start , int end) {
		startindex = start;
		endindex = end;
	}
	
	public static void readData(){
		List<Integer> list1= new ArrayList<>();
		File file = new File("");
		String s = StringUtils.EMPTY;
		InputStream io =null;
		try {
			io = new FileInputStream(file);
			byte b[] = new byte[io.available()];
			io.read(b);
			s = new String(b);

			String[] sarray = StringUtils.splitByWholeSeparator(s, ",");
			
			for(String s2:sarray){
				list1.add(Integer.parseInt(s2));
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				io.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		readData = list1;
		size= readData.size();
	}	
	
	
	@Override
	public void run() {

		for (int i = startindex; i <= endindex; i++) {
			sum = sum + readData.get(i);
		}
	}
	
}



/**
 * The Class ThreadTest.
 */
public class ThreadTest {
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		ThreadSum.readData();
		int size = ThreadSum.size;
		int halfSize = size/2;
		ThreadSum t1= new ThreadSum(0,halfSize);
		t1.start();		
		
		ThreadSum t2= new ThreadSum(halfSize+1,size);
		t2.start();
		
	}
}