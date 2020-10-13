package com.payaut.grocery.store.model;

import java.math.BigDecimal;

public class Order {
	
	Cart cart;
	BigDecimal netPrice;
	
	public Order() {}

	public Order(Cart cart, BigDecimal netPrice) {
		this.cart = cart;
		this.netPrice = netPrice;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public BigDecimal getNetPrice() {
		return netPrice;
	}

	public void setNetPrice(BigDecimal netPrice) {
		this.netPrice = netPrice;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cart == null) ? 0 : cart.hashCode());
		result = prime * result + ((netPrice == null) ? 0 : netPrice.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (cart == null) {
			if (other.cart != null)
				return false;
		} else if (!cart.equals(other.cart))
			return false;
		if (netPrice == null) {
			if (other.netPrice != null)
				return false;
		} else if (!netPrice.equals(other.netPrice))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Order [\n" + cart + ",\nnetPrice=" + netPrice + "\n]";
	}
	
}
