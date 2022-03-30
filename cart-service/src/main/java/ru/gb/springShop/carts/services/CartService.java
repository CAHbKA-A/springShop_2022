package ru.gb.springShop.carts.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.springShop.api.ProductDto;
import ru.gb.springShop.api.ResourceNotFoundException;
import ru.gb.springShop.carts.entities.Cart;
import ru.gb.springShop.carts.intrgrations.ProductServiceIntegration;


import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
public class CartService {
    private final ProductServiceIntegration productServiceIntegration;
    private Cart tempCart;

    @PostConstruct
    public void init() {
        tempCart = new Cart();
       // tempCart.setUser();
    }

    public Cart getCurrentCart() {
        return tempCart;
    }

    public void add(Long productId) {
        ProductDto product = productServiceIntegration.getProductById(productId);
        tempCart.add(product);
    }

    public void deleteItemFromCart(Long productId) {
        tempCart.remove(productId);
    }


    public void clear() {
        tempCart.clear();
    }

    public void setCountItemInCart(Long id, int count) {

        tempCart.setCount(id, Math.max(count, 0));
    }


}
