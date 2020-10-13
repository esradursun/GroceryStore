package com.payaut.grocery.store;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import com.payaut.grocery.store.model.Beer;
import com.payaut.grocery.store.model.BeerType;
import com.payaut.grocery.store.model.Bread;
import com.payaut.grocery.store.model.Cart;
import com.payaut.grocery.store.model.Order;
import com.payaut.grocery.store.model.Product;
import com.payaut.grocery.store.model.Vegetable;
import com.payaut.grocery.store.service.CartService;
import com.payaut.grocery.store.service.DiscountCalculationService;
import com.payaut.grocery.store.service.OrderService;

public class App {
	private static final OrderService orderService = new OrderService();
	private static final CartService cartService = new CartService(new DiscountCalculationService());

	private static final Product threeDaysOldBread = new Bread(getAgeOfBread(3), "Brown Bread", new BigDecimal(5));
	private static final Product twoDaysOldBread = new Bread(getAgeOfBread(2), "White Bread", new BigDecimal(4));
	private static final Product sixDaysOldBread = new Bread(getAgeOfBread(6), "White Bread", new BigDecimal(4));
	private static final Product sevenDaysOldBread = new Bread(getAgeOfBread(7), "Banana Bread", new BigDecimal(4));

	private static final Product broccoli = new Vegetable(50.0, "Broccoli", new BigDecimal(10));
	private static final Product artichoke = new Vegetable(50.0, "Artichoke", new BigDecimal(10));
	private static final Product celery = new Vegetable(500.0, "Celery", new BigDecimal(30));

	private static final Product belgiumBeer = new Beer("Belgium Beer", new BigDecimal(3), BeerType.BELGIUM);
	private static final Product dutchBeer = new Beer("Dutch Beer", new BigDecimal(3), BeerType.DUTCH);
	private static final Product germanBeer = new Beer("German Beer", new BigDecimal(3), BeerType.GERMAN);
	
	public static void main(String[] args) throws Exception {
		
		/*--------ORDER 1--------*/
		List<Product> productList = Arrays.asList(threeDaysOldBread, threeDaysOldBread, threeDaysOldBread, twoDaysOldBread,
				sixDaysOldBread, 
				broccoli, artichoke, 
				belgiumBeer, belgiumBeer, belgiumBeer, belgiumBeer, belgiumBeer,belgiumBeer, dutchBeer, germanBeer);
		
		final Cart cart = cartService.createCart(productList);
		final Order order = orderService.createOrder(cart);
		System.out.println("Order 1 is created by following informations = \n" + order);

		/*--------ORDER 2--------*/
		List<Product> productList2 = Arrays.asList(twoDaysOldBread, threeDaysOldBread, threeDaysOldBread, sixDaysOldBread,
				sixDaysOldBread, sixDaysOldBread, sixDaysOldBread, sevenDaysOldBread,
				celery,
				belgiumBeer, belgiumBeer, belgiumBeer, belgiumBeer, belgiumBeer,
				belgiumBeer, dutchBeer, dutchBeer, dutchBeer, dutchBeer, dutchBeer, dutchBeer, dutchBeer, germanBeer,
				germanBeer, germanBeer, germanBeer, germanBeer, germanBeer);
		
		final Cart cart2 = cartService.createCart(productList2);
		final Order order2 = orderService.createOrder(cart2);
		System.out.println("Order 2 is created by following informations = \n" + order2);
		
		/*--------ORDER 3--------*/
		List<Product> productList3 = Arrays.asList(twoDaysOldBread, sevenDaysOldBread, 
				belgiumBeer, belgiumBeer, dutchBeer, dutchBeer, germanBeer);
		
		final Cart cart3 = cartService.createCart(productList3);
		final Order order3 = orderService.createOrder(cart3);
		System.out.println("Order 3 is created by following informations = \n" + order3);

	}

	private static LocalDate getAgeOfBread(long day) {
		return LocalDate.now().minusDays(day);
	}
	
}
