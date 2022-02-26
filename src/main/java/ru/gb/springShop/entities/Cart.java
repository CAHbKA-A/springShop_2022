package ru.gb.springShop.entities;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
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


    public void add(Product product) {
        for (CartItem item : items) {
            if (product.getId().equals(item.getProductId())) {
                item.changeQuantity(item.getQuantity() + 1);
                recalculate();
                return;
            }
        }
        items.add(new CartItem(product.getId(), product.getTitle(), 1, product.getPrice(), product.getPrice()));
        recalculate();
    }


    public void remove(Long productId) {
        if (items.removeIf(item -> item.getProductId().equals(productId))) {
            recalculate();
        }
    }


    public void setCount(Long id, Integer count) {
//не удаляю из корзины при счетчике =0, если покупаль передумает или случайно пригонит к нулу, то не придется снова искать продукт в каталоге.
//в оформлении заказа нулевые будем игнорировать.
        for (CartItem item : items) {
            if (id.equals(item.getProductId())) {
                item.changeQuantity(count);
                recalculate();
                return;
            }
        }
    }

    //новый пересчет корзины
    private void recalculate() {
        totalPrice = 0;
        for (CartItem item : items) {
            totalPrice += item.getPrice();
        }

    }


    public void clear() {
        items.clear();
        totalPrice = 0;
    }


}
