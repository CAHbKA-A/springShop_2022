package ru.gb.springShop.carts.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import ru.gb.springShop.api.ProductDto;
import ru.gb.springShop.carts.entities.Cart;
import ru.gb.springShop.carts.intrgrations.ProductServiceIntegration;

import java.util.function.Consumer;

@Service
@RequiredArgsConstructor
public class CartService {
    private final ProductServiceIntegration productServiceIntegration;
    private final RedisTemplate<String, Object> redisTemplate;


    @Value("${cart-service.cart-prefix}")
    private String prefixForCarts;


    public Cart getCurrentCart(String cartIdByUser) {
        String fullCartId = prefixForCarts + cartIdByUser;
        if (!redisTemplate.hasKey(fullCartId)) {
            redisTemplate.opsForValue().set(fullCartId, new Cart());
        }
        return (Cart) redisTemplate.opsForValue().get(fullCartId);
    }

    public void add(String cartIdByUser, Long productId) {
        ProductDto product = productServiceIntegration.getProductById(productId);
        System.out.println(product);
        //        Cart cart = getCurrentCart(cartIdByUser);
//        cart.add(product);
        //       redisTemplate.opsForValue().set(prefixForCarts + cartIdByUser, cart);
        execute(cartIdByUser, cart -> cart.add(product));

    }

    public void deleteItemFromCart(String cartIdByUser, Long productId) {
//        Cart cart = getCurrentCart(cartIdByUser);
//        cart.remove(productId);
//        redisTemplate.opsForValue().set(prefixForCarts + cartIdByUser, cart);
        execute(cartIdByUser, cart -> cart.remove(productId));
    }


    public void clear(String cartIdByUser) {
//        Cart cart = getCurrentCart(cartIdByUser);
//        cart.clear();
//        redisTemplate.opsForValue().set(prefixForCarts + cartIdByUser, cart);
        //упрощаем на:
        //   execute(cartIdByUser,cart -> cart.clear());
        // еще короче
        execute(cartIdByUser, Cart::clear);


    }

    public void setCountItemInCart(String cartIdByUser, Long id, int count) {
//        Cart cart = getCurrentCart(cartIdByUser);
//        cart.setCount(id, Math.max(count, 0));
//        redisTemplate.opsForValue().set(prefixForCarts + cartIdByUser, cart);
        execute(cartIdByUser, cart -> cart.setCount(id, Math.max(count, 0)));
    }

    //вынесли поторяющиеся методы
    private void execute(String cartIdByUser, Consumer<Cart> operation) {
        Cart cart = getCurrentCart(cartIdByUser);
        operation.accept(cart); //выполняем переданый метод
        redisTemplate.opsForValue().set(prefixForCarts + cartIdByUser, cart);

    }

}
