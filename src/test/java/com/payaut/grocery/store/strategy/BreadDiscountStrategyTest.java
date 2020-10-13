package com.payaut.grocery.store.strategy;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import com.payaut.grocery.store.TestSupport;
import com.payaut.grocery.store.model.Bread;
import com.payaut.grocery.store.model.DiscountFee;
import com.payaut.grocery.store.model.DiscountType;

import junit.framework.Assert;

public class BreadDiscountStrategyTest extends TestSupport{
	
	BreadDiscountStrategy breadDiscountStrategy;
	
	@Before
	public void setup() {
		breadDiscountStrategy = new BreadDiscountStrategy();
	}

	@Test
	public void whenApplyDiscountCalledForPay1Take2BreadDiscount_itShouldReturnPay1Take2BreadDiscountFee() {
		List<Bread> breadList = Arrays.asList((Bread)threeDaysOldBread, (Bread)threeDaysOldBread, (Bread)threeDaysOldBread);
		
		DiscountFee expectedDiscountFee = new DiscountFee(new BigDecimal(10).setScale(2, RoundingMode.CEILING), DiscountType.BREADDISCOUNT);
		DiscountFee result = breadDiscountStrategy.applyDiscount(breadList).get();
		
		Assert.assertEquals(result, expectedDiscountFee);
	}

	
	@Test
	public void whenApplyDiscountCalledForPay1Take3BreadDiscount_itShouldReturnPay1Take3BreadDiscountFee() {
		List<Bread> breadList = Arrays.asList((Bread)sixDaysOldBread, (Bread)sixDaysOldBread, (Bread)sixDaysOldBread, (Bread)sixDaysOldBread);
		
		DiscountFee expectedDiscountFee = new DiscountFee(new BigDecimal(12).setScale(2, RoundingMode.CEILING), DiscountType.BREADDISCOUNT);
		DiscountFee result = breadDiscountStrategy.applyDiscount(breadList).get();
		
		Assert.assertEquals(result, expectedDiscountFee);
	}
	
	@Test
	public void whenApplyDiscountCalledForPay1Take2AndPay1Take3BreadDiscount_itShouldReturnPay1Take2AndPay1Take3BreadDiscountFee() {
		List<Bread> breadList = Arrays.asList((Bread)threeDaysOldBread, (Bread)threeDaysOldBread, (Bread)threeDaysOldBread, 
				(Bread)sixDaysOldBread, (Bread)sixDaysOldBread,(Bread)sixDaysOldBread, (Bread)sixDaysOldBread);
		
		DiscountFee expectedDiscountFee = new DiscountFee(new BigDecimal(22).setScale(2, RoundingMode.CEILING), DiscountType.BREADDISCOUNT);
		DiscountFee result = breadDiscountStrategy.applyDiscount(breadList).get();
		
		Assert.assertEquals(result, expectedDiscountFee);
	}
	
	@Test
	public void whenApplyDiscountCalledForPay1Take2AndOne6DaysOldBreadDiscount_itShouldReturnPay1Take2BreadDiscountFee() {
		List<Bread> breadList = Arrays.asList((Bread)threeDaysOldBread, (Bread)threeDaysOldBread, (Bread)threeDaysOldBread, 
				(Bread)sixDaysOldBread);
		
		DiscountFee expectedDiscountFee = new DiscountFee(new BigDecimal(10).setScale(2, RoundingMode.CEILING), DiscountType.BREADDISCOUNT);
		DiscountFee result = breadDiscountStrategy.applyDiscount(breadList).get();
		
		Assert.assertEquals(result, expectedDiscountFee);
	}
	
	@Test
	public void whenApplyDiscountCalledForPay1Take3AndOneThreeDaysOldBreadDiscount_itShouldReturnPay1Take3AndAndOneThreeDaysOldBreadDiscountFee() {
		List<Bread> breadList = Arrays.asList((Bread)threeDaysOldBread, (Bread)sixDaysOldBread, (Bread)sixDaysOldBread,
				(Bread)sixDaysOldBread, (Bread)sixDaysOldBread);
		
		DiscountFee expectedDiscountFee = new DiscountFee(new BigDecimal(12).setScale(2, RoundingMode.CEILING), DiscountType.BREADDISCOUNT);
		DiscountFee result = breadDiscountStrategy.applyDiscount(breadList).get();
		
		Assert.assertEquals(result, expectedDiscountFee);
	}
	
	@Test
	public void whenApplyDiscountCalledFor2DaysOldBreadDiscount_itShouldReturnNoEligibleBreadDiscountStrategy() {
		List<Bread> breadList = Arrays.asList((Bread)twoDaysOldBread, (Bread)twoDaysOldBread, (Bread)twoDaysOldBread);
		
		Optional<DiscountFee> result = breadDiscountStrategy.applyDiscount(breadList);
		
		Assert.assertTrue(result.isEmpty());
	}
	
	@Test
	public void whenApplyDiscountCalledForOlderThanSixDaysBreadDiscount_itShouldReturnNoEligibleBreadDiscountStrategy() {
		List<Bread> breadList = Arrays.asList((Bread)sevenDaysOldBread, (Bread)sevenDaysOldBread, (Bread)sevenDaysOldBread);
		
		Optional<DiscountFee> result = breadDiscountStrategy.applyDiscount(breadList);
		
		Assert.assertTrue(result.isEmpty());
	}
	
	@Test
	public void whenApplyDiscountCalledForPay1Take2ForOneBreadBreadDiscount_itShouldReturnNoEligibleBreadDiscountStrategy() {
		List<Bread> breadList = Arrays.asList((Bread)threeDaysOldBread);
		
		Optional<DiscountFee> result = breadDiscountStrategy.applyDiscount(breadList);
		
		Assert.assertTrue(result.isEmpty());
	}
	
	@Test
	public void whenApplyDiscountCalledForPay1Take3ForOneBreadBreadDiscount_itShouldReturnNoEligibleBreadDiscountStrategy() {
		List<Bread> breadList = Arrays.asList((Bread)sixDaysOldBread);
		
		Optional<DiscountFee> result = breadDiscountStrategy.applyDiscount(breadList);
		
		Assert.assertTrue(result.isEmpty());
	}
	
	
	
}
