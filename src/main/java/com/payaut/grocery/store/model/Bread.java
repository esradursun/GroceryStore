package com.payaut.grocery.store.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Bread extends Product {
	
	private LocalDate productionDate;

	public Bread(LocalDate productionDate, String name, BigDecimal price) {
		super(name, price);
		this.productionDate = productionDate;
	}

	public LocalDate getProductionDate() {
		return productionDate;
	}

	public void setProductionDate(LocalDate productionDate) {
		this.productionDate = productionDate;
	}

	@Override
	public String toString() {
		return "Bread [ name = " + getName() + ", price = " + getPrice() + ", productionDate = " + productionDate + "]\n";
	}
	
	public long getAgeOfBread() {
		return productionDate.until(LocalDate.now(), ChronoUnit.DAYS);
	}

}
