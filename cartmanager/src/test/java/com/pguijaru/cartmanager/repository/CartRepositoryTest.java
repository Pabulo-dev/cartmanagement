package com.pguijaru.cartmanager.repository;

import com.pguijaru.cartmanager.exception.CartNotFoundException;
import com.pguijaru.cartmanager.model.Cart;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

class CartRepositoryTest {

    private CartRepository cartRepository;

    @BeforeEach
    void setUp() {
        cartRepository = new CartRepository(new ArrayList<>());
    }

    @Test
    void testCreateCart() {
        Cart cart = cartRepository.createCart();
        assertNotNull(cart);
        assertNotNull(cart.getId());
        assertEquals(0, cart.getProducts().size());
    }

    @Test
    void testGetCartById_CartExists() {
        Cart cart = cartRepository.createCart();
        Cart retrievedCart = cartRepository.getCartById(cart.getId());
        assertEquals(cart, retrievedCart);
    }

    @Test
    void testGetCartById_CartDoesNotExist() {
        assertThrows(CartNotFoundException.class, () -> cartRepository.getCartById(999L));
    }

    @Test
    void testDeleteCartById_CartExists() {
        Cart cart = cartRepository.createCart();
        cartRepository.deleteCartById(cart.getId());
        assertThrows(CartNotFoundException.class, () -> cartRepository.getCartById(cart.getId()));
    }

    @Test
    void testDeleteExpiredCarts() {
        Cart cart = cartRepository.createCart();
        cart.setLastUpdate(LocalDateTime.now().minusMinutes(11));
        cartRepository.deleteExpiredCarts();
        assertThrows(CartNotFoundException.class, () -> cartRepository.getCartById(cart.getId()));
    }


    @Test
    void updateCartUpdatesExistingCart() {
        Cart cart = cartRepository.createCart();
        cart.setLastUpdate(LocalDateTime.now().minusMinutes(5));
        cartRepository.updateCart(cart);
        Cart updatedCart = cartRepository.getCartById(cart.getId());
        assertEquals(cart.getLastUpdate(), updatedCart.getLastUpdate());
    }

    @Test
    void updateCartThrowsExceptionWhenCartDoesNotExist() {
        Cart cart = new Cart(999L, new ArrayList<>(), LocalDateTime.now());
        assertThrows(CartNotFoundException.class, () -> cartRepository.updateCart(cart));
    }
}
