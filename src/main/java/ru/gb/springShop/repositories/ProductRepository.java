package ru.gb.springShop.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gb.springShop.entities.Product;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
