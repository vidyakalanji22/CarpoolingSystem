package com.edu.route;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class RoutingAlgorithmA extends Routing {

	public final String name;
	public Edge[] adjacencies;
	public double minDistance = Double.POSITIVE_INFINITY;
	public RoutingAlgorithmA previous;

	public RoutingAlgorithmA(String argName) {
		name = argName;
	}

	public String toString() {
		return name;
	}

	public String routeOperation(String source, String destination) {
		RoutingAlgorithmA sourceA = new RoutingAlgorithmA(source);
		RoutingAlgorithmA destinationZ = new RoutingAlgorithmA(destination);

		String str = Dijkstra.calculate(sourceA, destinationZ);

		return str;
	}

	@Override
	public void useAlgorithm(String source, String dest) {

		System.out.println("-----------Using shortest Distance algorithm------------------");
		RoutingAlgorithmA ra = new RoutingAlgorithmA(source);
		String path = ra.routeOperation(source, dest);
		String[] params = path.split(" ");
		System.out.println("Distance::" + params[0]);

		System.out.println("Path:: ");
		for (int i = 1; i < params.length; i++) {
			System.out.println(params[i]);
		}

	}

}

class Edge {
	public final RoutingAlgorithmA target;
	public final double weight;

	public Edge(RoutingAlgorithmA argTarget, double argWeight) {
		target = argTarget;
		weight = argWeight;
	}
}

class Dijkstra {
	public static void computePaths(RoutingAlgorithmA source) {
		source.minDistance = 0.;
		PriorityQueue<RoutingAlgorithmA> vertexQueue = new PriorityQueue<RoutingAlgorithmA>();
		vertexQueue.add(source);

		while (!vertexQueue.isEmpty()) {
			RoutingAlgorithmA u = vertexQueue.poll();

			// Visit each edge exiting u
			for (Edge e : u.adjacencies) {
				RoutingAlgorithmA v = e.target;
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

	public static List<RoutingAlgorithmA> getShortestPathTo(RoutingAlgorithmA target) {
		List<RoutingAlgorithmA> path = new ArrayList<RoutingAlgorithmA>();
		for (RoutingAlgorithmA routingAlgorithmA = target; routingAlgorithmA != null; routingAlgorithmA = routingAlgorithmA.previous)
			path.add(routingAlgorithmA);

		Collections.reverse(path);
		return path;
	}

	public static String calculate(RoutingAlgorithmA source, RoutingAlgorithmA destination) {
		// mark all the vertices
		RoutingAlgorithmA SanJose = new RoutingAlgorithmA("SanJose");
		RoutingAlgorithmA Belmont = new RoutingAlgorithmA("Belmont");
		RoutingAlgorithmA Blair = new RoutingAlgorithmA("Blair");
		RoutingAlgorithmA Sunnyvale = new RoutingAlgorithmA("Sunnyvale");
		RoutingAlgorithmA Fremont = new RoutingAlgorithmA("Fremont");
		RoutingAlgorithmA Milpitas = new RoutingAlgorithmA("Milpitas");
		RoutingAlgorithmA PaloAlto = new RoutingAlgorithmA("PaloAlto");
		RoutingAlgorithmA Hayward = new RoutingAlgorithmA("Hayward");
		RoutingAlgorithmA Milbrae = new RoutingAlgorithmA("Milbrae");
		RoutingAlgorithmA SanMateo = new RoutingAlgorithmA("SanMateo");
		RoutingAlgorithmA SanFrancisco = new RoutingAlgorithmA("SanFrancisco");

		Map<RoutingAlgorithmA, String> map = new LinkedHashMap<RoutingAlgorithmA, String>();
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
		SanJose.adjacencies = new Edge[] { new Edge(PaloAlto, 8) };
		Belmont.adjacencies = new Edge[] { new Edge(Blair, 11) };
		Blair.adjacencies = new Edge[] { new Edge(Belmont, 11) };
		Sunnyvale.adjacencies = new Edge[] { new Edge(Fremont, 23) };
		Fremont.adjacencies = new Edge[] { new Edge(Hayward, 40) };
		Milpitas.adjacencies = new Edge[] { new Edge(Fremont, 25) };
		PaloAlto.adjacencies = new Edge[] { new Edge(SanMateo, 8) };
		Hayward.adjacencies = new Edge[] { new Edge(Fremont, 40) };
		Milbrae.adjacencies = new Edge[] { new Edge(SanFrancisco, 18) };
		SanMateo.adjacencies = new Edge[] { new Edge(Milbrae, 15) };
		SanFrancisco.adjacencies = new Edge[] { new Edge(Milbrae, 18) };

		for (Map.Entry<RoutingAlgorithmA, String> entry : map.entrySet()) {
			if (source.toString().equals(entry.getKey().toString())) {
				source = entry.getKey();
			}
			if (destination.toString().equals(entry.getKey().toString())) {
				destination = entry.getKey();
			}
		}

		computePaths(source); // run Dijkstra
		List<RoutingAlgorithmA> path = getShortestPathTo(destination);
		if (destination.minDistance == Double.POSITIVE_INFINITY){
			destination.minDistance = 30.0;
			path.clear();
			path.add(source);
			path.add(destination);
		}
		return destination.minDistance + " " + path.toString();
	}
}