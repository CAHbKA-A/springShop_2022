package ru.gb.springShop.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.springShop.entities.Cart;
import ru.gb.springShop.entities.Product;
import ru.gb.springShop.repositories.CartRepository;
import ru.gb.springShop.repositories.ProductRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;


    public Optional<Cart> findById(Long id) {
        return cartRepository.findById(id);
    }


    public void addProduct(Optional<Product> product) {
        Cart cart = new Cart();
        cart.setUserId(1L);
        cartRepository.save(cart);
    }

    public void deleteById(Long id) {
        Optional<Cart> cart = Optional.of(new Cart());
        Optional<Product> productForDel = productRepository.findById(id);
        //пока user ID - 1
        cart = cartRepository.findFirstByUserId(1L);
        Set<Product> products = cart.get().getProducts();
        products.remove(productForDel.get());
        cart.get().setProducts(products);
        System.out.println(cart.get());
        cartRepository.saveAndFlush(cart.get());
    }


    public void addProduct1(Optional<Product> product) {

        Optional<Cart> cart = Optional.of(new Cart());

        //пока user ID - 1
        if (cartRepository.findFirstByUserId(1L).isPresent()) {
            cart = cartRepository.findFirstByUserId(1L);
        } else {
            cart = Optional.of(new Cart());
        }
        Set<Product> products;
        if (cart.get().getProducts() != null) {
            products = cart.get().getProducts();
            products.add(product.get());
        } else products = new HashSet<>();
        cart.get().setProducts(products);
        System.out.println(cart.get());
        cartRepository.saveAndFlush(cart.get());
    }


    public Set<Product> findAll() {
        Cart cart = cartRepository.findFirstByUserId(1L).get();

        return cart.getProducts();
    }


}
