package ru.gb.springShop.carts.intrgrations;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.gb.springShop.api.ProductDto;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProductServiceIntegration {

    @Value("${integration.urlCore}")
    private String URL;


    private final RestTemplate restTemplate;

    public Optional<ProductDto> getProductById(Long id) {
        return Optional.ofNullable(restTemplate.getForObject(URL + "/" + id, ProductDto.class));
    }
}
