package com.edu.ride;

public class RideInTransitState implements RideState {

	public void scheduleRide(Ride ride) {
		System.out.println("Ride is in In Transit for request id :" + ride.getRequest_id());
	}

}
