package ru.gb.springShop.core.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

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
    private BigDecimal price;

    @Column(name = "description")
    private String description;


    @CreationTimestamp //хибирнетовская антоация! автодобавление
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp//хибирнетовская антоация! автоапдейт
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

//
}
