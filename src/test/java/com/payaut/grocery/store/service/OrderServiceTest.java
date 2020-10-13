package com.payaut.grocery.store.service;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.Test;

import com.payaut.grocery.store.TestSupport;
import com.payaut.grocery.store.model.Cart;
import com.payaut.grocery.store.model.Order;

import junit.framework.Assert;

public class OrderServiceTest extends TestSupport {
	
	private OrderService orderService = new OrderService();
	
	@Test
	public void whenCreateOrderCalled_itshouldReturnCreatedOrder() {
		Cart cart = generateCartWithEligibleDiscount();
		Order expectedOrder = new Order(cart, calculateNetPrice(cart.getOriginalPrice(), cart.getTotalDiscount()));
		Order result = orderService.createOrder(cart);
		
		Assert.assertEquals(result, expectedOrder);
	}
	
	private BigDecimal calculateNetPrice(BigDecimal originalPrice, BigDecimal totalDiscount) {
		return originalPrice.subtract(totalDiscount).setScale(2, RoundingMode.CEILING);
	}

}
