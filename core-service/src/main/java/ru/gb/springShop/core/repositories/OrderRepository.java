package ru.gb.springShop.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gb.springShop.core.entities.Order;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
  // Optional<Order> findByUsername(String username);
  Optional<Order> findByUserId(Long id);
  Optional<Order> findById(Long id) ;





}
