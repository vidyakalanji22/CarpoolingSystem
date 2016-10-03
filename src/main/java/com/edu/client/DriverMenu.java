package com.edu.client;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import com.edu.member.Driver;

public class DriverMenu {
	private static BufferedReader bufferedReader;

	public DriverMenu() {
		bufferedReader = Client.getReader();
	}

	public Driver register() {
		Driver driver = new Driver();
		try {
			System.out.println();
			System.out.println("___________________________________________________________________");
			System.out.println("Driver registration form");
			System.out.println("___________________________________________________________________");
			System.out.println("Please enter details: ");

			System.out.println("Name: ");
			driver.setName(bufferedReader.readLine());

			System.out.println("Phone No: ");
			driver.setContactNo(bufferedReader.readLine());

			System.out.println("Email: ");
			driver.setEmail(bufferedReader.readLine());

			System.out.println("Driver License Number: ");
			driver.setLicenceNo(bufferedReader.readLine());

			System.out.println("Do you want to Submit registration? [y:n]: ");
			if (bufferedReader.readLine().equalsIgnoreCase("y")) {
				this.addDriverToFile(driver.getName(), driver.getContactNo(), driver.getEmail(), driver.getLicenceNo());
				System.out.println("You are successfully registered.");
			} else {
				System.out.println("Successfully Cancelled.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void login() {
		try {
			System.out.println("___________________________________________________________________");
			System.out.println("Driver Login Details: ");
			System.out.println("Please Enter your Username: ");
			String userName = bufferedReader.readLine();
			System.out.println(userName);
			String line = null;
			int count = 0;
			String fileName = "C:/EclipseWorkspace/CarpoolingSystem/src/main/resources/driver.txt";
			FileReader fileReader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			while ((line = bufferedReader.readLine()) != null) {
				if (line.contains(userName))
					count++;
			}
			if (count > 0)
				System.out.println("You are successfully logged into the system.");
			else
				System.out.println("You are not a registered Driver.");
			bufferedReader.close();
		} catch (Exception e) {
		}
	}

	public void viewRides() {
		System.out.println("===================Rides===================");
		try {
			String line = null;
			String fileName = "C:/EclipseWorkspace/CarpoolingSystem/src/main/resources/ride.txt";
			FileReader fileReader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			while ((line = bufferedReader.readLine()) != null) {
				System.out.println(line);
			}
			bufferedReader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void addDriverToFile(String name, String contactNum, String email, String licenseNum) throws Exception {
		File file = new File("C:/EclipseWorkspace/CarpoolingSystem/src/main/resources/driver.txt");
		FileWriter writer = new FileWriter(file, true);
		writer.append(name + ":" + contactNum + ":" + email + ":" + licenseNum + ":5:6:available" + "\n");
		writer.close();
	}

}
