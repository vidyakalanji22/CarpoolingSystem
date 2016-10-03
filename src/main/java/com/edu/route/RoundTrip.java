package com.edu.route;

public class RoundTrip extends TripType {

	public RoundTrip(Routing a) {
		super(a);
	}

	@Override
	public void callAlgorithm(String source, String dest) {
		useAlgorithm(source, dest);
	}

}
