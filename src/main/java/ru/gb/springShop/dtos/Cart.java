package ru.gb.springShop.dtos;

import lombok.Data;
import ru.gb.springShop.entities.Product;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
public class Cart {
    private List<CartItem> items;
    private int totalPrice;

    public Cart() {
        this.items = new ArrayList<>();
    }

    //Возврщает содержимое корзины, не на дает ее изменять(unmodifiableList).
    public List<CartItem> getItems() {
        return Collections.unmodifiableList(items);
    }


    //todo проверка добаленного продукта. пересчет количества и стоимости
    public void add(Product product) {

        items.add(new CartItem(product.getId(), product.getTitle(), 1, product.getPrice(), product.getPrice()));
        recalculate();
    }


    //новый пересчет корзины
    private void recalculate() {
        totalPrice = 0;
        for (CartItem item : items) {
            totalPrice += item.getPrice();
        }
    }
}
