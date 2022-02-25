package ru.gb.springShop.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Or;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.springframework.stereotype.Service;
import ru.gb.springShop.dtos.Cart;
import ru.gb.springShop.dtos.CartItem;
import ru.gb.springShop.entities.Order;
import ru.gb.springShop.entities.OrderItem;
import ru.gb.springShop.entities.User;
import ru.gb.springShop.repositories.OrderRepository;
import ru.gb.springShop.repositories.ProductRepository;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {
    private final ProductService productService;
    private final CartService cartService;
    private final OrderRepository orderRepository;



    public void createOrder(User user) {
        log.info(user.getUsername()+ " creating order");

        log.info(""+ cartService.getCurrentCart().getItems());
        Order order= new Order();
        order.setUser(user);
        List<CartItem> cartItems = cartService.getCurrentCart().getItems();
        List<OrderItem> orderItemList = new ArrayList<>();
        for (CartItem cartItem : cartItems) {
            orderItemList.add(new OrderItem(productService.findById(cartItem.getProductId()).get(),cartItem.getQuantity(),cartItem.getPricePerProduct(),cartItem.getPrice()));
           // orderItemList.add(new OrderItem(productService.findById(1L).get(),7,8,9));
        }
        order.setId(2L);
        order.setItems(orderItemList);
       order.setTotalPrice(cartService.getCurrentCart().getTotalPrice());
       log.info(String.valueOf(order));

        orderRepository.save(order);
      //  cartService.getCurrentCart().getItems().stream().map(cartItem -> new OrderItem(null,))
//        Order order = new Order();
//        Cart cart = new Cart();
//        cart.getItems().stream().map(cartItem -> {
//            new OrderItem(null,productService.findById(cartItem.getProductId(), order))
//        })

    }
}
