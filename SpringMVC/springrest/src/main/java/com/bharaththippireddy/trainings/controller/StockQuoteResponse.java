package com.bharaththippireddy.trainings.controller;

public class StockQuoteResponse {
	private String firmName;
	private int stockValue;

	public String getFirmName() {
		return firmName;
	}

	public void setFirmName(String firmName) {
		this.firmName = firmName;
	}

	public int getStockValue() {
		return stockValue;
	}

	public void setStockValue(int stockValue) {
		this.stockValue = stockValue;
	}

}
