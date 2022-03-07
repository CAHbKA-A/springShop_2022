package ru.gb.springShop.core.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ru.gb.springShop.api.JwtRequest;
import ru.gb.springShop.api.JwtResponse;
import ru.gb.springShop.api.StringResponse;
import ru.gb.springShop.core.utils.JwtTokenUtil;

import ru.gb.springShop.core.services.UserService;

import java.security.Principal;

//контроллер аутентификации
@Slf4j
@RestController
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final JwtTokenUtil jwtTokenUtil;
    //бин спринга. сверяет токен с юзером(есть/нет вообще )
    private final AuthenticationManager authenticationManager;

    //урл авторизации. через метод пост json c логином и паролем
    @PostMapping("/auth")
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest) {
        try {
            //проверяем естьли такой юзер вообще
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        } catch (BadCredentialsException e) {
            //если нет такого юзера ош401

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        //если ок идем дальше. выдергиваем юзера из БД (логин+список ролей)
        UserDetails userDetails = userService.loadUserByUsername(authRequest.getUsername());
        //генерим токен
        String token = jwtTokenUtil.generateToken(userDetails);
        //отправляем на фронт
        return ResponseEntity.ok(new JwtResponse(token));
    }



//пример проверки авторизацмм. (при обращениии на эндпоинт возвращает имя. Principal - это запись в security контаксте
    @GetMapping("/auth_check")
    public StringResponse authCheck(Principal principal) {
        log.info("check AuthController");
        return new StringResponse(principal.getName());
    }
}
