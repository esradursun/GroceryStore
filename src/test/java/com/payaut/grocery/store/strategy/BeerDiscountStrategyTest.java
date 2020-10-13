package com.payaut.grocery.store.strategy;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import com.payaut.grocery.store.TestSupport;
import com.payaut.grocery.store.model.Beer;
import com.payaut.grocery.store.model.DiscountFee;
import com.payaut.grocery.store.model.DiscountType;

import junit.framework.Assert;

public class BeerDiscountStrategyTest extends TestSupport{
	
	private BeerDiscountStrategy beerDiscountStrategy;
	
	@Before
	public void setup() {
		beerDiscountStrategy = new BeerDiscountStrategy();
	}

	@Test
	public void whenApplyDiscountCalledForEligibleBelgiumBeerDiscount_itShouldReturnBelgiumBeerDiscountFee() {
		List<Beer> beerList = Arrays.asList((Beer)belgiumBeer, (Beer)belgiumBeer, (Beer)belgiumBeer, 
				(Beer)belgiumBeer, (Beer)belgiumBeer, (Beer)belgiumBeer);
		
		DiscountFee expectedDiscountFee = new DiscountFee(new BigDecimal(3).setScale(2, RoundingMode.CEILING), DiscountType.BEERDISCOUNT);
		DiscountFee result = beerDiscountStrategy.applyDiscount(beerList).get();
		
		Assert.assertEquals(result, expectedDiscountFee);
	}
	
	@Test
	public void whenApplyDiscountCalledForEligibleSixPackOfBelgiumBeerAndNoEligibileBelgiumBeer_itShouldReturnPackedOfBelgiumBeerDiscountFee() {
		List<Beer> beerList = Arrays.asList((Beer)belgiumBeer, (Beer)belgiumBeer, (Beer)belgiumBeer, 
				(Beer)belgiumBeer, (Beer)belgiumBeer, (Beer)belgiumBeer, (Beer)belgiumBeer, (Beer)belgiumBeer);
		
		DiscountFee expectedDiscountFee = new DiscountFee(new BigDecimal(3).setScale(2, RoundingMode.CEILING), DiscountType.BEERDISCOUNT);
		DiscountFee result = beerDiscountStrategy.applyDiscount(beerList).get();
		
		Assert.assertEquals(result, expectedDiscountFee);
	}
	
	@Test
	public void whenApplyDiscountCalledForEligibleDutchBeerDiscount_itShouldReturnDutchBeerDiscountFee() {
		List<Beer> beerList = Arrays.asList((Beer)dutchBeer, (Beer)dutchBeer, (Beer)dutchBeer, 
				(Beer)dutchBeer, (Beer)dutchBeer, (Beer)dutchBeer);
		
		DiscountFee expectedDiscountFee = new DiscountFee(new BigDecimal(2).setScale(2, RoundingMode.CEILING), DiscountType.BEERDISCOUNT);
		DiscountFee result = beerDiscountStrategy.applyDiscount(beerList).get();
		
		Assert.assertEquals(result, expectedDiscountFee);
	}
	
	@Test
	public void whenApplyDiscountCalledForEligibleGermanBeerDiscount_itShouldReturnGermanBeerDiscountFee() {
		List<Beer> beerList = Arrays.asList((Beer)germanBeer, (Beer)germanBeer, (Beer)germanBeer, 
				(Beer)germanBeer, (Beer)germanBeer, (Beer)germanBeer);
		
		DiscountFee expectedDiscountFee = new DiscountFee(new BigDecimal(1).setScale(2, RoundingMode.CEILING), DiscountType.BEERDISCOUNT);
		DiscountFee result = beerDiscountStrategy.applyDiscount(beerList).get();
		
		Assert.assertEquals(result, expectedDiscountFee);
	}
	
	@Test
	public void whenApplyDiscountCalledForAllEligibleBeerType_itShouldReturnAllBeersDiscountFee() {		
		List<Beer> beerList = Arrays.asList((Beer)belgiumBeer, (Beer)belgiumBeer, (Beer)belgiumBeer, (Beer)belgiumBeer, (Beer)belgiumBeer,
				(Beer)belgiumBeer,
				(Beer)dutchBeer, (Beer)dutchBeer, (Beer)dutchBeer, (Beer)dutchBeer, (Beer)dutchBeer, (Beer)dutchBeer,
				(Beer)germanBeer, (Beer)germanBeer, (Beer)germanBeer, (Beer)germanBeer, (Beer)germanBeer, (Beer)germanBeer);
		
		DiscountFee expectedDiscountFee = new DiscountFee(new BigDecimal(6).setScale(2, RoundingMode.CEILING), DiscountType.BEERDISCOUNT);
		DiscountFee result = beerDiscountStrategy.applyDiscount(beerList).get();
		
		Assert.assertEquals(result, expectedDiscountFee);
	}
	
	@Test
	public void whenApplyDiscountCalledForNoEligibleBeerDiscount_itShouldReturnNoEligibleBeerDiscountStrategy() {		
		List<Beer> beerList = Arrays.asList((Beer)belgiumBeer, (Beer)belgiumBeer, (Beer)dutchBeer, (Beer)dutchBeer, 
				(Beer)germanBeer, (Beer)germanBeer);
		
		Optional<DiscountFee> result = beerDiscountStrategy.applyDiscount(beerList);
		
		Assert.assertTrue(result.isEmpty());
	}
	
}
