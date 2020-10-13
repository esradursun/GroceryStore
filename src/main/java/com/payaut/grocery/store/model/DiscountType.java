package com.payaut.grocery.store.model;

public enum DiscountType {
	
	BREADDISCOUNT("Bread Discount"), VEGETABLEDISCOUNT("Vegetable Discount"), BEERDISCOUNT("Beer Discount"), NODISCOUNTELIGIBLE("No Discount Eligible");
	
	public final String value;
	
	private DiscountType(String value) {
		this.value = value;
	}
	
	@Override 
	public String toString() { 
	    return this.value; 
	}

}
