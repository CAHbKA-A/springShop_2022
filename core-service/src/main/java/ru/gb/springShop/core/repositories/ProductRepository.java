package ru.gb.springShop.core.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gb.springShop.core.entities.Product;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {


    public List <Product> findAllByPriceGreaterThanEqualAndTitleContains(BigDecimal min, String title);


    public List <Product> findAllByPriceGreaterThanEqualAndPriceLessThanEqualAndTitleContains(BigDecimal min,BigDecimal max, String title);




}
