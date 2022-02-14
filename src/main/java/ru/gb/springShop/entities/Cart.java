package ru.gb.springShop.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity
@Data
@Table(name = "carts")
@NoArgsConstructor
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cart")
    private Long id;

    //владелец корзины
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "date_expiration")
    private Long dateExpiration;


    /*      todo убрать и сделать связь с таблицей продукты*/
    @Column(name = "product_id")
    private Long productId;

    /*      todo убрать и сделать связь с таблицей продукты*/
    @Column(name = "count")
    private int count;

    /*     todo убрать и сделать связь с таблицей продукты*/
    @Column(name = "product_name")
    private String productName;

    /*     todo убрать и сделать связь с таблицей продукты*/
    @Column(name = "cost")
    private Integer productCost;


    /*1 корзина может содержать несколько продутов*/
    /*один продукт может быть в нескольких корзинах*/
    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "cart_to_product",
            joinColumns = @JoinColumn(name = "id_cart"),
            inverseJoinColumns = @JoinColumn(name = "id_product")
    )
    private Set<Product> products;


    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", userId=" + userId +
                ", products=" + products +
                '}';
    }
}
