package com.ims.base.Threading;

import org.apache.commons.lang.StringUtils;

public class Runner implements Runnable{
	/**
	 * using static would be useful if we use extends thread method.
	 * In case of runnable all the threads operate on the
	 * same runnable object hence a non static winner var will also work.
	 */
	//private static String winner;
	private String winner;
	
	@Override
	public void run() {
		this.runMy();
		
	}

	private void runMy() {
		for (int i = 1; i <= 10; i++) {
			System.out.println("Distance covered by " + Thread.currentThread().getName() +" " + i);
			if(i==5&&Thread.currentThread().getName().equals("Loser")){
				try {
					System.out.println("Arjun Sleep for 5 sec when distance = 5");
					Thread.sleep(1000*5);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			boolean isRaceDone = isRaceDone(i);
			if(isRaceDone == true){
				System.out.println("Race completed by " + Thread.currentThread().getName());
				//break;
			}
		}
	}

	private boolean isRaceDone(int distance) {
		boolean isRaceDone = false; 
		if(StringUtils.isEmpty(winner) && distance==10){
			winner = Thread.currentThread().getName();
			System.out.println("Winner " + winner);
			isRaceDone = true;
		}else if(StringUtils.isEmpty(winner)){
			isRaceDone = false;
		}else if(distance==10){
			//System.out.println("Current thread " + Thread.currentThread().getName() +" Total Distance covered " + distance);
			isRaceDone = true;
		}
		return isRaceDone;
	}

}
