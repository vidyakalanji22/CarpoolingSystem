package com.edu.request;

public class RequestContext {

	private RequestState requestState = new WaitingState();
	private Request request;

	public RequestContext(Request request) {
		this.request = request;
	}

	public void setRequestState(RequestState requestState) {
		this.requestState = requestState;
	}

	public void showRequestStatus() {
		requestState.processRequest(request);
	}
}
