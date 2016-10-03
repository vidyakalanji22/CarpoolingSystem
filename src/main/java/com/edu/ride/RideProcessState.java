package com.edu.ride;

public class RideProcessState implements RideState {

	public void scheduleRide(Ride ride) {

		System.out.println("Your ride request : " + ride.getRequest_id() + " is in Process State");

	}

}
