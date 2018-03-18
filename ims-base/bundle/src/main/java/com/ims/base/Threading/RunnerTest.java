package com.ims.base.Threading;

public class RunnerTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Runner runner =  new Runner();
		Thread thread  = new Thread(runner,"RahulAggarwal");
		Thread thread2 = new Thread(runner,"Loser");
		thread.start();
		thread2.start();
	}

}
