package com.edu.client;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import com.edu.member.*;

public class GarageOwnerMenu {
	private BufferedReader bufferedReader;

	public GarageOwnerMenu() {
		bufferedReader = Client.getReader();
	}

	public GarageOwner register() {
		GarageOwner gOwner = new GarageOwner();
		try {
			System.out.println();
			System.out.println("===================================================================");
			System.out.println("Garage Owner registration form");
			System.out.println("===================================================================");
			System.out.println("Please enter details: ");

			System.out.println("Name: ");
			gOwner.setName(bufferedReader.readLine());

			System.out.println("Contact Number: ");
			gOwner.setContactNo(bufferedReader.readLine());

			System.out.println("Email: ");
			gOwner.setEmail(bufferedReader.readLine());

			System.out.println("Garage ID: ");
			gOwner.setGarageId(bufferedReader.readLine());

			System.out.println("Garage Name: ");
			gOwner.setGarageName(bufferedReader.readLine());

			System.out.println("Garage Location: ");
			gOwner.setGarageLocation(bufferedReader.readLine());

			System.out.println("Do you want to Submit registration? [y:n]: ");
			if (bufferedReader.readLine().equalsIgnoreCase("y")) {
				this.addGOwnerToFile(gOwner.getName(), gOwner.getContactNo(), gOwner.getEmail(), gOwner.getGarageId(),
						gOwner.getGarageName(), gOwner.getGarageLocation());

				System.out.println("You are successfully registered.");
			} else {
				System.out.println("Successfully Cancelled.");
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	public void login() {
		try {
			System.out.println("___________________________________________________________________");
			System.out.println("Garage Owner Login Details: ");
			System.out.println("Please Enter your Username: ");
			String userName = bufferedReader.readLine();
			System.out.println(userName);
			String line = null;
			int count = 0;
			String fileName = "C:/EclipseWorkspace/CarpoolingSystem/src/main/resources/garageowner.txt";
			FileReader fileReader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			while ((line = bufferedReader.readLine()) != null) {
				if (line.contains(userName))
					count++;
			}
			if (count > 0)
				System.out.println("You are successfully logged into the system.");
			else
				System.out.println("You are not a registered Garage Owner.");
			bufferedReader.close();
		} catch (Exception e) {
		}

	}

	public void addGOwnerToFile(String name, String contactNum, String email, String garageId, String garageName,
			String garageLocation) throws Exception {
		File file = new File("C:/EclipseWorkspace/CarpoolingSystem/src/main/resources/garageowner.txt");
		FileWriter writer = new FileWriter(file, true);
		writer.append(name + ":" + contactNum + ":" + email + ":" + garageId + ":" + garageName + ":" + garageLocation
				+ "\n");
		writer.close();
	}

}
