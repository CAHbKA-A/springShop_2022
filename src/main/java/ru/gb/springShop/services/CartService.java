package ru.gb.springShop.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.springShop.dtos.Cart;
import ru.gb.springShop.entities.Product;
import ru.gb.springShop.exceptions.ResourceNotFoundException;

import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
public class CartService {
    private final ProductService productService;
    private Cart tempCart;

    @PostConstruct
    public void init() {
        tempCart = new Cart();
    }

    public Cart getCurrentCart() {
        return tempCart;
    }

    public void add(Long productId) {
        Product product = productService.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Не  удается добавить продукт: " + productId + " в корзину. Продукт не найден"));
        tempCart.add(product);
    }

    public void deleteItemFromCart(Long productId) {
        tempCart.deleteItemFromCart(productId);
    }

    public void SetCountItemInCart(Long id, int count) {


        if (count <= 0) {

            tempCart.setCount(id, 0);
            //наврное не надо убирать из корзины. вдруг передумает и добавит
            //deleteItemFromCart(id);
        } else {
            tempCart.setCount(id, count);
        }
    }
}
