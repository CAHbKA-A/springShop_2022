package ru.gb.springShop.core.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gb.springShop.api.CartDto;
import ru.gb.springShop.core.entities.Order;
import ru.gb.springShop.core.entities.OrderData;
import ru.gb.springShop.core.entities.OrderItem;
import ru.gb.springShop.core.intergrations.CartServiceIntegration;
import ru.gb.springShop.core.repositories.OrderRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {
    private final ProductService productService;

    private final OrderRepository orderRepository;

    private final CartServiceIntegration cartServiceIntegration;


    public Optional<Order> findById(Long id) {
        return orderRepository.findById(id);
    }

    public List<Order> findAllOrders() {
        return orderRepository.findAll();
    }


    @Transactional
    public Order createOrder(String username, OrderData orderData) {
        CartDto cartDto = cartServiceIntegration.getCurrentCart(username);


        Order order = new Order();
        if (cartDto.getItems().size() != 0) {
            order.setUsername(username);
            order.setAddress(orderData.getAddress());
            order.setPhone(orderData.getPhone());
            order.setTotalPrice(cartDto.getTotalPrice());

            order.setItems(cartDto.getItems().stream().map(
                    cartItem -> new OrderItem(
                            productService.findById(cartItem.getProductId()).get(),
                            order,
                            cartItem.getQuantity(),
                            cartItem.getPricePerProduct(),
                            cartItem.getPrice()
                    )
            ).collect(Collectors.toList()));
            orderRepository.save(order);
            cartServiceIntegration.clear(username);
        }
        return order;
    }
}