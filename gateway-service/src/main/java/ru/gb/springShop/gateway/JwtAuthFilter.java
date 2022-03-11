package ru.gb.springShop.gateway;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;


//преобразуем токен в хедер имя+роль. будем дальше прокидыватьв таком виде


//AbstractGatewayFilterFactory<JwtAuthFilter.Config>  - чтобы можно было запихивать в yaml
@Component
public class JwtAuthFilter extends AbstractGatewayFilterFactory<JwtAuthFilter.Config> {
    @Autowired
    private JwtUtil jwtUtil;

    public JwtAuthFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            //перехватываем запрос
            ServerHttpRequest request = exchange.getRequest();
            //если в заоголовке есть авторизейштн хедер
            if (!isAuthMissing(request)) {
                //выдерунли хедер авторизейшн
                final String token = getAuthHeader(request);
                if (jwtUtil.isInvalid(token)) {//проверяем валидность токена
                    return this.onError(exchange, "Authorization header is invalid", HttpStatus.UNAUTHORIZED);
                }
                populateRequestWithHeaders(exchange, token);
            }
            return chain.filter(exchange);
        };
    }

    public static class Config {
    }

    private Mono<Void> onError(ServerWebExchange exchange, String err, HttpStatus httpStatus) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpStatus);
        return response.setComplete();
    }

    private String getAuthHeader(ServerHttpRequest request) {
        return request.getHeaders().getOrEmpty("Authorization").get(0).substring(7);
    }

    private boolean isAuthMissing(ServerHttpRequest request) {
        if (!request.getHeaders().containsKey("Authorization")) {
            return true;
        }
        if (!request.getHeaders().getOrEmpty("Authorization").get(0).startsWith("Bearer ")) {
            return true;
        }
        return false;
    }

    //получить запрос и токен и преобразвать в юзернейм
    private void populateRequestWithHeaders(ServerWebExchange exchange, String token) {
        //выдергиваем клеймсы
        Claims claims = jwtUtil.getAllClaimsFromToken(token);
        //корректируем
        exchange.getRequest().mutate()
                .header("username", claims.getSubject())
//                .header("role", String.valueOf(claims.get("role")))
                .build();
    }
}
