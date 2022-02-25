package ru.gb.springShop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gb.springShop.entities.Order;
import ru.gb.springShop.entities.User;

import javax.xml.validation.Validator;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
  // Optional<Order> findByUsername(String username);


}
