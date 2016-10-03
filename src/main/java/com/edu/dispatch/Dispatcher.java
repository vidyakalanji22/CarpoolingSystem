package com.edu.dispatch;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.edu.feedback.Feedback;
import com.edu.member.Driver;
import com.edu.member.Passenger;
import com.edu.member.PremiumMember;
import com.edu.member.RegularMember;
import com.edu.parking.CompactParking;
import com.edu.parking.Garage;
import com.edu.parking.GarageParking;
import com.edu.parking.Parking;
import com.edu.parking.ParkingType;
import com.edu.parking.RegularParking;
import com.edu.parking.RoadSideParking;
import com.edu.payment.PaymentContext;
import com.edu.request.Request;
import com.edu.ride.Ride;
import com.edu.ride.RideContext;
import com.edu.ride.RideEndedState;
import com.edu.ride.RideInTransitState;
import com.edu.ride.RideScheduledState;
import com.edu.ride.RideStartedState;
import com.edu.route.Logic;
import com.edu.route.Routing;
import com.edu.route.RoutingAlgorithmA;
import com.edu.route.RoutingAlgorithmB;
import com.edu.util.DataStore;
import com.edu.vehicle.Vehicle;

public class Dispatcher {

	private static Scanner scanner = new Scanner(System.in);

