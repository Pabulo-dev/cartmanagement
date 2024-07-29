package com.pguijaru.cartmanager.controller;

import com.pguijaru.cartmanager.model.Cart;
import com.pguijaru.cartmanager.model.Product;
import com.pguijaru.cartmanager.service.CartServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CartController {

    @Autowired
    private CartServiceImpl cartServiceImpl;


    @PostMapping("/cart")
    public ResponseEntity<Cart> createCart() {
        return cartServiceImpl.createCart();
    }

    @GetMapping("/cart/{id}")
    public ResponseEntity<Cart> getCart(@PathVariable Long id) {
        return cartServiceImpl.getCartById(id);
    }

    @PostMapping("/cart/{id}/product/{productId}/amount/{amount}")
    public ResponseEntity<Cart> addProductToCart(@PathVariable Long id, @PathVariable Long productId, @PathVariable Integer amount) {
        return cartServiceImpl.addProductToCart(id, productId, amount);
    }

    @PostMapping("/cart/{id}/products")
    public ResponseEntity<Cart> addProductsToCart(@PathVariable Long id, @RequestBody List<Product> products) {
        return cartServiceImpl.addProductsToCart(id, products);
    }

    @DeleteMapping("/cart/{id}")
    public ResponseEntity<String> deleteCart(@PathVariable Long id) {
        return cartServiceImpl.deleteCartById(id);
    }
}
