package com.payaut.grocery.store.strategy;

import java.util.List;
import java.util.Optional;

import com.payaut.grocery.store.model.DiscountFee;

public interface DiscountStrategy<T> {
	
	Optional<DiscountFee> applyDiscount(List<T> productList);

}
