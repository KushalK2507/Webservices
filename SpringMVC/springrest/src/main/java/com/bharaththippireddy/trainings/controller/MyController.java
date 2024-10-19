package com.bharaththippireddy.trainings.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/greeting")
public class MyController {

	@RequestMapping(value = "/{name}", method = RequestMethod.GET)
	public String sayHello(@PathVariable String name) {
		return "Hello" + name;
	}

	@RequestMapping(value = "/stockQuote/{firmName}", method = RequestMethod.GET)
	public StockQuoteResponse getStockQuote(@PathVariable String firmName) {
		StockQuoteResponse response = new StockQuoteResponse();
		response.setFirmName(firmName);
		response.setStockValue(10000);

		return response;
	}

	@RequestMapping(value = "/myName")
	public String myName() {
		return "Kushal";
	}
}
