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
        CartItem addingItem = new CartItem(product.getId(), product.getTitle(), 1, product.getPrice(), product.getPrice());
        //что лучше переопределит хеш и equals и по нему найи индекс или тупо перебором?
        //если корзина небольшая, то проще перебором. ну а я замахнулся на корзину в милион товаров)).
        //перебором мне не понравилось. не красиво выглядит код.


        int index = items.indexOf(addingItem);

        if (index != -1) {
            items.get(index).setQuantity(items.get(index).getQuantity() + 1);
            //цену берем из product, на случай если за время существования корзины измениалсь цена в БД. покупать надо по актульной цене.
            //         items.get(index).setPricePerProduct(product.getPrice() );
            items.get(index).setPrice(product.getPrice() * ((items.get(index).getQuantity())));
        } else {
            items.add(addingItem);
        }
        recalculate();
    }


    public void deleteItemFromCart(Long id) {
        items.remove(FindItemByProductID(id));
        recalculate();
    }


    public void setCount(Long id, int count) {
        CartItem tmp = FindItemByProductID(id);
        tmp.setQuantity(count);
        recalculate();
    }


    //новый пересчет корзины
    private void recalculate() {
        totalPrice = 0;
        for (CartItem item : items) {
            item.setPrice(item.getPricePerProduct() * ((item.getQuantity())));
            totalPrice += item.getPrice();


        }
    }

    private CartItem FindItemByProductID(Long id) {
        for (CartItem item : items) {
            if (item.getProductId() == id)
                return item;
        }
        //пагубная практика -возвращать null, но пока не придумал решения
        return null;
    }
//
//    private CartItem FindItemByProduct(Product product) {
//        return items.get(items.indexOf(product));
//
//    }

}
