package ru.gb.springShop.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*объект который будет возвращаться на фронт, при создании запроса (адрес телефон)*/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderData {
    private String address;
    private String phone;
}
