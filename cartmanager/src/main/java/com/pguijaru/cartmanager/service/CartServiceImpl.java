package com.pguijaru.cartmanager.service;

import com.pguijaru.cartmanager.exception.CartException;
import com.pguijaru.cartmanager.model.Cart;
import com.pguijaru.cartmanager.model.Product;
import com.pguijaru.cartmanager.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;


    @Override
    public ResponseEntity<Cart> createCart() {
        Cart cart = cartRepository.createCart();
        return new ResponseEntity<>(cart, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Cart> getCartById(Long id) {
        Cart cart = cartRepository.getCartById(id);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Cart> addProductToCart(Long cartId, Long productId, int amount) {
        if (amount <= 0) {
            throw new CartException("Amount must be greater than 0");
        }

        Cart cart = cartRepository.getCartById(cartId);
        List<Product> cartProducts = cart.getProducts();

        boolean productExists = false;
        for (Product cartProduct : cartProducts) {
            if (cartProduct.getId().equals(productId)) {
                cartProduct.setAmount(cartProduct.getAmount() + amount);
                productExists = true;
                break;
            }
        }
        if (!productExists) {
            Product newProduct = new Product();
            newProduct.setId(productId);
            newProduct.setAmount(amount);
            cartProducts.add(newProduct);
        }

        cart.setProducts(cartProducts);
        cart.setLastUpdate(LocalDateTime.now());
        cartRepository.updateCart(cart);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Cart> addProductsToCart(Long id, List<Product> products) {
        for (Product product : products) {
            if (product.getAmount() <= 0) {
                throw new CartException("Amount must be greater than 0");
            }
        }

        Cart cart = cartRepository.getCartById(id);
        List<Product> cartProducts = cart.getProducts();

        for (Product newProduct : products) {
            boolean productExists = false;
            for (Product cartProduct : cartProducts) {
                if (cartProduct.getId().equals(newProduct.getId())) {
                    cartProduct.setAmount(cartProduct.getAmount() + newProduct.getAmount());
                    productExists = true;
                    break;
                }
            }
            if (!productExists) {
                cartProducts.add(newProduct);
            }
        }

        cart.setProducts(cartProducts);
        cart.setLastUpdate(LocalDateTime.now());
        cartRepository.updateCart(cart);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> deleteCartById(Long id) {
        cartRepository.deleteCartById(id);
        return new ResponseEntity<>("Cart " + id + " deleted", HttpStatus.OK);
    }
}
