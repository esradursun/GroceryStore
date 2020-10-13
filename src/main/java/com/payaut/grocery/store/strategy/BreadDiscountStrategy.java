package com.payaut.grocery.store.strategy;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.payaut.grocery.store.model.Bread;
import com.payaut.grocery.store.model.DiscountFee;
import com.payaut.grocery.store.model.DiscountType;

public class BreadDiscountStrategy implements DiscountStrategy<Bread> {
	
	private static final Integer ThreeDaysAgedBread = 3;
	private static final Integer FiveDaysAgedBread = 5;
	private static final Integer SixDaysAgedBread = 6;

	@Override
	public Optional<DiscountFee> applyDiscount(final List<Bread> breadList) {
		List<Bread> takeTwoBreads = breadList.stream()
				.filter(bread -> bread.getAgeOfBread() >= ThreeDaysAgedBread && bread.getAgeOfBread() <= FiveDaysAgedBread)
				.collect(Collectors.toList());
		
		List<Bread> takeThreeBreads = breadList.stream()
				.filter(bread -> bread.getAgeOfBread() == SixDaysAgedBread)
				.collect(Collectors.toList());
		
		BigDecimal discountedFee = BigDecimal.ZERO;
		
		for(int i = 0; i < takeTwoBreads.size(); i = i + 3) {
			if(takeTwoBreads.size() >= i + 3) {
				discountedFee = discountedFee.add(takeTwoBreads.get(i+1).getPrice().add(takeTwoBreads.get(i+2).getPrice()));
			}
		}
		
		for(int i = 0; i < takeThreeBreads.size(); i = i + 4) {
			if(takeThreeBreads.size() >= i + 4) {
				discountedFee = discountedFee.add(takeThreeBreads.get(i+1).getPrice()
						.add(takeThreeBreads.get(i+2).getPrice())
						.add(takeThreeBreads.get(i+3).getPrice()));
			}
		}
		
		return discountedFee.compareTo(BigDecimal.ZERO) > 0 ? Optional.of(new DiscountFee(discountedFee.setScale(2, RoundingMode.CEILING), DiscountType.BREADDISCOUNT)) : Optional.empty();
	}
	
	

}
