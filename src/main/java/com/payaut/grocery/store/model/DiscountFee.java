package com.payaut.grocery.store.model;

import java.math.BigDecimal;

public class DiscountFee {
	
	private BigDecimal discountedPrice;
	private DiscountType discountType;
	
	public DiscountFee() {}
	
	public DiscountFee(BigDecimal discountedPrice, DiscountType discountType) {
		this.discountedPrice = discountedPrice;
		this.discountType = discountType;
	}

	public BigDecimal getDiscountedPrice() {
		return discountedPrice;
	}
	public void setDiscountedPrice(BigDecimal discountedPrice) {
		this.discountedPrice = discountedPrice;
	}
	public DiscountType getDiscountType() {
		return discountType;
	}
	public void setDiscountType(DiscountType discountType) {
		this.discountType = discountType;
	}
	
	@Override
	public String toString() {
		return "\nDiscountFee [discountedPrice=" + discountedPrice + ", discountType=" + discountType + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((discountType == null) ? 0 : discountType.hashCode());
		result = prime * result + ((discountedPrice == null) ? 0 : discountedPrice.hashCode());
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
		DiscountFee other = (DiscountFee) obj;
		if (discountType != other.discountType)
			return false;
		if (discountedPrice == null) {
			if (other.discountedPrice != null)
				return false;
		} else if (!discountedPrice.equals(other.discountedPrice))
			return false;
		return true;
	}

	
}
