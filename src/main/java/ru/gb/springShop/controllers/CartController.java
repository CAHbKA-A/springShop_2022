package ru.gb.springShop.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gb.springShop.entities.Product;
import ru.gb.springShop.services.CartService;
import ru.gb.springShop.services.ProductService;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor

public class CartController {
    //Подключаем сервисы (финал -в обяз)
    private final CartService cartService;
    private final ProductService productService;


    //вытягивание всего списка
    @GetMapping
    public List<Product> findAllCarts() {
        return cartService.findAll();
    }

    //удаляем объект по id
    @GetMapping("/delete/{id}")
    void deleteCartById(@PathVariable Long id) {
        cartService.deleteById(id);
    }

    @GetMapping("/add-to-cart/{productId}")
    void addToCart2(@PathVariable Long productId) {

        Optional product = productService.findById(productId);
        cartService.addProduct1(product);

    }

}
