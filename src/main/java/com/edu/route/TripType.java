package com.edu.route;

public abstract class TripType {

	protected Routing algorithm;

	public abstract void callAlgorithm(String source, String dest);

	public TripType(Routing a) {
		algorithm = a;
	}

	protected void useAlgorithm(String source, String dest) {
		algorithm.useAlgorithm(source, dest);
	}

}
