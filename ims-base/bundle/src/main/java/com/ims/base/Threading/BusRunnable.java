package com.ims.base.Threading;

public class BusRunnable implements Runnable {
	int totalSeatsAvailable=3;

	@Override
	public void run() {
		
		PassengerThread pt = (PassengerThread) Thread.currentThread();
		bookTickets(pt);

	}

	/**
	 * @param pt
	 */
	private synchronized void bookTickets(PassengerThread pt) {
		System.out.println("Welcome " + Thread.currentThread().getName()
				+ " Number of seats available are : " + totalSeatsAvailable);
		if (totalSeatsAvailable >= pt.seatsNeeded) {
			System.out.println("Congrats "+pt.seatsNeeded + " seats booked for "
					+ pt.getName());
			totalSeatsAvailable = totalSeatsAvailable - pt.seatsNeeded;
		} else {
			System.out
					.println("Sorry no of seats needed not available. Seats available are "
							+ totalSeatsAvailable);
		}
	}

}
