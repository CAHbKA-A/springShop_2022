package ru.gb.springShop.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gb.springShop.entities.Cart;



@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
}
