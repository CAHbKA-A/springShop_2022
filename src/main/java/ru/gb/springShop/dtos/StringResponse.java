package ru.gb.springShop.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/*объект который будет возвращаться на фронт, при запросу статуса авторизации. содержит только строку с именем*/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StringResponse {
    private String value;
}
