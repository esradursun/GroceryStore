package com.payaut.grocery.store.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.payaut.grocery.store.model.Beer;
import com.payaut.grocery.store.model.Bread;
import com.payaut.grocery.store.model.DiscountFee;
import com.payaut.grocery.store.model.DiscountType;
import com.payaut.grocery.store.model.Product;
import com.payaut.grocery.store.model.Vegetable;
import com.payaut.grocery.store.strategy.BeerDiscountStrategy;
import com.payaut.grocery.store.strategy.BreadDiscountStrategy;
import com.payaut.grocery.store.strategy.DiscountCart;
import com.payaut.grocery.store.strategy.VegetableDiscountStrategy;

public class DiscountCalculationService {
    private static final Logger logger = LoggerFactory.getLogger(DiscountCalculationService.class);
	
	public DiscountCalculationService() {}
	
	public List<DiscountFee> getCalculationDiscountFee(List<Product> productList) {
		List<DiscountFee> discountFeeList = new ArrayList<DiscountFee>();
		
		if(checkProductCount(productList, Bread.class)) {
			List<Bread> breadList = productList.stream().filter(product -> product instanceof Bread).map(bread -> (Bread)bread).collect(Collectors.toList());
			new DiscountCart<Bread>(new BreadDiscountStrategy()).applyDiscountStrategy(breadList).map(breadDiscount -> discountFeeList.add(breadDiscount));
            logger.info("Bread products are checked whether eligible for the Bread promotion.");
		} 
		
		if(checkProductCount(productList, Vegetable.class)) {
			List<Vegetable> vegetableList = productList.stream().filter(product -> product instanceof Vegetable).map(vegetable -> (Vegetable)vegetable).collect(Collectors.toList());
			new DiscountCart<Vegetable>(new VegetableDiscountStrategy()).applyDiscountStrategy(vegetableList).map(vegetableDiscount -> discountFeeList.add(vegetableDiscount));
			logger.info("Vegetable products are checked whether eligible for the Vegetable promotion.");
		} 
		
		if(checkProductCount(productList, Beer.class)) {
			List<Beer> beerList = productList.stream().filter(product -> product instanceof Beer).map(beer -> (Beer)beer).collect(Collectors.toList());
			new DiscountCart<Beer>(new BeerDiscountStrategy()).applyDiscountStrategy(beerList).map(beerDiscount -> discountFeeList.add(beerDiscount));
			logger.info("Beer products are checked whether eligible for the Beer promotion.");
		}
		
		if(discountFeeList.isEmpty()) {
			logger.info("There is no eligible discount");
			discountFeeList.add(new DiscountFee(BigDecimal.ZERO, DiscountType.NODISCOUNTELIGIBLE));
		}
		
		return discountFeeList;
	}
	
	private <T> boolean checkProductCount(List<Product> productList, Class<T> t) {
		return productList.stream().anyMatch(product -> product.getClass().getName().equals(t.getName()));
	}

}
