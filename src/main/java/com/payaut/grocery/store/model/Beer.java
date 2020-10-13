package com.payaut.grocery.store.model;

import java.math.BigDecimal;

public class Beer extends Product{

	private BeerType beerType;
	
	public Beer(String name, BigDecimal price, BeerType beerType) {
		super(name, price);
		this.beerType = beerType;
	}

	public static Beer createBeer(String name, BigDecimal price, BeerType beerType) {
		return new Beer(name, price, beerType);
	}

	public BeerType getBeerType() {
		return beerType;
	}

	public void setBeerType(BeerType beerType) {
		this.beerType = beerType;
	}

	@Override
	public String toString() {
		return "Beer [ name = " + getName() + ", price = " + getPrice() + ", beerType = " + beerType + "]\n";
	}
	

}
