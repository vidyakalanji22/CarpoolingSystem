package com.edu.ride;

public class RideScheduledState implements RideState {

	public void scheduleRide(Ride ride) {
		System.out.println("Ride has been Scheduled for request id :" + ride.getRequest_id());

	}

}
