package com.pguijaru.cartmanager.service;

import com.pguijaru.cartmanager.model.Cart;
import com.pguijaru.cartmanager.model.Product;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CartService {

    ResponseEntity<Cart> createCart();
    ResponseEntity<Cart> getCartById(Long id);
    ResponseEntity<Cart> addProductToCart(Long id, Long productId, int amount);
    ResponseEntity<Cart> addProductsToCart(Long id, List<Product> products);
    ResponseEntity<String> deleteCartById(Long id);

}
