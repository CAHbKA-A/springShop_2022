package ru.gb.springShop.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
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




    /*1 корзина может содержать несколько продуkтов*/
    /*один продукт может быть в нескольких корзинах*/
    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "cart_to_product",
            joinColumns = @JoinColumn(name = "id_cart"),
            inverseJoinColumns = @JoinColumn(name = "id_product")
    )
    private List<Product> products;


    @Override
    public String toString() {
        return "Cart{" +
                "userId=" + userId +

                ", products=" + products +
                '}';
    }
}
