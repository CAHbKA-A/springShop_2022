package ru.gb.springShop.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//json  . ответка бэка фронту . сам токен
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponse {
    private String token;
}
