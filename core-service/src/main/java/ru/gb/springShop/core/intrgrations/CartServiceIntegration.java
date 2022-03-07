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
    @Value("${integration.urlCart}")
    private String URL;


    private final RestTemplate restTemplate;

    public Optional<CartDto> getCart() {
        return Optional.ofNullable(restTemplate.getForObject(URL , CartDto.class));
    }

    public void  clear() {
       restTemplate.delete(URL+"/clear" );
    }

}
