package com.edu.track;

import com.edu.ride.Ride;

public class StartedState implements TrackState {

	public void track(Ride ride) {
		System.out.println("your ride " + ride.getRequest_id() + " has started");

	}

}
