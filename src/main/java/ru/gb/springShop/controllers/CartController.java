package ru.gb.springShop.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.gb.springShop.entities.Cart;
import ru.gb.springShop.services.CartService;

@Slf4j
@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

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
     //   log.info("set for pr " + id + "  count= " + count);
        cartService.setCountItemInCart(id, count);
    }

    @GetMapping("/clear")
    @ResponseBody
    public void clearCart() {
        cartService.clear();
    }


    @GetMapping
    public Cart getCurrentCart() {
        return cartService.getCurrentCart();
    }
}
