package com.edu.request;

public class Request {

	private int requestId;
	RequestState state;
	private String requestType;
	private int numOfPeople;

	private String pickUpLoc;
	private String destination;

	private int startX, startY;
	private int endX, endY;

	private String requestDate;
	private int requesTime;
	private String CarType;
	private String tripType;
	private String passengerName;
	private float fareCalculated;

	public Request(int requestId, String pickUpLoc, String dest, String requestDate, int requestTime, int numOfPeople,
			String tripType) {
		this.requestId = requestId;
		this.pickUpLoc = pickUpLoc;
		this.destination = dest;
		this.requestDate = requestDate;
		this.numOfPeople = numOfPeople;
		this.tripType = tripType;
		this.requesTime = requestTime;
	}

	public float getFareCalculated() {
		return fareCalculated;
	}

	public void setFareCalculated(float fareCalculated) {
		this.fareCalculated = fareCalculated;
	}

	public int getRequesTime() {
		return requesTime;
	}

	public void setRequesTime(int requesTime) {
		this.requesTime = requesTime;
	}

	public String getPassengerName() {
		return passengerName;
	}

	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}

	public String getTripType() {
		return tripType;
	}

	public void setTripType(String tripType) {
		this.tripType = tripType;
	}

	public int getNumOfPeople() {
		return numOfPeople;
	}

	public void setNumOfPeople(int numOfPeople) {
		this.numOfPeople = numOfPeople;
	}

	public int getRequestId() {
		return requestId;
	}

	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	public String getPickUpLoc() {
		return pickUpLoc;
	}

	public void setPickUpLoc(String pickUpLoc) {
		this.pickUpLoc = pickUpLoc;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public int getStartX() {
		return startX;
	}

	public void setStartX(int startX) {
		this.startX = startX;
	}

	public int getStartY() {
		return startY;
	}

	public void setStartY(int startY) {
		this.startY = startY;
	}

	public int getEndX() {
		return endX;
	}

	public void setEndX(int endX) {
		this.endX = endX;
	}

	public int getEndY() {
		return endY;
	}

	public void setEndY(int endY) {
		this.endY = endY;
	}

	public String getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(String requestDate) {
		this.requestDate = requestDate;
	}

	public String getCarType() {
		return CarType;
	}

	public void setCarType(String carType) {
		CarType = carType;
	}

	public Request() {

	}

	public Request(String requestType, String pickUpLoc, String destination, String requestDate, String CarType) {
		this.requestType = requestType;
		this.pickUpLoc = pickUpLoc;
		this.destination = destination;
		this.requestDate = requestDate;
		this.CarType = CarType;
	}

	public RequestState getState() {
		return state;
	}

	public void setState(RequestState s) {
		this.state = s;
	}

	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder();
		sb.append("Request:");
		sb.append("\n___________________________________");

		sb.append("\nPickup Location: " + pickUpLoc + " [" + startX + "," + startY + "]");
		sb.append("\nDestination: " + destination + " [" + endX + "," + endY + "]");
		sb.append("\nBooking Date: " + requestDate);
		sb.append("\nType of trip: " + tripType);
		sb.append("\nPassenger count: " + numOfPeople);

		String string = sb.toString();

		return string;
	}

}
