package ru.gb.springShop.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.springShop.entities.Cart;
import ru.gb.springShop.entities.Product;
import ru.gb.springShop.repositories.CartRepository;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;

    public List<Cart> findAll() {

        return cartRepository.findAll();
    }

    public void addProduct(Optional<Product> product) {
        Cart cart = new Cart();
        cart.setProductId(product.get().getId());
        cart.setCount(1);
        cart.setUserId(0L);
        cart.setProductName(product.get().getTitle());
        cart.setProductCost(product.get().getPrice() * cart.getCount());

        System.out.println(cart);
        cartRepository.save(cart);
    }

    public void deleteById(Long id) {
        cartRepository.deleteById(id);
    }


}
