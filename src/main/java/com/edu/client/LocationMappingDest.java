package com.edu.client;

import java.util.HashMap;

public class LocationMappingDest {

	private static LocationMappingDest locationsObject;
	private HashMap<String, Location> locations = new HashMap<String, Location>();

	private LocationMappingDest() {
		getLocations().put("PaloAlto", new Location(4, 6));
		getLocations().put("SanMateo", new Location(1, 5));
		getLocations().put("Milbrae", new Location(5, 2));
		getLocations().put("SanFrancisco", new Location(6, 8));
	}

	public static LocationMappingDest getLocationObject() {
		if (locationsObject == null) {
			locationsObject = new LocationMappingDest();
		}
		return locationsObject;
	}

	public HashMap<String, Location> getLocations() {
		return locations;
	}

}
