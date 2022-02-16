package ru.gb.springShop.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.gb.springShop.entities.Product;
import ru.gb.springShop.services.CartService;
import ru.gb.springShop.services.ProductService;

import java.util.List;


@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor

public class ProductController {
    //Подключаем сервисы (финал -в обяз)
    private final ProductService productService;
    private final CartService cartService;


    //вытягивание всего списка
    @GetMapping
    public List<Product> findAllProducts() {
        return productService.findAll();
    }

    //выдергиваем  объект по id
    @GetMapping("/{id}")
    public Product findProductById(@PathVariable Long id) {
        return productService.findById(id).get();
    }

    //удаляем объект по id
    @DeleteMapping("/{id}")
    void deleteProductById(@PathVariable Long id) {
        productService.deleteById(id);
    }



}