	public void dispatchRide(List<Request> requestList) throws Exception {
		Passenger passenger = DataStore.getPassengerByName("Vidya");
		Logic logic = new Logic();

		Ride ride = null;
		RideContext ridecontext = null;
		Vehicle newVehicleForRide = null;
		List<Ride> carpoolRideList = new ArrayList<Ride>();

		boolean foundRideInCarPool;
		for (Request request : requestList) {
			foundRideInCarPool = false;
			Thread.sleep(2000);

			if (carpoolRideList.size() > 0) {
				System.out.println("Processing request");
				System.out.println("Checking in CarPool List for above Source and Destination for pooling option");
				System.out.println("");
				for (Ride poolRide : carpoolRideList) {
					if (checkRideExistInPool(poolRide, request)) {
						Vehicle poolVehicle = poolRide.getVehicle();
						int currVacancy = poolVehicle.getVacancy();
						System.out.println("poolVehicle.getVacancy() = " + currVacancy);
						if ((currVacancy > 0) && currVacancy >= request.getNumOfPeople()) {
							System.out.println("");
							System.out.println("Found Vacancy for " + request.getNumOfPeople() + " people");
							addPassengerToVehicle(passenger, poolVehicle, request);
							foundRideInCarPool = true;
							printVehicleDetails(poolVehicle);
							break;
						} else {
							System.out.println("");
							System.out.println(
									"Unfotunately, Couldnt find vacancy for " + request.getNumOfPeople() + " people");
							System.out.println("creating new Ride for this Source & Destination");
						}
					}
				}
				Thread.sleep(2000);
			} else {
				System.out.println(
						"Currently, no carpool option available for this ride. Creating new carpool option...");
				System.out.println("");
				Thread.sleep(2000);
			}

			if (!foundRideInCarPool) {
				System.out.println("");
				System.out.println("Finding new Vehicle, Creating Ride with provided details");
				Thread.sleep(3000);

				ride = createNewRide(request); // create Ride

				List<Vehicle> availableVehicleList = DataStore.getVehicleListByStatus("hasFreeSpace");

				List<Object> dataListForAlgo = new ArrayList<Object>();
				dataListForAlgo.add(request);
				dataListForAlgo.add(availableVehicleList);

				List<Vehicle> resultList = logic.calculateRoute(dataListForAlgo);
				for (int i = 0; i < resultList.size(); i++) {
					newVehicleForRide = (Vehicle) resultList.get(i);

					if (newVehicleForRide.getVacancy() < request.getNumOfPeople()) {
						System.out.println("Vehicle " + newVehicleForRide.getVehicleId()
								+ " has not enough vacancy, so not contacting the Driver");
						System.out.println("");
						continue;
					} else {
						System.out.println("Contacting Driver for acceptance");
						System.out.println("");
						Thread.sleep(2000);
					}
					System.out.println("");
					System.out.println("Driver " + newVehicleForRide.getVehicleId()
							+ " your distance from pick up location is " + newVehicleForRide.getDistance()
							+ " miles do you want to accept this Ride? [Y/N]");

					while (!scanner.hasNext("[ynYN]")) {
						System.out.println("Not a valid input");
						scanner.next();
					}
					String sline = scanner.next();
					if (sline.equalsIgnoreCase("Y")) {
						addPassengerToVehicle(passenger, newVehicleForRide, request);
						ride.setVehicle(newVehicleForRide);
						carpoolRideList.add(ride);

						chooseRouteTimeAlgorithm(request);

						//System.out.println(newVehicleForRide.getDriverName());

						printVehicleDetails(newVehicleForRide);

						ridecontext = new RideContext(ride);
						ridecontext.setRideState(new RideScheduledState());
						ridecontext.showRideStatus();

						// adding parking
						System.out.println("==================Driver==========================");
						System.out.println("Do you want parking for this ride?? [y/n]");
						while (!scanner.hasNext("[ynYN]")) {
							System.out.println("Not a valid input");
							scanner.next();
						}
						String inputParking = scanner.next();
						if (inputParking.equalsIgnoreCase("Y")) {
							System.out.println("");
							System.out.println("Please choose parking type from below");
							System.out.println("1.Roadside Parking");
							System.out.println("2.Garage Parking");
							int option;
							do {
								while (!scanner.hasNextInt()) {
									System.out.println("Invalid input. Accepts only numeric");
									scanner.next();
								}
								option = scanner.nextInt();
								if (option < 1 || option > 2)
									System.out.println("Invalid input. Please enter the correct choice");
							} while (option < 1 || option > 2);
							System.out.println("Please enter from time");
							float fromTime = scanner.nextFloat();
							System.out.println("Please enter to time");
							float toTime = scanner.nextFloat();
							Garage garage = DataStore.getGarageListByDestination(request.getDestination());
							Parking p1;
							ParkingType pt;
							switch (option) {
							case 1:
								if (newVehicleForRide.getVehicleSize().equalsIgnoreCase("regular")) {
									pt = new RegularParking();
									p1 = new RoadSideParking(pt);
									p1.showParkingDetails(garage.getGarageLoc(), "", request.getRequestDate(),
											fromTime,toTime, garage.getParkingCost());
								} else if (newVehicleForRide.getVehicleSize().equalsIgnoreCase("compact")) {
									pt = new CompactParking();
									p1 = new RoadSideParking(pt);
									p1.showParkingDetails(garage.getGarageLoc(), "", request.getRequestDate(),
											fromTime, toTime, garage.getParkingCost());
								}
								payForParking(garage.getParkingCost());
								break;
							case 2:
								if (newVehicleForRide.getVehicleSize().equalsIgnoreCase("regular")) {
									pt = new RegularParking();
									p1 = new GarageParking(pt);
									p1.showParkingDetails(garage.getGarageLoc(), garage.getGarageName(),
											request.getRequestDate(), fromTime, toTime,
											garage.getParkingCost());
								} else if (newVehicleForRide.getVehicleSize().equalsIgnoreCase("compact")) {
									pt = new CompactParking();
									p1 = new GarageParking(pt);
									p1.showParkingDetails(garage.getGarageLoc(), garage.getGarageName(),
											request.getRequestDate(), fromTime, toTime,
											garage.getParkingCost());
								}
								payForParking(garage.getParkingCost());
								break;
							}

						}

						break;
					} else {
						System.out.println("Checking another one");
					}
				}
			}

		}

		printCarPoolDetails(carpoolRideList);
		startRide(carpoolRideList, ridecontext, passenger);
	}

