package com.edu.track;

import com.edu.ride.Ride;

public class CompletedState implements TrackState {

	public void track(Ride ride) {
		System.out.println("your ride " + ride.getRequest_id() + " has been completed. Have a good day");
	}

}
