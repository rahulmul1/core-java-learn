package com.ims.base.Threading;

public class TestBusBooking {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BusRunnable busRunnable = new BusRunnable(); 
		Thread pt1 = new PassengerThread(2, busRunnable);
		pt1.setName("Passenger1");
		Thread pt2 = new PassengerThread(2,busRunnable);
		pt2.setName("Passenger2");
		
		pt1.start();
		pt2.start();
	}

}
