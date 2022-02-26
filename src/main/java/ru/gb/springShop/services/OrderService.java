package ru.gb.springShop.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.gb.springShop.entities.*;
import ru.gb.springShop.repositories.OrderItemRepository;
import ru.gb.springShop.repositories.OrderRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {
    private final ProductService productService;
    private final CartService cartService;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;



    public Optional<Order> findById(Long id) {
        return orderRepository.findById(id);
    }


    private List<OrderItem> cartToOrderItems() {

        List<OrderItem> orderItemList = new ArrayList<>();
        for (CartItem cartItem : cartService.getCurrentCart().getItems()) {
            OrderItem orderItem = new OrderItem(
                    null,
                    productService.findById(cartItem.getProductId()).get(),
                    null,
                    cartItem.getQuantity(),
                    cartItem.getPricePerProduct(),
                    cartItem.getPrice(),
                    null,
                    null);
            orderItemList.add(orderItem);
        }
        return orderItemList;
    }


    public Order createNewOrder(User user, OrderData orderData) {
        log.info(user.getUsername() + " creating order");
        //todo в Stream
        Order order = new Order();
        order.setUser(user);
        order.setAddress(orderData.getAddress());
        order.setPhone(orderData.getPhone());
        order.setTotalPrice(cartService.getCurrentCart().getTotalPrice());
        order.setItems(cartToOrderItems());
        orderRepository.save(order);
        saveItemsList(order.getItems(), order.getId());

        return order;
    }

    public void saveItemsList(List<OrderItem> list, Long id) {
        for (OrderItem orderItem : list) {
            orderItem.setOrder(orderRepository.findById(id).get());
            orderItemRepository.save(orderItem);
        }
    }

}
