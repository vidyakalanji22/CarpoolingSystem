package com.edu.client;

import java.util.HashMap;

public class LocationMappingSource {

	private static LocationMappingSource locationsObject;
	private HashMap<String, Location> locations = new HashMap<String, Location>();

	private LocationMappingSource() {
		getLocations().put("SanJose", new Location(2, 3));
		getLocations().put("PaloAlto", new Location(3, 5));
		getLocations().put("Sunnyvale", new Location(1, 2));
		getLocations().put("Milpitas", new Location(1, 2));
	}

	public static LocationMappingSource getLocationObject() {
		if (locationsObject == null) {
			locationsObject = new LocationMappingSource();
		}
		return locationsObject;
	}

	public HashMap<String, Location> getLocations() {
		return locations;
	}

}
