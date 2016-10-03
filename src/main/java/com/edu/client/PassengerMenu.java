package com.edu.client;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

import com.edu.request.*;
import com.edu.ride.Ride;
import com.edu.track.TrackContext;
import com.edu.track.TransitState;
import com.edu.util.DataStore;
import com.edu.dispatch.Dispatcher;
import com.edu.member.Passenger;
import com.edu.payment.PaymentContext;

public class PassengerMenu {

	private static Scanner scanner = new Scanner(System.in);

	private static BufferedReader reader = Client.getReader();
	List<Request> requestList = new ArrayList<Request>(); // Request Creation
	static List<Ride> rideList = new ArrayList<Ride>();
	private static String REQ_P_LOC = "PICK_LOCATION";
	private static String REQ_D_LOC = "DROP_LOCATION";
	private static String REQ_DATE = "REQUESTED_DATE";
	private static String REQ_TIME = "REQUESTED_TIME";
	private static String REQ_PPL = "NO_OF_PPL";

	public Passenger register() {
		int basicFee = 25;
		int premiumFee = 50;
		Passenger passenger = new Passenger();
		try {
			System.out.println();
			System.out.println("================================================");
			System.out.println("Customer registration form");
			System.out.println("================================================");
			System.out.println("Please enter details: ");

			System.out.println("Name: ");
			passenger.setName(reader.readLine());

			System.out.println("Contact Number: ");
			passenger.setContactNo(reader.readLine());

			System.out.println("Email: ");
			passenger.setEmail(reader.readLine());

			System.out.println("Membership Plan [Regular/Premium]: ");
			passenger.setPlan(reader.readLine());

			System.out.println("Do you want to Submit registration? [y:n]: ");
			while (!scanner.hasNext("[ynYN]")) {
				System.out.println("Not a valid input");
				scanner.next();
			}
			if (scanner.next().equalsIgnoreCase("y")) {
				if (passenger.getPlan().equalsIgnoreCase("regular")) {
					System.out.println("Your membership fee is $" + basicFee);
					passenger.setMembershipFee(basicFee);
				} else if (passenger.getPlan().equalsIgnoreCase("premium")) {
					System.out.println("Your membership fee is $" + premiumFee);
					passenger.setMembershipFee(premiumFee);
				}
				System.out.println("Which payment method you want to use ? Please choose from the below list ");
				System.out.println("1. Credit card");
				System.out.println("2. Debit card");
				System.out.println("3. Paypal");
				int option = Integer.parseInt(reader.readLine());
				int fee = passenger.getMembershipFee();
				PaymentContext paymentContext = new PaymentContext();
				switch (option) {
				case 1:
					cardDetails(passenger);
					paymentContext.makePayment("creditcard", fee, "Passenger");
					passenger.setPaymentType("creditcard");
					paymentContext.addObserver(passenger);
					paymentContext.notifyObserver(passenger.getName());
					break;
				case 2:
					cardDetails(passenger);
					paymentContext.makePayment("debitcard", fee, "Passenger");
					passenger.setPaymentType("debitcard");
					paymentContext.addObserver(passenger);
					paymentContext.notifyObserver(passenger.getName());
					break;
				case 3:
					paypalDetails(passenger);
					paymentContext.makePayment("paypal", fee, "Passenger");
					passenger.setPaymentType("Paypal");
					paymentContext.addObserver(passenger);
					paymentContext.notifyObserver(passenger.getName());
					break;
				}
				this.addPassengerToFile(passenger.getName(), passenger.getContactNo(), passenger.getEmail(),
						passenger.getCreditCardNo(), passenger.getCvv(), passenger.getExpirationDate(),
						passenger.getPlan(), passenger.getPaymentType());

				System.out.println("You are successfully registered.");
			} else {
				System.out.println("Successfully Cancelled.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void addPassengerToFile(String name, String contactNum, String email, String creditCardNum, int cvv,
			String expDate, String plan, String paymentType) throws Exception {
		File file = new File("C:/EclipseWorkspace/CarpoolingSystem/src/main/resources/passenger.txt");
		FileWriter writer = new FileWriter(file, true);
		writer.append(name + ":" + contactNum + ":" + email + ":" + creditCardNum + ":" + cvv + ":" + expDate + ":"
				+ plan + ":" + paymentType + "\n");
		writer.close();
	}

	private void paypalDetails(Passenger passenger) throws IOException {
		System.out.println("Paypal Email:");
		passenger.setPaypalEmail(reader.readLine());
	}

	private void cardDetails(Passenger passenger) throws IOException {
		System.out.println(" Card Number: ");
		passenger.setCreditCardNo(reader.readLine());

		System.out.println("CVV: ");
		passenger.setCvv(Integer.parseInt(reader.readLine()));

		System.out.println("Date of expiration[MM/yyyy]: ");
		passenger.setExpirationDate(reader.readLine());
		System.out.println("Your card information has been stored in the system");
	}

	public void login() {
		try {
			System.out.println("______________________");
			System.out.println("Customer Login Details: ");
			System.out.println("Please Enter your Username: ");
			String userName = reader.readLine();
			System.out.println(userName);
			String line = null;
			int count = 0;
			String fileName = "C:/EclipseWorkspace/CarpoolingSystem/src/main/resources/passenger.txt";
			FileReader fileReader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			while ((line = bufferedReader.readLine()) != null) {
				if (line.contains(userName))
					count++;
			}
			if (count > 0)
				System.out.println("You are successfully logged into the system.");
			else
				System.out.println("You are not a registered user.");
			bufferedReader.close();
		} catch (Exception e) {
		}
	}

	/**
	 * process PassengerRequests
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<Request> passengerRequests() throws Exception {

		boolean frequestRide = false;
		Map<String, String> pdLocMap = receivePickAndDestin(); // just receive
																// PickUp and
																// Drop

		List<Ride> frequentRideList = DataStore.getFrequestRidesBySourceAndDestin(pdLocMap.get(REQ_P_LOC),
				pdLocMap.get(REQ_D_LOC));
		if (frequentRideList.size() > 0) {
			System.out.println(
					"We have frequent ride list for this source and destination. Interested in taking a look? [Y/N]");
			while (!scanner.hasNext("[ynYN]")) {
				System.out.println("Not a valid input");
				scanner.next();
			}
			String sline = scanner.next();
			if (sline.equalsIgnoreCase("y")) {
				frequestRide = true;
				displayAndProcessFrequestRides(frequentRideList);
			}
		}

		if (!frequestRide) {
			Map<String, String> dateTimePassMap = receiveDateTimePassenger();
			Request request = createRequestFromPassengerInputs(pdLocMap, dateTimePassMap);
			requestList.add(request);
		}

		boolean runagain = false;
		do {
			// Do you want to book any other rides
			System.out.println("Do you want create one more request for any other location? [Y/N]");
			while (!scanner.hasNext("[ynYN]")) {
				System.out.println("Not a valid input");
				scanner.next();
			}
			String s = scanner.next();
			if (s.equalsIgnoreCase("Y")) {
				runagain = true;
				acceptSeparateRideRequests();
			} else {
				runagain = false;
			}
		} while (runagain);
		displayRequestDetails(requestList);
		return requestList;

	}

	private void acceptSeparateRideRequests() throws Exception {
		boolean frequestRide = false;
		Map<String, String> pdLocMap = receivePickAndDestin();
		
		List<Ride> frequentRideList = DataStore.getFrequestRidesBySourceAndDestin(pdLocMap.get(REQ_P_LOC),
				pdLocMap.get(REQ_D_LOC));
		if (frequentRideList.size() > 0) {
			System.out.println(
					"We have frequent ride list for this source and destination. Interested in taking a look? [Y/N]");
			while (!scanner.hasNext("[ynYN]")) {
				System.out.println("Not a valid input");
				scanner.next();
			}
			if (scanner.next().equalsIgnoreCase("y")) {
				frequestRide = true;
				displayAndProcessFrequestRides(frequentRideList);
			}
		}
		
		if (!frequestRide) {
			Map<String, String> dateTimePassMap = receiveDateTimePassenger();
			Request request = createRequestFromPassengerInputs(pdLocMap, dateTimePassMap);
			requestList.add(request);
		}
		
	}

	private Request createRequestFromPassengerInputs(Map<String, String> pdLocMap, Map<String, String> dateTimePassMap)
			throws InterruptedException {

		Request request = new Request();
		request.setRequestId(new Random().nextInt(100)+1000); // assign requestId

		setUpLocation(request, pdLocMap.get(REQ_P_LOC), "PICKUP");
		setUpLocation(request, pdLocMap.get(REQ_D_LOC), "DISTINATION");

		request.setRequestDate(dateTimePassMap.get(REQ_DATE));
		request.setRequesTime(Integer.parseInt(dateTimePassMap.get(REQ_TIME)));
		request.setNumOfPeople(Integer.parseInt(dateTimePassMap.get(REQ_PPL)));

		displayRequestState(request);
		attachFareToRequest(request);

		return request;
	}

	private Map<String, String> receiveDateTimePassenger() throws Exception {

		Map<String, String> dateTimePassMap = new HashMap<String, String>();

		System.out.println("Booking Date[MM/dd/yyyy hh:mm]: ");
		String date = reader.readLine();

		System.out.println("Enter time: ");
		int time = Integer.parseInt(reader.readLine());

		System.out.println("Enter the number of people ");
		int peopleCount = 0;
		do {
			while (!scanner.hasNextInt()) {
				System.out.println("Invalid input. Accepts only numeric");
				scanner.next();
			}
			peopleCount = scanner.nextInt();
			if (peopleCount < 1 || peopleCount > 6)
				System.out.println("Invalid input. Please enter the correct choice");
		} while (peopleCount < 1 || peopleCount > 6);

		dateTimePassMap.put(REQ_DATE, date);
		dateTimePassMap.put(REQ_TIME, String.valueOf(time));
		dateTimePassMap.put(REQ_PPL, String.valueOf(peopleCount));
		return dateTimePassMap;
	}

	private Map<String, String> receivePickAndDestin() throws Exception {

		Map<String, String> pdLocMap = new HashMap<String, String>();
		System.out.println("==============Request ride Details================");
		System.out.println("Please enter ride details");
		HashMap<String, Location> source = LocationMappingSource.getLocationObject().getLocations();
		int countSrc = 1;
		for (String string : source.keySet()) {
			System.out.println(countSrc++ + ". " + string);
		}
		System.out.println("Pick Up Location [pick from above list]: ");
		int index;
		do {
			while (!scanner.hasNextInt()) {
				System.out.println("Invalid input. Accepts only numeric");
				scanner.next();
			}
			index = scanner.nextInt();
			if (index < 1 || index > 4)
				System.out.println("Invalid input. Please enter the correct choice");
		} while (index < 1 || index > 4);
		
		// index = scanner.nextInt();
		String enteredPickUp = (String) (source.keySet().toArray()[index - 1]);

		HashMap<String, Location> dest = LocationMappingDest.getLocationObject().getLocations();
		int countDest = 1;
		for (String string : dest.keySet()) {
			System.out.println(countDest++ + ". " + string);
		}
		System.out.println("Enter Destination [pick from above list]:  ");
		do {
			while (!scanner.hasNextInt()) {
				System.out.println("Invalid input. Accepts only numeric");
				scanner.next();
			}
			index = scanner.nextInt();
			if (index < 1 || index > 4)
				System.out.println("Invalid input. Please enter the correct choice");
		} while (index < 1 || index > 4);
		
		String enteredDestination = (String) (dest.keySet().toArray()[index - 1]);

		pdLocMap.put(REQ_P_LOC, enteredPickUp);
		pdLocMap.put(REQ_D_LOC, enteredDestination);

		return pdLocMap;
	}

	private void setUpLocation(Request request, String enteredLoc, String type) {

		if (type.equalsIgnoreCase("PICKUP")) {
			HashMap<String, Location> locations = LocationMappingSource.getLocationObject().getLocations();
			Location location = locations.get(enteredLoc);
			request.setStartX(location.x);
			request.setStartY(location.y);
			request.setPickUpLoc(enteredLoc);
		} else if (type.equalsIgnoreCase("DISTINATION")) {
			HashMap<String, Location> locations = LocationMappingDest.getLocationObject().getLocations();
			Location location = locations.get(enteredLoc);
			request.setEndX(location.x);
			request.setEndY(location.y);
			request.setDestination(enteredLoc);
		}
	}

	private void displayRequestState(Request request) throws InterruptedException {
		System.out.println("Your request has been created ");
		RequestContext requestContext = new RequestContext(request);
		requestContext.showRequestStatus();
		Thread.sleep(2000);
		requestContext.setRequestState(new EvaluatingState());
		requestContext.showRequestStatus();
		Thread.sleep(2000);
		requestContext.setRequestState(new ProcessedState());
		requestContext.showRequestStatus();
		Thread.sleep(2000);
	}

	private void displayAndProcessFrequestRides(List<Ride> frequentRideList) throws IOException, InterruptedException {
		System.out.println("");
		//System.out.println("frequentRideList size:" + frequentRideList.size());
		for (int i = 0; i < frequentRideList.size(); i++) {
			System.out.println("=========================================");
			System.out.println(i + ".");
			System.out.println("Pick Up: " + frequentRideList.get(i).getPickUpLoc());
			System.out.println("Destination: " + frequentRideList.get(i).getDestination());
			System.out.println("Ride Day: " + frequentRideList.get(i).getRideDate());
			System.out.println("Ride Time: " + frequentRideList.get(i).getRideTime());
		}

		System.out.println("Do u want to choose from above list? [Y/N]");
		while (!scanner.hasNext("[ynYN]")) {
			System.out.println("Not a valid input");
			scanner.next();
		}
		String sline = scanner.next();
		if (sline.equalsIgnoreCase("Y")) {
			for (int i = 0; i < frequentRideList.size(); i++) {

				System.out.println("Please choose any ride from the above list");
				int option = Integer.parseInt(reader.readLine());
				System.out.println("Please enter the num of people for this ride");
				int peopleCount = 0;
				do {
					while (!scanner.hasNextInt()) {
						System.out.println("Invalid input. Accepts only numeric");
						scanner.next();
					}
					peopleCount = scanner.nextInt();
					if (peopleCount < 1 || peopleCount > 6)
						System.out.println("Invalid input. Please enter the correct choice");
				} while (peopleCount < 1 || peopleCount > 6);
				
				Request request = new Request(new Random().nextInt(100) + 500,
						frequentRideList.get(option).getPickUpLoc(), frequentRideList.get(option).getDestination(),
						frequentRideList.get(option).getRideDate(), frequentRideList.get(option).getRideTime(),
						peopleCount, "oneway");

				setUpLocation(request, frequentRideList.get(option).getPickUpLoc(), "PICKUP");
				setUpLocation(request, frequentRideList.get(option).getDestination(), "DISTINATION");

				displayRequestState(request);
				attachFareToRequest(request);

				requestList.add(request);
				System.out.println("Do you want pick any other ride from the list? [Y/N]");
				while (!scanner.hasNext("[ynYN]")) {
					System.out.println("Not a valid input");
					scanner.next();
				}
				String input = scanner.next();
				//String s = reader.readLine();
				if (input.equalsIgnoreCase("N")) {
					break;
				}
			}
		}
	}

	private void attachFareToRequest(Request request) {

		int x1 = request.getStartX();
		int x2 = request.getStartY();
		int y1 = request.getEndX();
		int y2 = request.getEndY();

		float power = (float) (Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2));
		float distance = (float) Math.sqrt(power);
		float fareCalculated = distance * 25;

		request.setFareCalculated(fareCalculated);
		System.out.println("----------------------------------------------------------------------------");
		System.out.println("Your ride fare would be $" + fareCalculated + ". Amount $150 is put in hold !!!");
		System.out.println("----------------------------------------------------------------------------");
	}

	private void displayRequestDetails(List<Request> requestList) {
		System.out.println("");
		System.out.println("========Here are your reguest details=========");
		for (Request request : requestList) {
			System.out.println("------------------------------------------");
			System.out.println("PickUp Location: " + request.getPickUpLoc());
			System.out.println("DropOff Location: " + request.getDestination());
			System.out.println("PickUp Date: " + request.getRequestDate());
			System.out.println("PickUp Time: " + request.getRequesTime());
			System.out.println("Num of people: " + request.getNumOfPeople());
			System.out.println("------------------------------------------");
		}
		System.out.println("==============================================");
		System.out.println("");
	}

	public void requestRide(List<Request> requestList) throws Exception {

		Dispatcher dispatcher = new Dispatcher();
		dispatcher.dispatchRide(requestList);

	}

	public static void trackRide() {

		try {
			Ride ride;
			String line = null;
			String fileName = "C:/EclipseWorkspace/CarpoolingSystem/src/main/resources/ride.txt";
			FileReader fileReader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			while ((line = bufferedReader.readLine()) != null) {
				ride = new Ride();
				String[] strSplit = line.split(":");
				String id = strSplit[0];
				int rId = Integer.parseInt(id);
				String source = strSplit[1];
				String dest = strSplit[2];
				String date = strSplit[3];
				ride.setRequest_id(rId);
				ride.setPickUpLoc(source);
				ride.setDestination(dest);
				ride.setRideDate(date);
				System.out.println("Id: " + ride.getRequest_id() + " PickUp: " + ride.getPickUpLoc() + " Drop Off: "
						+ ride.getDestination() + " Date: " + ride.getRideDate());
			}
			System.out.println("These are the rides available. which ride do you want to track? \n"
					+ "Please enter the request id from above list ");

			int option = Integer.parseInt(reader.readLine());
			Ride rideChose = new Ride();
			rideChose.setRequest_id(option);
			TrackContext trcctxt = new TrackContext(rideChose);
			trcctxt.showTrackStatus();

			Thread.sleep(3000);

			trcctxt.setTrackState(new TransitState());

			trcctxt.showTrackStatus();

			Thread.sleep(3000);
			bufferedReader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
