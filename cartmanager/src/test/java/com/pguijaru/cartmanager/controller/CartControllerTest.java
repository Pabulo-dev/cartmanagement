package com.pguijaru.cartmanager.controller;

import com.pguijaru.cartmanager.model.Cart;
import com.pguijaru.cartmanager.model.Product;
import com.pguijaru.cartmanager.service.CartServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CartControllerTest {

    @Mock
    private CartServiceImpl cartServiceImpl;

    @InjectMocks
    private CartController cartController;

    @Test
    void testCreateCart() {
        Cart cart = new Cart();
        cart.setId(1L);
        when(cartServiceImpl.createCart()).thenReturn(new ResponseEntity<>(cart, HttpStatus.CREATED));

        ResponseEntity<Cart> response = cartController.createCart();
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1L, response.getBody().getId());
    }

    @Test
    void testGetCart() {
        Cart cart = new Cart();
        cart.setId(1L);
        when(cartServiceImpl.getCartById(1L)).thenReturn(new ResponseEntity<>(cart, HttpStatus.OK));

        ResponseEntity<Cart> response = cartController.getCart(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1L, response.getBody().getId());
    }

    @Test
    void testAddProductToCart() {
        Cart cart = new Cart();
        cart.setId(1L);
        Product product = new Product(1L, "Product 1", 2);
        cart.setProducts(List.of(product));

        when(cartServiceImpl.addProductToCart(1L, 1L, 2)).thenReturn(new ResponseEntity<>(cart, HttpStatus.OK));

        ResponseEntity<Cart> response = cartController.addProductToCart(1L, 1L, 2);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().getProducts().size());
        assertEquals(2, response.getBody().getProducts().getFirst().getAmount());
    }

    @Test
    void testAddProductsToCart() {
        Cart cart = new Cart();
        cart.setId(1L);
        Product product1 = new Product(1L, "Product 1", 1);
        Product product2 = new Product(2L, "Product 2", 2);
        List<Product> products = Arrays.asList(product1, product2);
        cart.setProducts(products);

        when(cartServiceImpl.addProductsToCart(1L, products)).thenReturn(new ResponseEntity<>(cart, HttpStatus.OK));

        ResponseEntity<Cart> response = cartController.addProductsToCart(1L, products);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().getProducts().size());
    }

    @Test
    void testDeleteCart() {
        when(cartServiceImpl.deleteCartById(1L)).thenReturn(new ResponseEntity<>("Cart 1 deleted", HttpStatus.OK));

        ResponseEntity<String> response = cartController.deleteCart(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Cart 1 deleted", response.getBody());
    }
}
