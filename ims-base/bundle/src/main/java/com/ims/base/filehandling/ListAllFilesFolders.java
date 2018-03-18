package com.ims.base.filehandling;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class ListAllFilesFolders.
 */
public class ListAllFilesFolders {
	
	
	/**
	 * Recursive function to get all the files from a directory 
	 * and the sub directories.
	 *
	 * @param directoryName the directory name
	 * @param files the files
	 */
	public static void listf(String directoryName, List<File> files) {
	    File directory = new File(directoryName);

	    // get all the files from a directory
	    File[] fList = directory.listFiles();
	    for (File file : fList) {
	        if (file.isFile()) {
	            files.add(file);
	        } else if (file.isDirectory()) {
	        	System.out.println("Folder :: " + file);
	            listf(file.getAbsolutePath(), files);
	        }
	    }
	}
	
	/**
	 * List exe files.
	 *
	 * @param directoryName the directory name
	 * @return the list
	 */
	/*public static List<File> listExeFiles(String directoryName){
		File directory = new File(directoryName);
		List<File> xmlFileList = Arrays.asList(directory.listFiles(new FilenameFilter(){
	        @Override
	        public boolean accept(File dir, String name) {
	            return name.endsWith(".exe"); // or something else
	        }}));
		return xmlFileList;
	}*/
	
	public static List<File> listExeFiles(String directoryName){
		File directory = new File(directoryName);
		FilenameFilter filenameFilter = new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(".exe"); // or something else
			}
		};
		/**
		 * listFiles method internally calls the accept method of the anonymous
		 * class which implements the FilenameFilter interface.
		 */
		List<File> xmlFileList = Arrays.asList(directory.listFiles(filenameFilter));
		return xmlFileList;
	}
	
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		List<File> fileFolderList = new ArrayList<>();
		System.out.println("List All files and folder -");
		listf("H:/IMS PROJECT/pkg",fileFolderList );
		System.out.println("Files :: "+fileFolderList);
				
		System.out.println();
		System.out.println("List xml files -");
		FilenameFilter filenameFilter = new OnlyExt("xml");
		File directory = new File("H:/IMS PROJECT/Created Xmls");
		String[] arrayFiles = directory.list(filenameFilter);
		System.out.print("Array of filepath :: ");
		for (String s : arrayFiles) {
			System.out.print(s + " , ");
		}
		System.out.println();
		File[] listFiles = directory.listFiles(filenameFilter);
		System.out.println("List of file objects :: " + Arrays.asList(listFiles));
		
		System.out.println();
		System.out.println("List exe files -");
		List<File> listXmlFiles = listExeFiles("H:/software");
		System.out.println(listXmlFiles);
		
	}
	
	

}
