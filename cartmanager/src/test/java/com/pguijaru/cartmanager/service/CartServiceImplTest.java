package com.pguijaru.cartmanager.service;

import com.pguijaru.cartmanager.model.Cart;
import com.pguijaru.cartmanager.model.Product;
import com.pguijaru.cartmanager.repository.CartRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CartServiceImplTest {

    @Mock
    private CartRepository cartRepository;

    @InjectMocks
    private CartServiceImpl cartServiceImpl;

    @Test
    void testCreateCart() {
        Cart cart = new Cart();
        when(cartRepository.createCart()).thenReturn(cart);
        ResponseEntity<Cart> response = cartServiceImpl.createCart();
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(cart, response.getBody());
    }

    @Test
    void testGetCartById() {
        Cart cart = new Cart();
        when(cartRepository.getCartById(anyLong())).thenReturn(cart);
        ResponseEntity<Cart> response = cartServiceImpl.getCartById(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(cart, response.getBody());
    }

    @Test
    void testAddProductToCart() {
        Cart cart = new Cart(1L, new ArrayList<>(), null);
        when(cartRepository.getCartById(1L)).thenReturn(cart);
        doNothing().when(cartRepository).updateCart(any(Cart.class));

        ResponseEntity<Cart> response = cartServiceImpl.addProductToCart(1L, 1L, 2);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().getProducts().size());
    }

    @Test
    void testAddProductsToCart() {
        Cart cart = new Cart(1L, new ArrayList<>(), null);
        when(cartRepository.getCartById(1L)).thenReturn(cart);
        doNothing().when(cartRepository).updateCart(any(Cart.class));

        List<Product> products = new ArrayList<>();
        products.add(new Product(1L, "Product 1", 1));
        products.add(new Product(2L, "Product 2", 2));

        ResponseEntity<Cart> response = cartServiceImpl.addProductsToCart(1L, products);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().getProducts().size());
    }

    @Test
    void testDeleteCartById() {
        doNothing().when(cartRepository).deleteCartById(1L);
        ResponseEntity<String> response = cartServiceImpl.deleteCartById(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Cart 1 deleted", response.getBody());
    }
}
