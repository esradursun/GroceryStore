package com.payaut.grocery.store.service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.payaut.grocery.store.TestSupport;
import com.payaut.grocery.store.model.DiscountFee;
import com.payaut.grocery.store.model.DiscountType;
import com.payaut.grocery.store.model.Product;

public class DiscountCalculationServiceTest extends TestSupport {
	
	private DiscountCalculationService discountCalculationService = new DiscountCalculationService();
	
	@Test
	public void whenGetCalculationDiscountFeeCalledByEligibleDiscountProductList_itShouldReturnDiscountFeeList() {
		List<Product> requestedProductList = Arrays.asList(threeDaysOldBread, threeDaysOldBread, threeDaysOldBread, twoDaysOldBread,
				sixDaysOldBread, 
				broccoli, artichoke, 
				belgiumBeer, belgiumBeer, belgiumBeer, belgiumBeer, belgiumBeer,belgiumBeer, dutchBeer, germanBeer);
		
		List<DiscountFee> expectedDiscountList = generateDiscountFeeList();
		
		List<DiscountFee> result = discountCalculationService.getCalculationDiscountFee(requestedProductList);
		
		Assert.assertEquals(result, expectedDiscountList);
	}
	
	@Test
	public void whenGetCalculationDiscountFeeCalledByNotEligibleDiscountProductList_itShouldReturnNoDiscountEligible() {
		List<Product> requestedProductList = Arrays.asList(threeDaysOldBread);
		List<DiscountFee> expectedDiscountList = Arrays.asList(new DiscountFee(BigDecimal.ZERO, DiscountType.NODISCOUNTELIGIBLE));
		
		List<DiscountFee> result = discountCalculationService.getCalculationDiscountFee(requestedProductList);
		
		Assert.assertEquals(result, expectedDiscountList);
	}

}
