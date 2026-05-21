package com.swiggy.specification;

import org.springframework.data.jpa.domain.Specification;
import com.swiggy.entity.Restaurant;
import com.swiggy.entity.RestaurantCategory;

public class RestaurantSpecification {

    public static Specification<Restaurant> hasCity(String city) {
        return (root, query, cb) ->
                city == null ? null : cb.equal(root.get("city"), city);
    }

    public static Specification<Restaurant> hasCategory(RestaurantCategory category) {
        return (root, query, cb) ->
                category == null ? null : cb.equal(root.get("restaurantCategory"), category);
    }

    public static Specification<Restaurant> hasNameLike(String search) {
        return (root, query, cb) ->
                search == null ? null :
                        cb.like(cb.lower(root.get("name")), "%" + search.toLowerCase() + "%");
    }

    public static Specification<Restaurant> isActive() {
        return (root, query, cb) ->
                cb.isTrue(root.get("isActive"));
    }
}