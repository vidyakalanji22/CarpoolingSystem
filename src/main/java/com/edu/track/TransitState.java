package com.edu.track;

import com.edu.ride.Ride;

public class TransitState implements TrackState {

	public void track(Ride ride) {
		System.out.println("your ride " + ride.getRequest_id() + " is in transit. Please wait for it");
	}

}
