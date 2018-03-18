package com.ims.base.interviewalgo;
/**
 * Remove duplicates from sorted array.
 */
public class RemoveDuplicates {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] input = new int[] { 1, 1, 3, 7, 7, 8, 9, 9, 9, 10, 10, 10 };
		int length=input.length;
		for(int i=0;i<length;i++){
			for(int j=i+1;j<length;j++){
				if(input[i]==input[j]){
					remove(input ,j,length);
					j--;
					length--;
				}else{
					break;
				}
			}
		}
		for (int i = 0; i < length; i++) {
			System.out.println(input[i]);
		}
		
		System.out.println("Correct way");
		removeDuplicate(input);
		
		
	}

	private static void remove(int[] input,int j,int length) {
		for(int i=j;i<length-1;i++){
			input[i]=input[i+1];
		}
	}
	
	
	
	private static void removeDuplicate(int[] input){
		int j=0;
		for (int i = 0; i < input.length; i++) {
			if(input[i]==input[j]){
				
			}else{
				input[++j] = input[i];
			}
		}
		for (int i = 0; i < j+1; i++) {
			System.out.println(input[i]);
		}
	}
	
	

}
