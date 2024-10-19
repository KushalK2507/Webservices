package com.kushal.restws;

import java.util.ArrayList;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import com.kushal.restws.model.Passenger;

public class PasssengerServiceImpl implements PassengerService {

	List<Passenger> passengers = new ArrayList<>();
	int currentId = 123;

	public PasssengerServiceImpl() {
		init();
	}

	void init() {
		Passenger passenger = new Passenger();
		passenger.setId(currentId);
		passenger.setName("Kushal");
		passengers.add(passenger);
	}

	@Override
	public List<Passenger> getPassenger(int start, int size) {
		System.out.println("Start = " + start);
		System.out.println("Size = " + size);
		if (null == passengers) {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
		return passengers;
	}

	@Override
	public void addPassenger(String firstName, String lastName, String agent, HttpHeaders headers) {
		System.out.println("FirstName " + firstName);
		System.out.println("LastName = " + lastName);
		System.out.println("Agent = " + agent);

		// To get All Headers
		MultivaluedMap<String, String> allHeaders = headers.getRequestHeaders();
		Set<String> headerKeys = allHeaders.keySet();
		for (String key : headerKeys) {
			System.out.println("Keys = " + key);
			System.out.println("Headers value =" + headers.getHeaderString(key));
		}

		// To Get the Cookies
		Map<String, Cookie> cookies = headers.getCookies();
		Set<String> cookieKey = cookies.keySet();
		for (String eachcookie : cookieKey) {
			System.out.println("-------------------Cookies------------");
			System.out.println("Cookie Key " + eachcookie);
			System.out.println("Cookie value " + cookies.get(eachcookie).toString());

		}
	}

}
