package com.payaut.grocery.store.service;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.payaut.grocery.store.TestSupport;
import com.payaut.grocery.store.model.Cart;
import com.payaut.grocery.store.model.DiscountFee;
import com.payaut.grocery.store.model.Product;


public class CartServiceTest extends TestSupport {

	private DiscountCalculationService discountCalculationService;
	private CartService cartService;
	
	
	@Before
	public void setup() {
		discountCalculationService = Mockito.mock(DiscountCalculationService.class);
		cartService = new CartService(discountCalculationService);
	}
	
	@Test
	public void whenCreateCartCalled_itShouldReturnCartObject() {
		List<Product> requestProductList = Arrays.asList(threeDaysOldBread, threeDaysOldBread, threeDaysOldBread, twoDaysOldBread,
				sixDaysOldBread, sevenDaysOldBread,
				broccoli, artichoke, 
				belgiumBeer, belgiumBeer, belgiumBeer, belgiumBeer, belgiumBeer,belgiumBeer, dutchBeer, germanBeer);
		
		
		List<Product> filteredProductList = Arrays.asList(threeDaysOldBread, threeDaysOldBread, threeDaysOldBread, twoDaysOldBread,
				sixDaysOldBread, 
				broccoli, artichoke, 
				belgiumBeer, belgiumBeer, belgiumBeer, belgiumBeer, belgiumBeer,belgiumBeer, dutchBeer, germanBeer);
		List<DiscountFee> discountFeeList = generateDiscountFeeList();
		
		Cart expectedCart = new Cart(filteredProductList, new BigDecimal(67), new BigDecimal(14.01).setScale(2, RoundingMode.CEILING), discountFeeList);
		
		Mockito.when(discountCalculationService.getCalculationDiscountFee(filteredProductList)).thenReturn(discountFeeList);
		
		Cart result = cartService.createCart(requestProductList);
		Mockito.verify(discountCalculationService).getCalculationDiscountFee(filteredProductList);
		assertEquals(result, expectedCart);
		
		
	}
}
