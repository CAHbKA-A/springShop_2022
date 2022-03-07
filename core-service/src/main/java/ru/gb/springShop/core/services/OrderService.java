package ru.gb.springShop.core.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gb.springShop.api.CartDto;
import ru.gb.springShop.api.ResourceNotFoundException;
import ru.gb.springShop.core.convertors.OrderConvertor;
import ru.gb.springShop.core.entities.Order;
import ru.gb.springShop.core.entities.OrderData;
import ru.gb.springShop.core.entities.OrderItem;
import ru.gb.springShop.core.entities.User;
import ru.gb.springShop.core.intrgrations.CartServiceIntegration;
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
    public void createNewOrder(User user, OrderData orderData) {
        log.info(user.getUsername() + " creating order");
     //   CartDto cartDto =cartServiceIntegration.getCart().get();
        CartDto cartDto =cartServiceIntegration.getCart().orElseThrow(() -> new ResourceNotFoundException("Не  удается найти корзину"));

        Order order = new Order();
        order.setAddress(orderData.getAddress());
        order.setPhone(orderData.getPhone());
        order.setUser(user);
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
         cartServiceIntegration.clear();
    }

}
