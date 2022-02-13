package ru.gb.springShop.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
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


    @Column(name = "user_id")
    private Long userId;

    @Column(name = "product_id")
    private Long productId;


    @Column(name = "count")
    private int count;

    /*     todo убрать и сделать связь с таблицей продукты*/
    @Column(name = "product_name")
    private String productName;

    /*     todo убрать и сделать связь с таблицей продукты*/
  @Column(name = "cost")
    private Integer productCost;




//    //todo одна карзина содержит список товаров
//    @OneToMany(mappedBy = "cart")
//
//    private Set<Product> products = new HashSet<>();


    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", userId=" + userId +
                ", productId=" + productId +
                ", count=" + count +
                '}';
    }
}
