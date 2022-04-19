package ru.gb.springShop.auth.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.gb.springShop.api.*;
import ru.gb.springShop.auth.entities.User;
import ru.gb.springShop.auth.services.UserService;
import ru.gb.springShop.auth.utils.JwtTokenUtil;

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
    private final PasswordEncoder passwordEncoder;

    //урл авторизации. через метод пост json c логином и паролем
    @PostMapping("/auth")
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest) {
        try {
            log.info("/auth" + authRequest);
            //проверяем естьли такой юзер вообще
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        } catch (BadCredentialsException e) {
            //если нет такого юзера ош401

            return new ResponseEntity<>(new AppError(HttpStatus.UNAUTHORIZED.value(), "Incorrect username or password"), HttpStatus.UNAUTHORIZED);
        }
        //если ок идем дальше. выдергиваем юзера из БД (логин+список ролей)
        UserDetails userDetails = userService.loadUserByUsername(authRequest.getUsername());
        //  log.info(""+userDetails);
        //генерим токен
        String token = jwtTokenUtil.generateToken(userDetails);

        //  log.info(""+token);
        //отправляем на фронт
        return ResponseEntity.ok(new JwtResponse(token));
    }


    //пример проверки авторизацмм. (при обращениии на эндпоинт возвращает имя. Principal - это запись в security контаксте
    @GetMapping("/auth_check")
    public StringResponse authCheck(Principal principal) {
        log.info("check AuthController");
        return new StringResponse(principal.getName());
    }

    @PostMapping("/registration")
    public ResponseEntity<?> createAuthToken(@RequestBody RegistrationUserDto registrationUserDto) {
        if (!registrationUserDto.getPassword().equals(registrationUserDto.getConfirmPassword())) {
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "Пароли не совпадают"), HttpStatus.BAD_REQUEST);
        }
        if (userService.findByUsername(registrationUserDto.getUsername()).isPresent()) {
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "Пользователь с таким именем уже существует"), HttpStatus.BAD_REQUEST);
        }
        User user = new User();
        user.setEmail(registrationUserDto.getEmail());
        user.setUsername(registrationUserDto.getUsername());
        user.setPassword(passwordEncoder.encode(registrationUserDto.getPassword()));
        userService.createUser(user);

        UserDetails userDetails = userService.loadUserByUsername(registrationUserDto.getUsername());
        String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }
}
