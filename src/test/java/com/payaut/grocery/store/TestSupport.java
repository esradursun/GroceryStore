package com.payaut.grocery.store;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import com.payaut.grocery.store.model.Beer;
import com.payaut.grocery.store.model.BeerType;
import com.payaut.grocery.store.model.Bread;
import com.payaut.grocery.store.model.Cart;
import com.payaut.grocery.store.model.DiscountFee;
import com.payaut.grocery.store.model.DiscountType;
import com.payaut.grocery.store.model.Product;
import com.payaut.grocery.store.model.Vegetable;

public class TestSupport{
	
	public static final Product threeDaysOldBread = new Bread(getAgeOfBread(3), "Brown Bread", new BigDecimal(5));
	public static final Product twoDaysOldBread = new Bread(getAgeOfBread(2), "White Bread", new BigDecimal(4));
	public static final Product sixDaysOldBread = new Bread(getAgeOfBread(6), "White Bread", new BigDecimal(4));
	public static final Product sevenDaysOldBread = new Bread(getAgeOfBread(7), "Banana Bread", new BigDecimal(4));

	public static final Product broccoli = new Vegetable(50.0, "Broccoli", new BigDecimal(10));
	public static final Product artichoke = new Vegetable(50.0, "Artichoke", new BigDecimal(10));
	public static final Product spinach = new Vegetable(200.0, "Spinach", new BigDecimal(20));
	public static final Product celery = new Vegetable(500.0, "Celery", new BigDecimal(30));

	public static final Product belgiumBeer = new Beer("Belgium Beer", new BigDecimal(3), BeerType.BELGIUM);
	public static final Product dutchBeer = new Beer("Dutch Beer", new BigDecimal(3), BeerType.DUTCH);
	public static final Product germanBeer = new Beer("German Beer", new BigDecimal(3), BeerType.GERMAN);

	private static final List<Product> productList = Arrays.asList(threeDaysOldBread, threeDaysOldBread, threeDaysOldBread, twoDaysOldBread,
			sixDaysOldBread, 
			broccoli, artichoke, 
			belgiumBeer, belgiumBeer, belgiumBeer, belgiumBeer, belgiumBeer,belgiumBeer, dutchBeer, germanBeer);
	
	public Cart generateCartWithEligibleDiscount() {    	
    	return new Cart(productList, 
    			new BigDecimal(63).setScale(2, RoundingMode.CEILING), 
    			new BigDecimal(15.11).setScale(2, RoundingMode.CEILING), 
    			generateDiscountFeeList());
	}
	
	public Cart generateCartWithNoEligibleDiscount() {
    	Product bread = new Bread(getAgeOfBread(3), "Brown Bread", new BigDecimal(5));
    	List<Product> breadList = Arrays.asList(bread);
    	
    	return new Cart(breadList);
	}
	
	public static LocalDate getAgeOfBread(long day) {
    	return LocalDate.now().minusDays(day);
	}
	
	public List<DiscountFee> generateDiscountFeeList() {
		DiscountFee breadDiscount = new DiscountFee(new BigDecimal(10.00).setScale(2, RoundingMode.CEILING), DiscountType.BREADDISCOUNT);
		DiscountFee vegetableDiscount = new DiscountFee(new BigDecimal(1.001).setScale(2, RoundingMode.CEILING), DiscountType.VEGETABLEDISCOUNT);
		DiscountFee beerDiscount = new DiscountFee(new BigDecimal(3.00).setScale(2, RoundingMode.CEILING), DiscountType.BEERDISCOUNT);
		
		return Arrays.asList(breadDiscount, vegetableDiscount, beerDiscount);
	}
	
	
}
