package ru.gb.springShop.carts.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.gb.springShop.api.CartDto;
import ru.gb.springShop.carts.convertes.CartConverter;
import ru.gb.springShop.carts.services.CartService;


@Slf4j
@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
//@CrossOrigin("http://localhost:8080") //разрешить запросы только с порта 8080
public class CartController {
    private final CartService cartService;
    private final CartConverter cartConverter;

    @GetMapping("/add/{id}")
    public void addToCart(@PathVariable Long id) {
        cartService.add(id);
    }


    @GetMapping("/deleteItem/{id}")
    public void deleteItemFromCart(@PathVariable Long id) {
        cartService.deleteItemFromCart(id);
    }


    @GetMapping("/SetCountItem")
    @ResponseBody

    public void getCount(@RequestParam Long id, int count) {

        cartService.setCountItemInCart(id, count);
    }

    @GetMapping("/clear")
    public void clearCart() {
        cartService.clear();
    }


    @GetMapping
    public CartDto getCurrentCart() {
        return cartConverter.entityToDto(cartService.getCurrentCart());
    }
}
