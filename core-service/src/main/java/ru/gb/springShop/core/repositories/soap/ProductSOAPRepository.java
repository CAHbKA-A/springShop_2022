package ru.gb.springShop.core.repositories.soap;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.gb.springShop.core.entities.Product;

import java.util.Optional;

@Repository
public interface ProductSOAPRepository extends JpaRepository<Product, Long> {
    @Query("select s from Product s where s.title = ?1")
    Optional<Product> findByName(String name);
}
