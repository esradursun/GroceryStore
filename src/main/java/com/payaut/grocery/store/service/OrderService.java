package com.payaut.grocery.store.service;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.payaut.grocery.store.model.Cart;
import com.payaut.grocery.store.model.Order;

public class OrderService {
    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

	public OrderService() {}
	
	public Order createOrder(Cart cart) {
		logger.info("Order is creating with the cart and by calculating the net price.");
		return new Order(cart, calculateNetPrice(cart.getOriginalPrice(), cart.getTotalDiscount()));
	}
	
	private BigDecimal calculateNetPrice(BigDecimal originalPrice, BigDecimal totalDiscount) {
		return originalPrice.subtract(totalDiscount).setScale(2, RoundingMode.CEILING);
	}

}
