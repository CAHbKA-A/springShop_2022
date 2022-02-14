package ru.gb.springShop.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.springShop.entities.Cart;
import ru.gb.springShop.entities.Product;
import ru.gb.springShop.repositories.CartRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;

    public List<Cart> findAll() {

        return cartRepository.findAll();
    }

    public Optional<Cart> findById(Long id) {
        return cartRepository.findById(id);
    }


    public void addProduct(Optional<Product> product) {
        Cart cart = new Cart();
        cart.setProductId(product.get().getId());
        cart.setCount(1);
        cart.setUserId(0L);
        cart.setProductName(product.get().getTitle());
        cart.setProductCost(product.get().getPrice() * cart.getCount());

      //  System.out.println(cart);
        cartRepository.save(cart);
    }

    public void deleteById(Long id) {
        cartRepository.deleteById(id);
    }



//
//    public void addProduct1(Optional<Product> product) {
//
//        Optional<Cart> cart = Optional.of(new Cart());
//        //пока Cart ID - 1
//        if (cartRepository.findById(1L).isPresent()) {
//            cart = cartRepository.findById(1L);
//
//
//           // System.out.println("111 "+cart);
//        } //else System.out.println("нет корзин у пользователя");
//
//        Set<Product> products = new HashSet<>();
//        if (cart.get().getProducts() != null) {
//             products = cart.get().getProducts();
//        }
//
//      //  products.add(Optional<Product> product);
//        System.out.println(products);
//
//
//        System.out.println(cart);
//        //   cartRepository.save(Optional<Cart> cart);
//    }






}
