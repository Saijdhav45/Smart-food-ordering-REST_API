package com.swiggy.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.swiggy.entity.Cart;
import com.swiggy.entity.User;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByUser(User user);
}
