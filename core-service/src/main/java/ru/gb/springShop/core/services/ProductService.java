package ru.gb.springShop.core.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.gb.springShop.api.ProductDto;
import ru.gb.springShop.core.entities.FilterData;
import ru.gb.springShop.core.entities.Product;
import ru.gb.springShop.core.repositories.ProductRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    public Product createNewProduct(ProductDto productDto) {
        Product product = new Product();
        product.setPrice(productDto.getPrice());
        product.setTitle(productDto.getTitle());
        //   Category category = categoryService.findByTitle(productDto.getCategoryTitle()).orElseThrow(() -> new ResourceNotFoundException("Category not found"));
        //  product.setCategory(category);
        productRepository.save(product);
        return product;

    }


    public List<Product> findByFilter(FilterData filterData) {
log.info(filterData.getMinPrice()+" " +filterData.getMaxPrice());


        if (filterData.getMinPrice()==null){
            filterData.setMinPrice(new BigDecimal(0));
        }



        return productRepository.findProductsByPriceBetween (filterData.getMinPrice(),filterData.getMaxPrice());


    }
}
