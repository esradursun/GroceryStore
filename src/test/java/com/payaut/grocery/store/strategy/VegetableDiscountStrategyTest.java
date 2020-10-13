package com.payaut.grocery.store.strategy;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.payaut.grocery.store.TestSupport;
import com.payaut.grocery.store.model.DiscountFee;
import com.payaut.grocery.store.model.DiscountType;
import com.payaut.grocery.store.model.Vegetable;

import junit.framework.Assert;

public class VegetableDiscountStrategyTest extends TestSupport{
	
	private VegetableDiscountStrategy vegetableDiscountStrategy;
	
	@Before
	public void setup() {
		vegetableDiscountStrategy = new VegetableDiscountStrategy();
	}

	@Test
	public void whenApplyDiscountCalledForFivePercentageVegetableDiscount_itShouldReturnFivePercentageVegetableDiscountFee() {
		List<Vegetable> vegetableList = Arrays.asList((Vegetable)broccoli, (Vegetable)artichoke);
		
		DiscountFee expectedDiscountFee = new DiscountFee(new BigDecimal(1.001).setScale(2, RoundingMode.CEILING), DiscountType.VEGETABLEDISCOUNT);
		DiscountFee result = vegetableDiscountStrategy.applyDiscount(vegetableList).get();
		
		Assert.assertEquals(result, expectedDiscountFee);
	}
	
	@Test
	public void whenApplyDiscountCalledForSevenPercentageVegetableDiscount_itShouldReturnSevenPercentageVegetableDiscountFee() {		
		List<Vegetable> vegetableList = Arrays.asList((Vegetable)spinach, (Vegetable)broccoli);
		
		DiscountFee expectedDiscountFee = new DiscountFee(new BigDecimal(2.11).setScale(2, RoundingMode.CEILING), DiscountType.VEGETABLEDISCOUNT);
		DiscountFee result = vegetableDiscountStrategy.applyDiscount(vegetableList).get();
		
		Assert.assertEquals(result, expectedDiscountFee);
	}
	
	@Test
	public void whenApplyDiscountCalledForTenPercentageVegetableDiscount_itShouldReturnTenPercentageVegetableDiscountFee() {
		List<Vegetable> vegetableList = Arrays.asList((Vegetable)celery, (Vegetable)broccoli);
		
		DiscountFee expectedDiscountFee = new DiscountFee(new BigDecimal(4.01).setScale(2, RoundingMode.CEILING), DiscountType.VEGETABLEDISCOUNT);
		DiscountFee result = vegetableDiscountStrategy.applyDiscount(vegetableList).get();
		
		Assert.assertEquals(result, expectedDiscountFee);
	}

}
