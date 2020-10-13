package com.payaut.grocery.store.strategy;

import java.util.List;
import java.util.Optional;

import com.payaut.grocery.store.model.DiscountFee;

public class DiscountCart<T> {
	
	private DiscountStrategy<T> discountStrategy;
	
	public DiscountCart(DiscountStrategy<T> discountStrategy) {
		this.discountStrategy = discountStrategy;
	}

	public Optional<DiscountFee> applyDiscountStrategy(List<T> productList) {
		return discountStrategy.applyDiscount(productList);
	}

}
