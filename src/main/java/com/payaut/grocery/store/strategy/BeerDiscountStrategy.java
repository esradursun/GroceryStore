package com.payaut.grocery.store.strategy;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

import com.payaut.grocery.store.model.Beer;
import com.payaut.grocery.store.model.BeerType;
import com.payaut.grocery.store.model.DiscountFee;
import com.payaut.grocery.store.model.DiscountType;

public class BeerDiscountStrategy implements DiscountStrategy<Beer> {
    
	private static final int BELGIUM_DISCOUNT = 3;
	private static final int DUTCH_DISCOUNT = 2;
	private static final int GERMAN_DISCOUNT = 1;
	
	private Integer belgiumBeersCount = 0;
	private Integer dutchBeersCount = 0;
	private Integer germanBeersCount = 0;

	@Override
	public Optional<DiscountFee> applyDiscount(final List<Beer> beerList) {
		
		beerList.forEach(beer -> {
			if(beer.getBeerType() == BeerType.BELGIUM)
				belgiumBeersCount++;
			else if (beer.getBeerType() == BeerType.DUTCH)
				dutchBeersCount++;
			else if (beer.getBeerType() == BeerType.GERMAN)
				germanBeersCount++;
		});
		
		BigDecimal discountedFee = BigDecimal.ZERO;
		
		if(belgiumBeersCount >= 6) {
			discountedFee = discountedFee.add(new BigDecimal((belgiumBeersCount / 6) * BELGIUM_DISCOUNT));
		} 
		
		if(dutchBeersCount >= 6) {
			discountedFee = discountedFee.add(new BigDecimal((dutchBeersCount / 6) * DUTCH_DISCOUNT));
		}
		
		if(germanBeersCount >= 6) {
			discountedFee = discountedFee.add(new BigDecimal((germanBeersCount / 6) * GERMAN_DISCOUNT));
		}
		
		return discountedFee.compareTo(BigDecimal.ZERO) > 0 ? Optional.of(new DiscountFee(discountedFee.setScale(2, RoundingMode.CEILING), DiscountType.BEERDISCOUNT)) : Optional.empty();
	}
	
	

}
