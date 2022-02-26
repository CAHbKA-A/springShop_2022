package ru.gb.springShop.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.gb.springShop.convertors.OrderConvertor;
import ru.gb.springShop.dtos.OrderDto;
import ru.gb.springShop.entities.Order;
import ru.gb.springShop.entities.OrderData;
import ru.gb.springShop.entities.User;
import ru.gb.springShop.exceptions.ResourceNotFoundException;
import ru.gb.springShop.services.OrderService;
import ru.gb.springShop.services.UserService;

import java.security.Principal;

@Slf4j
@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    private final UserService userService;
    private final OrderService orderService;
    private final OrderConvertor orderConvertor;


    //если прилетел post,создаем заказ
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder(Principal principal, @RequestBody OrderData orderData) {

        log.info("try to create order with Data "+orderData);

        User user = userService.findByUsername(principal.getName()).orElseThrow(() -> new ResourceNotFoundException("Пользователь не найден, Username: " + principal.getName()));
        orderService.createNewOrder(user,orderData);
    }

    @GetMapping("/{id}")
    public OrderDto findOrderById(@PathVariable Long id) {
        Order o = orderService.findById(id).orElseThrow(() -> new ResourceNotFoundException("заказ не найден, id: " + id));
        return orderConvertor.entityToDto(o);
    }


}
