package ru.gb.springShop.carts.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
//элемент корзины
public class CartItem {

    private Long productId;
    private String productTitle;
    private int quantity;//количество
    private BigDecimal pricePerProduct;
    private BigDecimal price;

    public void changeQuantity(int count) {
        quantity = count;
        price = pricePerProduct .multiply (BigDecimal.valueOf(quantity));
    }
}
