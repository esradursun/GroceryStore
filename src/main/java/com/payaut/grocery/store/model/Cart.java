package com.payaut.grocery.store.model;

import java.math.BigDecimal;
import java.util.List;

public class Cart {
	
	private List<Product> productList;
	private BigDecimal originalPrice;
	private BigDecimal totalDiscount;
	private List<DiscountFee> discountFee;
	
	public Cart(List<Product> productList, BigDecimal originalPrice, BigDecimal totalDiscount,
			List<DiscountFee> discountFee) {
		this.productList = productList;
		this.originalPrice = originalPrice;
		this.totalDiscount = totalDiscount;
		this.discountFee = discountFee;
	}
		
	public Cart(List<Product> productList) {
		super();
		this.productList = productList;
	}

	public List<Product> getProduct() {
		return productList;
	}
	public void setProduct(List<Product> productList) {
		this.productList = productList;
	}
	public BigDecimal getOriginalPrice() {
		return originalPrice;
	}
	public void setOriginalPrice(BigDecimal originalPrice) {
		this.originalPrice = originalPrice;
	}
	public BigDecimal getTotalDiscount() {
		return totalDiscount;
	}
	public void setTotalDiscount(BigDecimal totalDiscount) {
		this.totalDiscount = totalDiscount;
	}
	public List<DiscountFee> getDiscountFee() {
		return discountFee;
	}
	public void setDiscountFee(List<DiscountFee> discountFee) {
		this.discountFee = discountFee;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((discountFee == null) ? 0 : discountFee.hashCode());
		result = prime * result + ((originalPrice == null) ? 0 : originalPrice.hashCode());
		result = prime * result + ((productList == null) ? 0 : productList.hashCode());
		result = prime * result + ((totalDiscount == null) ? 0 : totalDiscount.hashCode());
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
		Cart other = (Cart) obj;
		if (discountFee == null) {
			if (other.discountFee != null)
				return false;
		} else if (!discountFee.equals(other.discountFee))
			return false;
		if (originalPrice == null) {
			if (other.originalPrice != null)
				return false;
		} else if (!originalPrice.equals(other.originalPrice))
			return false;
		if (productList == null) {
			if (other.productList != null)
				return false;
		} else if (!productList.equals(other.productList))
			return false;
		if (totalDiscount == null) {
			if (other.totalDiscount != null)
				return false;
		} else if (!totalDiscount.equals(other.totalDiscount))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Cart [\nproductList: \n{" + productList + "}, \noriginalPrice=" + originalPrice + ", \ntotalDiscount=" + totalDiscount
				+ ", \ndiscountFee:{" + discountFee + "}\n]";
	}
	
	
}
