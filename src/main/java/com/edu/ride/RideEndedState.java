package com.edu.ride;

public class RideEndedState implements RideState {

	public void scheduleRide(Ride ride) {
		System.out.println("Ride has been Completed  for request id :" + ride.getRequest_id());
	}

}
