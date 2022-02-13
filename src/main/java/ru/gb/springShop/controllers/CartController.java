package ru.gb.springShop.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
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

    //удаляем объект по id
    @DeleteMapping("/{id}")
    void deleteCartById(@PathVariable Long id) {
        cartService.deleteById(id);
    }

}
