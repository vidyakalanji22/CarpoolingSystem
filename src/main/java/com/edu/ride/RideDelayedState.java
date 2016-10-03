package com.edu.ride;

public class RideDelayedState implements RideState {

	public void scheduleRide(Ride ride) {

		System.out.println("Your ride : " + ride.getRequest_id() + " is Delayed");

	}

}
