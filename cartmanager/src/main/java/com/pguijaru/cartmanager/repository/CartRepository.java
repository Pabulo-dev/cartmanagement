package com.pguijaru.cartmanager.repository;

import com.pguijaru.cartmanager.exception.CartNotFoundException;
import com.pguijaru.cartmanager.model.Cart;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CartRepository {

    private final List<Cart> cartList;
    private static Long lastId = 0L;

    private static synchronized Long generateId() {
        lastId++;
        return lastId;
    }

    public Cart createCart() {
        Cart cart = new Cart(generateId(), new ArrayList<>(), LocalDateTime.now());

        cartList.add(cart);
        return cart;
    }

    public void updateCart(Cart cart) {
        int index = cartList.indexOf(cart);
        if (index == -1) {
            throw new CartNotFoundException("Cart with id " + cart.getId() + " not found");
        }
        cartList.set(index, cart);
    }

    public Cart getCartById(Long id) {
        return cartList.stream()
                .filter(cart -> cart.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new CartNotFoundException("Cart with id " + id + " not found"));
    }

    public void deleteCartById(Long id) {
        Cart cart = getCartById(id);
        cartList.remove(cart);
    }


    @Scheduled(fixedRate = 1000)
    public void deleteExpiredCarts() {
        cartList.removeIf(cart -> cart.getLastUpdate().isBefore(LocalDateTime.now().minusMinutes(10)));
    }
}
