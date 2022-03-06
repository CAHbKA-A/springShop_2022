package ru.gb.springShop.carts.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
//элемент корзины
public class CartItem {

    private Long productId;
    private String productTitle;
    private int quantity;//количество
    private int pricePerProduct;
    private int price;

    public void changeQuantity(int count) {
        quantity = count;
        price = pricePerProduct * quantity;
    }
}
