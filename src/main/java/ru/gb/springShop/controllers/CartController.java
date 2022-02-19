package ru.gb.springShop.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.gb.springShop.dtos.Cart;
import ru.gb.springShop.services.CartService;

import java.util.List;


@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @GetMapping("/add/{id}")
    public void addToCart(@PathVariable Long id) {
        cartService.add(id);
    }

    @GetMapping("/deleteItemFromCart/{id}")
    public void deleteItemFromCart(@PathVariable Long id) {
        cartService.deleteItemFromCart(id);
    }


    @GetMapping("/SetCountItemInCart")
    @ResponseBody
    public void getCount(@RequestParam List<Long> param) {
        //todo проверка , что пришли 2 параметра
        Long id = param.get(0);
        int count = param.get(1).intValue();
        cartService.SetCountItemInCart(id, count);
    }

    @GetMapping("/clearCart")
    @ResponseBody
    public void clearCart() {
        System.out.println("546546456");
        cartService.clearCart();
    }


    @GetMapping
    public Cart getCurrentCart() {
        return cartService.getCurrentCart();
    }
}
