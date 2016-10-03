package com.edu.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.edu.util.DataStore;

public class Client {

	static BufferedReader br = null;

	public static void main(String[] args) {
		DataStore.main(args);
		Menu menu = new Menu();

	}

	public static BufferedReader getReader() {
		if (br == null) {
			br = new BufferedReader(new InputStreamReader(System.in));
		}
		return br;
	}

}
