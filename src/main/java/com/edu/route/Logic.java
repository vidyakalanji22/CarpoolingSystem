package com.edu.route;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.edu.request.*;
import com.edu.vehicle.Vehicle;

public class Logic {

	public ArrayList<Vehicle> calculateRoute(List<Object> a) {

		ArrayList<Vehicle> vehicleInfoFinal = new ArrayList<Vehicle>();

		Request requestObj = (Request) a.get(0);
		ArrayList<Vehicle> vehicleInfo = (ArrayList) a.get(1);

		int r1 = requestObj.getStartX(); // pick up location latitude
		int r2 = requestObj.getStartY(); // pick up location longitude

		for (int i = 0; i < vehicleInfo.size(); i++) {

			Vehicle location = (Vehicle) vehicleInfo.get(i);
			location.setCurrentLatitude(new Random().nextInt(10));
			location.setCurrentLongitude(new Random().nextInt(15));
			int d1 = location.getCurrentLatitude(); // vehicle latitude
			int d2 = location.getCurrentLongitude(); // vehicle longitude
			int distance = (int) Math.sqrt(((d1 - r1) * (d1 - r1)) + ((d2 - r2) * (d2 - r2))); // distance
																								// formula
			location.setDistance(distance);
		}

		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < vehicleInfo.size(); j++) {
				Vehicle location = (Vehicle) vehicleInfo.get(j);
				int d1 = location.getDistance();

				if (d1 >= i && d1 < (i + 1))
					vehicleInfoFinal.add(location);

			}
		}
		return vehicleInfoFinal;
	}

}
