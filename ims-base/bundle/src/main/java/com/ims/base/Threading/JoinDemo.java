package com.ims.base.Threading;

import java.lang.Thread.State;

class JoinThread extends Thread {
	Boolean hasJoined;

	@Override
	public void run() {
		hasJoined=false;
		for (int i = 1; i <= 10; i++) {
			System.out.println(Thread.currentThread().getName()
					+ " with priority "
					+ Thread.currentThread().getPriority()+ " and state " + Thread.currentThread().getState()+ " distance covered " + i);
			if(Thread.currentThread().getName().equals("FirstThread")&& hasJoined!=true){
				JoinDemo.thread2 = new Thread(new JoinThread(),"SecondThread"); 
				//thread2.setName("SecondThread")
				JoinDemo.thread2.setPriority(1);
				hasJoined = true;
				try {
					JoinDemo.thread2.start();
					JoinDemo.thread2.join();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(Thread.currentThread().getName().equals("SecondThread")&& i==3){
				System.out.println("Interrupt called");
				JoinDemo.thread1.interrupt();
			}
		}
	}
}

public class JoinDemo {
	public static Thread thread1;
	public static Thread thread2;
	//Thread thread1 = new Thread(jt);
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JoinThread jt = new JoinThread();
		thread1=new Thread(jt,"FirstThread");
		thread1.setPriority(10);
		thread1.start();
		
		/*while(true){
			System.out.println(System.nanoTime()+ " First thread state "+ thread1.getState());
			if(thread1.getState().equals(State.TERMINATED)){
				break;
			}
		}*/
		
	}

}
