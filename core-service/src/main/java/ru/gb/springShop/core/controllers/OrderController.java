package ru.gb.springShop.core.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.gb.springShop.api.OrderDto;
import ru.gb.springShop.api.ResourceNotFoundException;
import ru.gb.springShop.core.convertors.OrderConvertor;
import ru.gb.springShop.core.entities.Order;
import ru.gb.springShop.core.entities.OrderData;
import ru.gb.springShop.core.entities.User;
import ru.gb.springShop.core.services.OrderService;
import ru.gb.springShop.core.services.UserService;

import java.security.Principal;
import java.util.List;

@Slf4j
@RestController

@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
@CrossOrigin("*") // временный обход секьюрити
public class OrderController {
    private final UserService userService;
    private final OrderService orderService;
    private final OrderConvertor orderConvertor;


    //если прилетел post,создаем заказ
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder(Principal principal, @RequestBody OrderData orderData) {

        log.info("try to create order with Data " + orderData);

        User user = userService.findByUsername(principal.getName()).orElseThrow(() -> new ResourceNotFoundException("Пользователь не найден, Username: " + principal.getName()));
        orderService.createNewOrder(user, orderData);
    }

    @GetMapping("/{id}")
    public OrderDto findOrderById(@PathVariable Long id) {
        Order o = orderService.findById(id).orElseThrow(() -> new ResourceNotFoundException("заказ не найден, id: " + id));
        return orderConvertor.entityToDto(o);
    }


    @GetMapping
    public List<OrderDto> findAllProducts() {
        return orderConvertor.listToDto(orderService.findAllOrders());

    }

}
