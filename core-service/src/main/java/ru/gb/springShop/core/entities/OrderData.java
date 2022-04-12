package ru.gb.springShop.core.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*объект который будет возвращаться на фронт, при создании запроса (адрес телефон)*/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderData {
    @Schema(description = "Адрес доставки заказа", required = true, example = "Irkutsk City, str")
    private String address;

    @Schema(description = "Телефон покупателя", required = true, example = "+7902223")
    private String phone;
}
