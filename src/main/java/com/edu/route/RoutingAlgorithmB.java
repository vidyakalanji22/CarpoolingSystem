package com.edu.route;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class RoutingAlgorithmB extends Routing {

	public final String name;
	public Edges[] adjacencies;
	public double minDistance = Double.POSITIVE_INFINITY;
	public RoutingAlgorithmB previous;

	public RoutingAlgorithmB(String argName) {
		name = argName;
	}

	public String toString() {
		return name;
	}

	@Override
	public void useAlgorithm(String source, String dest) {

		System.out.println("-----------Using shortest Time algorithm------------------");
		RoutingAlgorithmB rb = new RoutingAlgorithmB(source);
		String path = rb.routeOperation(source, dest);
		String[] params = path.split(" ");
		System.out.println("Distance::" + params[0]);

		System.out.println("Path:: ");
		for (int i = 1; i < params.length; i++) {
			System.out.println(params[i]);
		}

	}

	public String routeOperation(String source, String destination) {
		RoutingAlgorithmB sourceA = new RoutingAlgorithmB(source);
		RoutingAlgorithmB destinationZ = new RoutingAlgorithmB(destination);

		DijkstraAlgo d = new DijkstraAlgo();
		String str = d.calculate(sourceA, destinationZ);

		return str;
	}

}

class Edges {
	public final RoutingAlgorithmB target;
	public final double weight;

	public Edges(RoutingAlgorithmB argTarget, double argWeight) {
		target = argTarget;
		weight = argWeight;
	}
}

class DijkstraAlgo {
	public static void computePaths(RoutingAlgorithmB source) {
		source.minDistance = 0.;
		PriorityQueue<RoutingAlgorithmB> vertexQueue = new PriorityQueue<RoutingAlgorithmB>();
		vertexQueue.add(source);

		while (!vertexQueue.isEmpty()) {
			RoutingAlgorithmB u = vertexQueue.poll();

			// Visit each edge exiting u
			for (Edges e : u.adjacencies) {
				RoutingAlgorithmB v = e.target;
				double weight = e.weight;
				double distanceThroughU = u.minDistance + weight;
				if (distanceThroughU < v.minDistance) {
					vertexQueue.remove(v);

					v.minDistance = distanceThroughU;
					v.previous = u;
					vertexQueue.add(v);
				}
			}
		}
	}

	public static List<RoutingAlgorithmB> getShortestPathTo(RoutingAlgorithmB target) {
		List<RoutingAlgorithmB> path = new ArrayList<RoutingAlgorithmB>();
		for (RoutingAlgorithmB routingAlgorithmA = target; routingAlgorithmA != null; routingAlgorithmA = routingAlgorithmA.previous)
			path.add(routingAlgorithmA);

		Collections.reverse(path);
		return path;
	}

	public static String calculate(RoutingAlgorithmB source, RoutingAlgorithmB destination) {
		// mark all the vertices
		RoutingAlgorithmB SanJose = new RoutingAlgorithmB("SanJose");
		RoutingAlgorithmB Belmont = new RoutingAlgorithmB("Belmont");
		RoutingAlgorithmB Blair = new RoutingAlgorithmB("Blair");
		RoutingAlgorithmB Sunnyvale = new RoutingAlgorithmB("Sunnyvale");
		RoutingAlgorithmB Fremont = new RoutingAlgorithmB("Fremont");
		RoutingAlgorithmB Milpitas = new RoutingAlgorithmB("Milpitas");
		RoutingAlgorithmB PaloAlto = new RoutingAlgorithmB("PaloAlto");
		RoutingAlgorithmB Hayward = new RoutingAlgorithmB("Hayward");
		RoutingAlgorithmB Milbrae = new RoutingAlgorithmB("Milbrae");
		RoutingAlgorithmB SanMateo = new RoutingAlgorithmB("SanMateo");
		RoutingAlgorithmB SanFrancisco = new RoutingAlgorithmB("SanFrancisco");

		Map<RoutingAlgorithmB, String> map = new LinkedHashMap<RoutingAlgorithmB, String>();
		map.put(SanJose, "SanJose");
		map.put(Belmont, "Belmont");
		map.put(Blair, "Blair");
		map.put(Sunnyvale, "Sunnyvale");
		map.put(Fremont, "Fremont");
		map.put(Milpitas, "Milpitas");
		map.put(PaloAlto, "PaloAlto");
		map.put(Hayward, "Hayward");
		map.put(Milbrae, "Milbrae");
		map.put(SanMateo, "SanMateo");
		map.put(SanFrancisco, "SanFrancisco");

		// set the edges and weight
		SanJose.adjacencies = new Edges[] { new Edges(PaloAlto, 10) };
		Belmont.adjacencies = new Edges[] { new Edges(Blair, 13) };
		Blair.adjacencies = new Edges[] { new Edges(Belmont, 13) };
		Sunnyvale.adjacencies = new Edges[] { new Edges(Fremont, 25) };
		Fremont.adjacencies = new Edges[] { new Edges(Hayward, 42) };
		Milpitas.adjacencies = new Edges[] { new Edges(Fremont, 27) };
		PaloAlto.adjacencies = new Edges[] { new Edges(SanMateo, 10) };
		Hayward.adjacencies = new Edges[] { new Edges(Fremont, 42) };
		Milbrae.adjacencies = new Edges[] { new Edges(SanFrancisco, 20) };
		SanMateo.adjacencies = new Edges[] { new Edges(Milbrae, 17) };
		SanFrancisco.adjacencies = new Edges[] { new Edges(Milbrae, 20) };

		for (Map.Entry<RoutingAlgorithmB, String> entry : map.entrySet()) {
			if (source.toString().equals(entry.getKey().toString())) {
				source = entry.getKey();
			}
			if (destination.toString().equals(entry.getKey().toString())) {
				destination = entry.getKey();
			}
		}

		computePaths(source); // run DijkstraAlgo
		List<RoutingAlgorithmB> path = getShortestPathTo(destination);
		if (destination.minDistance == Double.POSITIVE_INFINITY){
			destination.minDistance = 30.0;
			path.clear();
			path.add(source);
			path.add(destination);
		}
		return destination.minDistance + " " + path.toString();
	}
}
