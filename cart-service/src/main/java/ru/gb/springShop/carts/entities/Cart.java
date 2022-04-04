package ru.gb.springShop.carts.entities;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import ru.gb.springShop.api.ProductDto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
@Data
public class Cart {
    private List<CartItem> items;
    private BigDecimal totalPrice;


    public Cart() {
        this.items = new ArrayList<>();
    }


    //Возврщает содержимое корзины, не на дает ее изменять(unmodifiableList).
 //   public List<CartItem> getItems() {        return Collections.unmodifiableList(items);
 //   }


    public void add(ProductDto product) {
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




    public void addItem(CartItem cartItem) {
//        for (CartItem item : items) {
//            if (item.equals(item.getProductId())) {
//                item.changeQuantity(item.getQuantity() + 1);
//                recalculate();
//                return;
//            }
//        }
        items.add(cartItem);
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
        totalPrice = BigDecimal.ZERO;
        for (CartItem item : items) {
            totalPrice = totalPrice.add(item.getPrice());
        }

    }


    public void clear() {
        items.clear();
        totalPrice = BigDecimal.ZERO;
    }


}
