package ru.gb.springShop.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gb.springShop.entities.Cart;
import ru.gb.springShop.services.CartService;

import java.util.List;


@RestController
@RequestMapping("/api/v1/carts")
@RequiredArgsConstructor

public class CartController {
    //Подключаем сервисы (финал -в обяз)
    private final CartService cartService;


    //вытягивание всего списка
    @GetMapping
    public List<Cart> findAllProducts() {
        return cartService.findAll();
    }


}