	private void payForParking(float parkingCost) {
		System.out.println("============== For Driver =================");
		System.out.println("How would you like to pay for parking? ");
		System.out.println("1. Credit card");
		System.out.println("2. Debit card");
		System.out.println("3. Paypal");
		System.out.println("Please choose an option from above");
		PaymentContext payContext = new PaymentContext();
		int option = scanner.nextInt();
		switch(option){
		case 1: payContext.makePayment("creditcard", parkingCost, "driver");
				break;
		case 2: payContext.makePayment("debitcard", parkingCost, "driver");
				break;
		case 3: payContext.makePayment("paypal", parkingCost, "driver");
				break;
		default:break;
		
		}
		System.out.println("=================================================");
	}

	public static void addRideToFile(int requestId, String pickUp, String dest, String date, float time,
			String driverName, String vehicle) throws Exception {
		File file = new File("C:/EclipseWorkspace/CarpoolingSystem/src/main/resources/ride.txt");
		FileWriter writer = new FileWriter(file, true);
		writer.append(requestId + ":" + pickUp + ":" + dest + ":" + date + ":" + time + ":" + driverName + ":" + vehicle
				+ "\n");
		writer.close();
	}

	public static void main(String[] args) throws IOException, InterruptedException {

	}

	private static void chooseRouteTimeAlgorithm(Request request) throws IOException {
		System.out
				.println("Do you want to choose \n 1. shortest route \n 2. shortest time? \n Please enter your choice");
		int option;
		do {
			while (!scanner.hasNextInt()) {
				System.out.println("Invalid input. Accepts only numeric");
				scanner.next();
			}
			option = scanner.nextInt();
			if (option < 1 || option > 2)
				System.out.println("Invalid input. Please enter the corrct choice");
		} while (option < 1 || option > 2);
		Routing rtAlgo = null;
		switch (option) {

		case 1:
			rtAlgo = new RoutingAlgorithmA(request.getPickUpLoc());
			break;
		case 2:
			rtAlgo = new RoutingAlgorithmB(request.getPickUpLoc());
			break;
		default:
			break;
		}
		rtAlgo.useAlgorithm(request.getPickUpLoc(), request.getDestination());

	}

	private static void addPassengerToVehicle(Passenger passenger, Vehicle vehicle, Request request)
			throws InterruptedException {
		for (int i = 1; i <= request.getNumOfPeople(); i++) {
			System.out.println("Adding passenger " + i + " to the Vehicle");
			Thread.sleep(2000);
			vehicle.addPassenger(passenger);
		}

	}

	private static boolean checkRideExistInPool(Ride poolRide, Request request) {
		boolean rideExistInPool = false;
		if (poolRide.getPickUpLoc().equalsIgnoreCase(request.getPickUpLoc())
				&& poolRide.getDestination().equalsIgnoreCase(request.getDestination())
				&& (poolRide.getRideDate().equalsIgnoreCase(request.getRequestDate()))// date
				&& (poolRide.getRideTime() == request.getRequesTime())) { // time{
			System.out
					.println("Found a match in CarPool List is for above Source and Destination on this date and time");
			System.out.println("");
			rideExistInPool = true;
		}
		return rideExistInPool;
	}

	private static Ride createNewRide(Request request) {
		Ride ride = new Ride();
		ride.setPickUpLoc(request.getPickUpLoc());
		ride.setDestination(request.getDestination());
		ride.setRequest_id(request.getRequestId());
		ride.setRideDate(request.getRequestDate());
		ride.setRideTime(request.getRequesTime());
		ride.setFare(request.getFareCalculated());
		return ride;
	}

