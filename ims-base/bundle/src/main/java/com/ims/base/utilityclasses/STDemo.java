package com.ims.base.utilityclasses;

import java.util.StringTokenizer;

class STDemo {
	static String in = "title=Java: The Complete Reference;"
			+ "author=Schildt;" + "publisher=Osborne/McGraw-Hill;"
			+ "copyright=2002";

	public static void main(String args[]) {
		StringTokenizer st = new StringTokenizer(in, "=;");
		while (st.hasMoreTokens()) {
			String key = st.nextToken();
			String val = st.nextToken();
			System.out.println(key + "\t" + val);
		}

		StringTokenizer st1 = new StringTokenizer("this is a test");
		while (st1.hasMoreTokens()) {
			System.out.println(st1.nextToken());
		}

		/**
		 * StringTokenizer is a legacy class that is retained for compatibility
		 * reasons although its use is discouraged in new code. It is
		 * recommended that anyone seeking this functionality use the split
		 * method of String or the java.util.regex package instead.
		 */
		String[] result = "this is a test".split("\\s");
		for (int x = 0; x < result.length; x++)
			System.out.println(result[x]);
	}

}