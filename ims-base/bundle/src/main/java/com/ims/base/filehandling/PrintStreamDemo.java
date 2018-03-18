package com.ims.base.filehandling;

import java.io.PrintStream;


public class PrintStreamDemo {

   public static void main(String[] args) {
      Object c = 15;

      // create printstream object
      PrintStream ps = new PrintStream(System.out);

      // print an object and change line
      ps.println(c);
      ps.print("New Line");

      // flush the stream
      ps.flush();

   }
}