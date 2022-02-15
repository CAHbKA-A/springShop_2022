package ru.gb.springShop.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


//обработка json токенов. генерация и парсинг
@Component
public class JwtTokenUtil {

    //из конфига серкетный ключ. есть только на стороне сервера
    @Value("${jwt.secret}")
    private String secret;
    // время жизни токена. из конфига.
    @Value("${jwt.lifetime}")
    private Integer jwtLifetime;

    //генерим токен на основе userDetails. можем наидывать любую инфу, но токен будет раздуваться
    public String generateToken(UserDetails userDetails) {
        //данные которые дополнительно зашиваем в токен (список ролей
        Map<String, Object> claims = new HashMap<>();
        //выдергиваем роли, и запихиваем в list<string>
        List<String> rolesList = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        //укаладываем это в клеймсы. с ключем "roles" будт лежать роли пользователя
        claims.put("roles", rolesList);
        //дата создания
        Date issuedDate = new Date();
        //время истечения токена
        Date expiredDate = new Date(issuedDate.getTime() + jwtLifetime);
        //формируем токен из вышесобранного
        return Jwts.builder()
                //содердимое
                .setClaims(claims)
                //тема токена = имя пользователя
                .setSubject(userDetails.getUsername())
                //время выпуска токена
                .setIssuedAt(issuedDate)
                //варемя истечения токена
                .setExpiration(expiredDate)
                //подписать алгоритмом с исользованием ключа
                .signWith(SignatureAlgorithm.HS256, secret)
                //сбор
                .compact();
    }


    /*     разбор токена*/

//выдергиваем имя юзера , оно же  тема токена.  из клаймсов берем тему

    public String getUsernameFromToken(String token) {
        return getAllClaimsFromToken(token).getSubject();
    }

    //выдергиваем роли. из клеймсов выдираем лис по ключу "роли"
    public List<String> getRoles(String token) {
        return getAllClaimsFromToken(token).get("roles", List.class);
    }


    //выдергиваем из токена клеймсы(полезную инфу)
    private Claims getAllClaimsFromToken(String token) {
        //создаем парсер, указываем ключ, сам токен. проверяется время жизни токена и подпись
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }
}