	private static void printCarPoolDetails(List<Ride> carpoolRideList) throws Exception {

		System.out.println("");
		System.out.println("===============Below are the final carpool details========");
		for (Ride ride : carpoolRideList) {
			System.out.println("Ride Details");
			System.out.println("PickUp Location: " + ride.getPickUpLoc());
			System.out.println("Destination: " + ride.getDestination());
			System.out.println("Vehicle Allocated: " + ride.getVehicle().getVehicleType());
			System.out.println("Driver Name: " + ride.getVehicle().getDriverName());
			int vacancy = ride.getVehicle().getVacancy();
			int capaicty = ride.getVehicle().getCapacity();
			System.out.println("Passenger Count: " + (capaicty - vacancy));
			System.out.println("Available Vacancy: " + vacancy);
			System.out.println("========================================================");
			System.out.println("");
			addRideToFile(ride.getRequest_id(), ride.getPickUpLoc(), ride.getDestination(), ride.getRideDate(),
					ride.getRideTime(), ride.getVehicle().getDriverName(), ride.getVehicle().getVehicleType());

		}

	}

	private static void startRide(List<Ride> carpoolRideList, RideContext rideContext, Passenger passenger)
			throws InterruptedException, IOException {
			PaymentContext payContext = new PaymentContext();
			String membershipPlan = passenger.getPlan();
			for (Ride ride : carpoolRideList) {
				if (ride.getVehicle().getVacancy() == 0) {
					System.out.println("================== For Driver==========================");
					System.out.println("Do you want to start the ride? [Y/N]");
					while (!scanner.hasNext("[ynYN]")) {
						System.out.println("Not a valid input");
						scanner.next();
					}
					String in = scanner.next();
					if (in.equalsIgnoreCase("y")) {
					System.out.println("===============================Ride date==============================="
							+ ride.getRideDate());
					String driverName = ride.getVehicle().getDriverName();
					Driver driverObs = DataStore.getDriverByName(driverName);
					rideContext.setRideState(new RideStartedState());
					rideContext.showRideStatus();
					ride.addObserver(driverObs);
					ride.notifyObserver(driverName);
					ride.removeObserver(driverObs);
					ride.addObserver(passenger);
					ride.notifyObserver(passenger.getName());
					ride.removeObserver(passenger);
					Thread.sleep(3600);
					rideContext.setRideState(new RideInTransitState());
					rideContext.showRideStatus();
					rideContext.setRideState(new RideEndedState());
					rideContext.showRideStatus();
					float amount = ride.getFare();
					float discountAmount = 0;
					float creditBAckAmt = 0;
					if (membershipPlan.equalsIgnoreCase("premium")) {
						PremiumMember pm = new PremiumMember(passenger);
						pm.getDiscount(amount);
						discountAmount = pm.redeemDiscount(amount);
						creditBAckAmt = 150-discountAmount;
						payContext.makePayment(passenger.getPaymentType(), discountAmount, "passenger");
					} else {
						RegularMember rm = new RegularMember(passenger);
						rm.discounts();
						creditBAckAmt = 150 - amount;
						payContext.makePayment(passenger.getPaymentType(), amount, "passenger");
					}
					System.out.println("amount $"+creditBAckAmt+" has been credited back to your account");
					Feedback feedback = new Feedback();
					feedback.setFeedback(ride);
				}
			}

		}
	}

	private static void printVehicleDetails(Vehicle vehicle) {
		System.out.println("");
		System.out.println("==============Here are your details==============");
		System.out.println("DriverName: " + vehicle.getDriverName());
		System.out.println("License plate: " + vehicle.getLicensePlate());
		System.out.println("Vehicle color: " + vehicle.getVehicleColor());
		System.out.println("Model: " + vehicle.getModelNo());
		System.out.println("Vehicle Type: " + vehicle.getVehicleType());
		System.out.println("==================================================");
		System.out.println("");
	}

	static BufferedReader br = null;

	public static BufferedReader getReader() {
		if (br == null) {
			br = new BufferedReader(new InputStreamReader(System.in));
		}
		return br;
	}

}
