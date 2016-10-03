package com.edu.route;

public class OneWayTrip extends TripType {

	public OneWayTrip(Routing a) {
		super(a);
	}

	@Override
	public void callAlgorithm(String source, String dest) {
		useAlgorithm(source, dest);
	}

}
