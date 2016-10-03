package com.edu.ride;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.edu.notification.NotificationObservable;
import com.edu.notification.NotificationObserver;
import com.edu.vehicle.Vehicle;

public class Ride implements NotificationObservable {

	private List<NotificationObserver> users = new ArrayList<NotificationObserver>();

	private int request_id;
	private int driver_id;
	private Date start_time;
	private Date end_time;
	private float fare;
	private String payment_type;
	private String status;
	private float user_rating;
	private int driver_rating;
	private String pickUpLoc;
	private String destination;
	private String rideDate;
	private int rideTime;
	private Vehicle vehicle;

	public Ride() {

	}

	public Ride(int requestId, int driverId, String pickUp, String dest, String rideDate) {
		this.request_id = requestId;
		this.driver_id = driverId;
		this.pickUpLoc = pickUp;
		this.destination = dest;
		this.rideDate = rideDate;
	}

	public String getRideDate() {
		return rideDate;
	}

	public void setRideDate(String rideDate) {
		this.rideDate = rideDate;
	}

	public int getRideTime() {
		return rideTime;
	}

	public void setRideTime(int rideTime) {
		this.rideTime = rideTime;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
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

	public int getRequest_id() {
		return request_id;
	}

	public void setRequest_id(int request_id) {
		this.request_id = request_id;
	}

	public int getDriver_id() {
		return driver_id;
	}

	public void setDriver_id(int driver_id) {
		this.driver_id = driver_id;
	}

	public Date getStart_time() {
		return start_time;
	}

	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}

	public Date getEnd_time() {
		return end_time;
	}

	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}

	public float getFare() {
		return fare;
	}

	public void setFare(float fare) {
		this.fare = fare;
	}

	public String getPayment_type() {
		return payment_type;
	}

	public void setPayment_type(String payment_type) {
		this.payment_type = payment_type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public float getUser_rating() {
		return user_rating;
	}

	public void setUser_rating(float user_rating) {
		this.user_rating = user_rating;
	}

	public int getDriver_rating() {
		return driver_rating;
	}

	public void setDriver_rating(int driver_rating) {
		this.driver_rating = driver_rating;
	}

	public String RideName() {
		return "Sample Ride";
	}

	public int RideRequestID() {
		return 110;
	}

	public void addObserver(NotificationObserver o) {
		users.add(o);

	}

	public void removeObserver(NotificationObserver o) {
		users.remove(o);

	}

	public void notifyObserver(String name) {
		if (!name.equals(null))
			System.out.println("Ride Notification for " + name);
		for (NotificationObserver user : users) {
			user.receiveNotification();
		}

	}
}
