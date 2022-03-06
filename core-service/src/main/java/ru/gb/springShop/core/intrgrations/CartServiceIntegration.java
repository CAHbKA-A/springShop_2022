package ru.gb.springShop.core.intrgrations;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.gb.springShop.api.CartDto;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CartServiceIntegration {
    @Value("${integration.url}")
    private String URL;


    private final RestTemplate restTemplate;

    public Optional<CartDto> getCart() {
        return Optional.ofNullable(restTemplate.getForObject(URL+"/shop-carts/api/v1/cart" , CartDto.class));
    }

    public void  clear() {
       restTemplate.delete(URL+"/shop-carts/api/v1/cart/clear" );
    }

}
