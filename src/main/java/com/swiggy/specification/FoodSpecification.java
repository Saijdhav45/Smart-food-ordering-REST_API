package com.swiggy.specification;

import org.springframework.data.jpa.domain.Specification;

import com.swiggy.entity.FoodItem;

public class FoodSpecification {
	public static Specification<FoodItem> hasVeg(Boolean isVeg){
		return (root,query,cb)->{
			if(isVeg==null) return null;
			return cb.equal(root.get("isVeg"), isVeg);
		};
	}
	
	public static Specification<FoodItem> hasCategory(com.swiggy.entity.Category category){
		return (root,query,cb)->{
			if(category==null) return null;
			return cb.equal(root.get("category"), category);
		};
		
	
		 
	}
	public static Specification<FoodItem> hasKeyword(String keyword){
		return (root,query,cb)->{
			if(keyword==null || keyword.isBlank()) return null;
			
			  return cb.or(
		                cb.like(cb.lower(root.get("name")), "%" + keyword.toLowerCase() + "%"),
		                cb.like(cb.lower(root.get("description")), "%" + keyword.toLowerCase() + "%")
		            );
		};
	}
	
	  public static Specification<FoodItem> priceBetween(Double min, Double max) {
	        return (root, query, cb) -> {

	            if (min == null && max == null) return null;

	            if (min != null && max != null)
	                return cb.between(root.get("price"), min, max);

	            if (min != null)
	                return cb.greaterThanOrEqualTo(root.get("price"), min);

	            return cb.lessThanOrEqualTo(root.get("price"), max);
	        };
	    }
	
	
}
