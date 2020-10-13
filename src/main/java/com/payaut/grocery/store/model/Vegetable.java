package com.payaut.grocery.store.model;

import java.math.BigDecimal;

public class Vegetable extends Product{

	private Double weight;
	
	public Vegetable() {}

	public Vegetable(Double weight, String name, BigDecimal price) {
		super(name, price);
		this.weight = weight;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	@Override
	public String toString() {
		return "Vegetable [ name = " + getName() + ", price = " + getPrice() + ", weight = " + weight + "]\n";

	}
	
}
