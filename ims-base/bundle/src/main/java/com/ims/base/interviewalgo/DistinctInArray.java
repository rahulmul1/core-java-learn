package com.ims.base.interviewalgo;
/**
 * Remove duplicates from unsorted array. 
 */
public class DistinctInArray {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] input = {4,2,7,2,4,7,8,2,3};
		int length = input.length;
		
		
		for (int i = 0; i < length; i++) {
			boolean isDistinct= true;
			for (int k = i+1; k < length; k++) {
				if(input[i]==input[k]){
					isDistinct = false;
					break;
				}
			}
			if(isDistinct){
				System.out.println(input[i]);
			}
		}
		
		System.out.println("Find only distinct");
		onlyDistinct();
		
	}
	
	
	private static void onlyDistinct() {
		int[] input = {4,2,7,2,4,7,8,2,3};
		int length = input.length;
		
		
		for (int i = 0; i < length; i++) {
			boolean isDistinct= true;
			for (int k = 0; k < length; k++) {
				if (k != i) {
					if (input[i] == input[k]) {
						isDistinct = false;
						break;
					}
				}
			}
			if(isDistinct){
				System.out.println(input[i]);
			}
		}
	}

}
