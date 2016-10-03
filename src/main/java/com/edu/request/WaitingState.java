package com.edu.request;

public class WaitingState implements RequestState {

	public void processRequest(Request request) {
		System.out.println("Your request for " + request.getRequestId() + " is in waiting to be evaluated");

	}

}
