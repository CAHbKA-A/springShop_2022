package ru.gb.springShop.carts.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.gb.springShop.api.CartDto;
import ru.gb.springShop.api.StringResponse;
import ru.gb.springShop.carts.convertes.CartConverter;
import ru.gb.springShop.carts.services.CartService;

import java.util.UUID;


@Slf4j
@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
//@CrossOrigin("http://localhost:8080") //разрешить запросы только с порта 8080
public class CartController {
    private final CartService cartService;
    private final CartConverter cartConverter;


    //корзина для неавторизованных
    @GetMapping("/default_uuid")
    public StringResponse generateUuid() {
        return new StringResponse(UUID.randomUUID().toString());
    }

    @GetMapping("/{uuid}/add/{id}")
    public void addToCart(@RequestHeader(name = "username", required = false) String username, @PathVariable String uuid, @PathVariable Long id) {
        uuid = getCartUuid(username, uuid);
        cartService.add(uuid, id);

    }


    @GetMapping("/{uuid}/deleteItem/{id}")
    public void deleteItemFromCart(@RequestHeader(name = "username", required = false) String username, @PathVariable String uuid, @PathVariable Long id) {
        uuid = getCartUuid(username, uuid);
        cartService.deleteItemFromCart(uuid, id);
    }


    @GetMapping("/{uuid}/SetCountItem")
    @ResponseBody

    public void getCount(@RequestHeader(name = "username", required = false) String username, @PathVariable String uuid, @RequestParam Long id, int count) {
        uuid = getCartUuid(username, uuid);
        cartService.setCountItemInCart(uuid, id, count);
    }

    @GetMapping("/{uuid}/clear")
    public void clearCart(@RequestHeader(name = "username", required = false) String username, @PathVariable String uuid) {
        uuid = getCartUuid(username, uuid);
        cartService.clear(uuid);
    }

    @GetMapping("/mergeCart/{uuid}")
    public void merge(@RequestHeader(name = "username", required = false) String username, @PathVariable String uuid) {

        cartService.mergeCarts(username, uuid);


    }

    @GetMapping("/{uuid}")
    public CartDto getCurrentCart(@RequestHeader(name = "username", required = false) String username, @PathVariable String uuid) {
        //  cartService.mergeCarts(username,uuid);
        uuid = getCartUuid(username, uuid);
        return cartConverter.entityToDto(cartService.getCurrentCart(uuid));
    }

    private String getCartUuid(String username, String uuid) {
        if (username != null) {
            return username;
        }
        return uuid;
    }
}
