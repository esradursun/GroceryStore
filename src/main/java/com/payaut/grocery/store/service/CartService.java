package com.payaut.grocery.store.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.payaut.grocery.store.model.Bread;
import com.payaut.grocery.store.model.Cart;
import com.payaut.grocery.store.model.DiscountFee;
import com.payaut.grocery.store.model.Product;

public class CartService {
    private static final Logger logger = LoggerFactory.getLogger(CartService.class);
	
	private final DiscountCalculationService discountCalculationService;
	
	public CartService(DiscountCalculationService discountCalculationService) {
		this.discountCalculationService = discountCalculationService;
	}
	
	public Cart createCart(final List<Product> productList) {
		List<Product> newProductList = filterEligibleBreads(productList);
		List<DiscountFee> discountFee = discountCalculationService.getCalculationDiscountFee(newProductList);
		Cart cart = new Cart(newProductList, getOriginalPriceOfCart(newProductList), getTotalDiscountAmount(discountFee), discountFee);
		logger.info("Cart is created.");
		return cart;
	}
	
	private BigDecimal getOriginalPriceOfCart(List<Product> productList) {
		logger.info("Original price is calculating for the all products in the cart.");
		return productList.stream().map(Product::getPrice).reduce(BigDecimal::add).get();
	}
	
	private List<Product> filterEligibleBreads(List<Product> productList){
		logger.info("Checking whether breads are older than 6 days.");
		List<Product> newProductList = productList.stream()
				.filter(product -> product instanceof Bread)
				.filter(bread -> !(((Bread)bread).getAgeOfBread() > 6))
				.collect(Collectors.toList());
		
		newProductList.addAll(
				productList.stream()
				.filter(product -> !(product instanceof Bread))
				.collect(Collectors.toList()));
		
		return newProductList;
	}
	
	
	private BigDecimal getTotalDiscountAmount(List<DiscountFee> discountFeeList) {
		logger.info("Calculating total discount amount.");
		return discountFeeList.stream().map(DiscountFee::getDiscountedPrice).reduce(BigDecimal::add).get().setScale(2, RoundingMode.CEILING);
	}

}
