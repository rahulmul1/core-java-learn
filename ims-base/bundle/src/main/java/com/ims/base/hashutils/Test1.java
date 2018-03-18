package com.ims.base.hashutils;

import java.util.Scanner;

public class Test1 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		for (int i = 0; i < n; i++) {
			int a = sc.nextInt();
			System.out.println(a);
		}
	}
	
	static int isSumPossible(int a[],int N){
		int isSumPosisble = 0;
		for (int i = 0; i < a.length; i++) {
			for (int j = i+1; j < a.length; j++) {
				if(a[i]+a[j]==N){
					isSumPosisble=1;
					break;
				}
			}
			if(isSumPosisble==1){
				break;
			}
		}
		return isSumPosisble;
	}
	
	
}
