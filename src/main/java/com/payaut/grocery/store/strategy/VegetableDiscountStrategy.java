package com.payaut.grocery.store.strategy;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

import com.payaut.grocery.store.model.DiscountFee;
import com.payaut.grocery.store.model.DiscountType;
import com.payaut.grocery.store.model.Vegetable;

public class VegetableDiscountStrategy implements DiscountStrategy<Vegetable> {
	
	private static final double LESS_100G_DISCOUNT = 0.05;
	private static final double BETWEEN_100G_500G_DISCOUNT = 0.07;
	private static final double MORE_500G_DISCOUNT = 0.1;

	@Override
	public Optional<DiscountFee> applyDiscount(final List<Vegetable> vegetableList) {

		Double totalWeight = vegetableList.stream()
				.mapToDouble(Vegetable::getWeight)
				.sum();
		
		BigDecimal totalPrice = vegetableList.stream()
				.map(Vegetable::getPrice)
				.reduce(BigDecimal::add)
				.get();
		
		BigDecimal discountedFee = BigDecimal.ZERO;
		
		if(totalWeight > 0 && totalWeight <= 100) {
			discountedFee = totalPrice.multiply(new BigDecimal(LESS_100G_DISCOUNT));
		} else if (totalWeight > 100 && totalWeight <= 500) {
			discountedFee = totalPrice.multiply(new BigDecimal(BETWEEN_100G_500G_DISCOUNT));
		} else if (totalWeight > 500){
			discountedFee = totalPrice.multiply(new BigDecimal(MORE_500G_DISCOUNT));
		}
		
		return discountedFee.compareTo(BigDecimal.ZERO) > 0 ? 
				Optional.of(new DiscountFee(discountedFee.setScale(2, RoundingMode.CEILING), DiscountType.VEGETABLEDISCOUNT)) : 
				Optional.empty();
	}

}
