package ru.gb.springShop.carts.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.gb.springShop.api.ProductDto;
import ru.gb.springShop.carts.entities.Cart;
import ru.gb.springShop.carts.intrgrations.ProductServiceIntegration;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CartService {
    private final ProductServiceIntegration productServiceIntegration;
    private Map<String, Cart> allCarts;
    @Value("${cart-service.cart-prefix}")
    private String prefixForCarts;

    @PostConstruct
    public void init() {
        allCarts = new HashMap<>();
        // tempCart.setUser();
    }

    public Cart getCurrentCart(String cartIdByUser) {
        String fullCartId = prefixForCarts+cartIdByUser;
        if (!allCarts.containsKey(fullCartId)) {
            allCarts.put(fullCartId, new Cart());
        }
        return allCarts.get(fullCartId);
    }

    public void add(String cartIdByUser,Long productId) {
        ProductDto product = productServiceIntegration.getProductById(productId);
        getCurrentCart(cartIdByUser).add(product);

    }

    public void deleteItemFromCart(String cartIdByUser,Long productId) {

        getCurrentCart(cartIdByUser).remove(productId);
    }


    public void clear(String cartIdByUser) {

        getCurrentCart(cartIdByUser).clear();
    }

    public void setCountItemInCart(String cartIdByUser, Long id, int count) {

        getCurrentCart(cartIdByUser).setCount(id, Math.max(count, 0));
    }


}
