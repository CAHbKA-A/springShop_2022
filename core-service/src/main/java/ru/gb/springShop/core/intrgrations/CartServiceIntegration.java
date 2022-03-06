package ru.gb.springShop.core.intrgrations;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.gb.springShop.api.CartDto;
import ru.gb.springShop.api.ProductDto;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CartServiceIntegration {
    private final RestTemplate restTemplate;

    public Optional<CartDto> getCart() {
        return Optional.ofNullable(restTemplate.getForObject("http://localhost:8181/shop-carts/api/v1/cart" , CartDto.class));
    }

    public void  clear() {
       restTemplate.delete("http://localhost:8181/shop-carts/api/v1/cart/clear" );
    }

}
