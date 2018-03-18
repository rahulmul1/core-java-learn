package com.ims.base.Threading;

public class PassengerThread extends Thread{
	int seatsNeeded;
	
	public PassengerThread(int seats,Runnable runnable) {
		super(runnable);
		this.seatsNeeded = seats;
	}
	
	public int getSeatsNeeded() {
		return seatsNeeded;
	}

	public void setSeatsNeeded(int seatsNeeded) {
		this.seatsNeeded = seatsNeeded;
	}

}
