package ru.gb.springShop.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gb.springShop.entities.Cart;
import ru.gb.springShop.entities.User;

import java.util.Optional;


@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findFirstByUserId(Long userId);


}
