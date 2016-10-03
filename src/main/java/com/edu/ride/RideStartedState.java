package com.edu.ride;

public class RideStartedState implements RideState {

	public void scheduleRide(Ride ride) {

		System.out.println("Your ride : " + ride.getRequest_id() + " has Started ");

	}

}
