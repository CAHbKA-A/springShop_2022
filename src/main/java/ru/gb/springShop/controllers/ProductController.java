package ru.gb.springShop.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.gb.springShop.entities.Product;
import ru.gb.springShop.services.ProductService;


import java.util.List;


@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor

public class ProductController {
    //Подключаем сервисы (финал -в обяз)
    private final ProductService productService;


    //вытягивание всего списка
    @GetMapping
    public List<Product> findAllProducts() {
        return productService.findAll();
    }


}
