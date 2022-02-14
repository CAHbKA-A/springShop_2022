package ru.gb.springShop.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Table(name = "products")
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


    @Column(name = "title")
    private String title;


    @Column(name = "price")
    private int price;

    @Column(name = "description")
    private String description;




    @JsonIgnore // чтобы не зацикливался при преобразовании в json
    @ManyToMany
    @JoinTable(
            name = "cart_to_product",
            joinColumns = @JoinColumn(name = "id_cart"),
            inverseJoinColumns = @JoinColumn(name = "id_product")
    )
    private Set<Product> cart;


}
