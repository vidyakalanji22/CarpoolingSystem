package com.edu.request;

public class EvaluatingState implements RequestState {

	public void processRequest(Request request) {
		System.out.println("Your request for " + request.getRequestId() + " has been evaluated.. ");

	}

}
