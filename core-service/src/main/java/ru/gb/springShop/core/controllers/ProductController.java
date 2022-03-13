package ru.gb.springShop.core.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.gb.springShop.api.ProductDto;
import ru.gb.springShop.api.ResourceNotFoundException;
import ru.gb.springShop.core.convertors.ProductConverter;
import ru.gb.springShop.core.entities.FilterData;
import ru.gb.springShop.core.entities.Product;
import ru.gb.springShop.core.services.ProductService;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor

public class ProductController {
    //Подключаем сервисы (финал -в обяз)
    private final ProductService productService;
    private final ProductConverter productConverter;

    //вытягивание всего списка
    @GetMapping
    public List<ProductDto> findAllProducts() {
        //собираем и преобразуем в dto
        return productService.findAll().stream().map(p -> new ProductDto(p.getId(), p.getTitle(), p.getPrice())).collect(Collectors.toList());

    }


    @GetMapping("/{id}")

    public ProductDto findProductById(@PathVariable Long id) {
        Product p = productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Продукт не найден, id: " + id));
        return new ProductDto(p.getId(), p.getTitle(), p.getPrice());
    }


    //удаляем объект по id
    @DeleteMapping("/{id}")
    void deleteProductById(@PathVariable Long id) {
        productService.deleteById(id);
    }


    @PostMapping("/filter")
    @ResponseStatus(HttpStatus.CREATED)
    public  List<ProductDto>  filter(@RequestBody FilterData filterData) {

        //log.info(" "+filterData.getMinPrice()+filterData.getMaxPrice()+filterData.getTextSearch());
        return productService.findByFilter(filterData).stream().map(p -> new ProductDto(p.getId(), p.getTitle(), p.getPrice())).collect(Collectors.toList());

    }


}
