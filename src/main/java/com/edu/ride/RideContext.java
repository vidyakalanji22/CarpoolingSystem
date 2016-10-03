package com.edu.ride;

public class RideContext {

	private RideState rideState = new RideScheduledState();
	private Ride ride;

	public RideContext(Ride ride) {
		this.ride = ride;
	}

	public void setRideState(RideState rideState) {
		this.rideState = rideState;
	}

	public void showRideStatus() {
		rideState.scheduleRide(ride);
	}
}
