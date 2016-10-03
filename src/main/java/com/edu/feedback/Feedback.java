package com.edu.feedback;

import java.io.BufferedReader;
import java.io.IOException;

import com.edu.client.Client;
import com.edu.ride.Ride;

public class Feedback {

	private int id;
	private String feedback;
	private String passengerName;
	private float rating;

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	public String getPassengerName() {
		return passengerName;
	}

	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public void setFeedback(Ride ride) throws IOException {
		System.out.println("====================================================================");
		System.out.println("Your ride has been completed, Do you want to leave feedback?: [Y/N]");
		BufferedReader reader = Client.getReader();
		String input = reader.readLine();
		if (input.equalsIgnoreCase("y")) {
			System.out.println("Rate the driver from 1 to 5. ");
			String s = reader.readLine();
			if (s.isEmpty() || s.length() >= 2 || Integer.parseInt(s) > 5 || Integer.parseInt(s) < 1) {
				System.out.println("Wrong Input. Please enter again.");
			} else {
				float rating = Integer.parseInt(s);
				ride.setUser_rating(rating);
				this.setRating(rating);
				System.out.println("Please enter your comment");
				String comment = reader.readLine();
				System.out.println(
						"Your comment: " + comment + "\nrating: " + rating + " stars. Thank you for riding with us!");
			}
		}
	}

}
