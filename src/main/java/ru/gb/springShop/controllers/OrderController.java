package ru.gb.springShop.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.gb.springShop.entities.Product;
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


    //если прилетел post,создаем заказ
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder(Principal principal /*, @RequestBody OrderData orderData */) {
        log.info("try to create order");
        User user = userService.findByUsername(principal.getName()).orElseThrow(() -> new ResourceNotFoundException("Пользователь не найден, Username: " + principal.getName()));



        orderService.createOrder(user);
    }


}
