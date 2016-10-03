package com.edu.track;

import com.edu.ride.Ride;

public class TrackContext implements TrackInterface {

	private TrackState trackState = new StartedState();
	private Ride ride;

	public TrackContext(Ride ride) {
		this.ride = ride;
	}

	public void setTrackState(TrackState trackState) {
		this.trackState = trackState;
	}

	public void showTrackStatus() {
		trackState.track(ride);
	}

}
