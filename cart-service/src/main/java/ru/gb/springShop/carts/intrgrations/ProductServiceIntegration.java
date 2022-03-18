package ru.gb.springShop.carts.intrgrations;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.gb.springShop.api.ProductDto;
import ru.gb.springShop.api.ResourceNotFoundException;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProductServiceIntegration {




//    private final RestTemplate restTemplate;
private final WebClient productServiceWebClient; //рест убрали, заинжектили вебклиента

    public ProductDto getProductById(Long id) {

        //конфигурим запрос
        return productServiceWebClient.get()//тип запроса
                .uri("/api/v1/products/" + id) //куда
                //можно добавить хедеры, куки пр
               // .header("name", "ewfwew")
                .retrieve() //отоправляем запрос. возрващает responseSpec(упаковка над ответом)
                .onStatus(//перехват статусов
                        httpStatus -> httpStatus.value() == HttpStatus.NOT_FOUND.value(), //если 404
                        clientResponse -> Mono.error(new ResourceNotFoundException("ошибка в МС cart. или товар не найден"))
                )//если статус 200 или 201
                .bodyToMono(ProductDto.class) //перобразуем тело запроса к дто
                //тут можно добавить фильтры
                .block();
    }

//Mono - ожидаем 1 объект
    //flux ожидаем пачку объектов


   /*
        public void clearUserCart(String username) {
        cartServiceWebClient.get()
                .uri("/api/v1/cart/0/clear")
                .header("username", username)
                .retrieve()
                .toBodilessEntity()//ответ без тела
                .block();//ожидание ответа
    }

    public CartDto getUserCart(String username) {
        CartDto cart = cartServiceWebClient.get()
                .uri("/api/v1/cart/0")
                .header("username", username)
                // .bodyValue(body) // for POST
                .retrieve()
                .onStatus(
                        httpStatus -> httpStatus.is4xxClientError(), // HttpStatus::is4xxClientError
                        clientResponse -> clientResponse.bodyToMono(CartServiceAppError.class).map(
                                body -> {
                                    if (body.getCode().equals(CartServiceAppError.CartServiceErrors.CART_NOT_FOUND.name())) {
                                        return new CartServiceIntegrationException("Выполнен некорректный запрос к сервису корзин: корзина не найдена");
                                    }
                                    if (body.getCode().equals(CartServiceAppError.CartServiceErrors.CART_IS_BROKEN.name())) {
                                        return new CartServiceIntegrationException("Выполнен некорректный запрос к сервису корзин: корзина сломана");
                                    }
                                    return new CartServiceIntegrationException("Выполнен некорректный запрос к сервису корзин: причина неизвестна");
                                }
                        )
                )
//                .onStatus(HttpStatus::is4xxClientError, clientResponse -> Mono.error(new CartServiceIntegrationException("Выполнен некорректный запрос к сервису корзин")))
//                .onStatus(HttpStatus::is5xxServerError, clientResponse -> Mono.error(new CartServiceIntegrationException("Сервис корзин сломался")))
                .bodyToMono(CartDto.class)
                .block();//ожидание ответа
        return cart;
    }
     */








}
