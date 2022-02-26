package ru.gb.springShop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gb.springShop.entities.Order;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
  // Optional<Order> findByUsername(String username);
  Optional<Order> findByUserId(Long id);
  Optional<Order> findById(Long id) ;





}
