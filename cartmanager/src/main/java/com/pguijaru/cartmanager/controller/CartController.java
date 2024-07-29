package com.pguijaru.cartmanager.controller;

import com.pguijaru.cartmanager.model.Cart;
import com.pguijaru.cartmanager.model.Product;
import com.pguijaru.cartmanager.service.CartServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;

import java.util.List;

@Controller
@Tag(name = "Cart REST controller", description = "API for managing carts")
public class CartController {

    @Autowired
    private CartServiceImpl cartServiceImpl;


    @PostMapping("/cart")
    @Operation(summary = "Create a new cart")
    @ApiResponse(responseCode = "201", description = "Cart created successfully")
    public ResponseEntity<Cart> createCart() {
        return cartServiceImpl.createCart();
    }

    @GetMapping("/cart/{id}")
    @Operation(summary = "Get cart info by ID")
    @ApiResponse(responseCode = "200", description = "Cart found")
    @ApiResponse(responseCode = "404", description = "Cart not found")
    public ResponseEntity<Cart> getCart(@Parameter(description = "Cart ID") @PathVariable Long id) {
        return cartServiceImpl.getCartById(id);
    }

    @PostMapping("/cart/{id}/product/{productId}/amount/{amount}")
    @Operation(summary = "Add a product to a cart")
    @ApiResponse(responseCode = "200", description = "Product added to cart")
    @ApiResponse(responseCode = "400", description = "Invalid product amount")
    @ApiResponse(responseCode = "404", description = "Cart not found")
    public ResponseEntity<Cart> addProductToCart(
            @Parameter(description = "Cart ID") @PathVariable Long id,
            @Parameter(description = "Product ID") @PathVariable Long productId,
            @Parameter(description = "Product quantity") @PathVariable Integer amount) {
        return cartServiceImpl.addProductToCart(id, productId, amount);
    }

    @PostMapping("/cart/{id}/products")
    @Operation(summary = "Add multiple products to a cart")
    @ApiResponse(responseCode = "200", description = "Products added to cart")
    @ApiResponse(responseCode = "400", description = "Invalid product amount")
    @ApiResponse(responseCode = "404", description = "Cart not found")
    public ResponseEntity<Cart> addProductsToCart(
            @Parameter(description = "Cart ID") @PathVariable Long id,
            @RequestBody List<Product> products) {
        return cartServiceImpl.addProductsToCart(id, products);
    }

    @DeleteMapping("/cart/{id}")
    @Operation(summary = "Delete a cart by ID")
    @ApiResponse(responseCode = "200", description = "Cart deleted")
    @ApiResponse(responseCode = "404", description = "Cart not found")
    public ResponseEntity<String> deleteCart(@Parameter(description = "Cart ID") @PathVariable Long id) {
        return cartServiceImpl.deleteCartById(id);
    }
}
