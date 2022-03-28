package ru.gb.springShop.core.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.gb.springShop.api.ProductDto;
import ru.gb.springShop.core.entities.Product;
import ru.gb.springShop.core.repositories.ProductRepository;
import ru.gb.springShop.core.repositories.specifications.ProductsSpecifications;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Page<Product> findAll(Specification<Product> spec, int page) {
        return productRepository.findAll(spec, PageRequest.of(page, 5));
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

//    //todo ignoreCase
//    public List<Product> findByFilter(FilterData filterData) {
//
//        BigDecimal minPrice = filterData.getMinPrice();
//        BigDecimal maxPrice = filterData.getMaxPrice();
//        String textForSearch = filterData.getTextSearch();
//        if (minPrice == null) minPrice = BigDecimal.ZERO;
//        if (maxPrice == null ) maxPrice = BigDecimal.ZERO;
//        if (textForSearch == null) textForSearch = "~*";
//        if (maxPrice.equals(BigDecimal.ZERO))
//            return productRepository.findAllByPriceGreaterThanEqualAndTitleContains(minPrice, textForSearch);
//
//
//        return productRepository.findAllByPriceGreaterThanEqualAndPriceLessThanEqualAndTitleContains(minPrice, maxPrice, textForSearch);
//
//
//    }

    public Specification<Product> createSpecByFilters(Integer minPrice, Integer maxPrice, String title) {
        Specification<Product> spec = Specification.where(null);
        if (minPrice != null) {
            spec = spec.and(ProductsSpecifications.priceGreaterOrEqualsThan(minPrice));
        }
        if (maxPrice != null) {
            spec = spec.and(ProductsSpecifications.priceLessThanOrEqualsThan(maxPrice));
        }
        if (title != null) {
            spec = spec.and(ProductsSpecifications.titleLike(title));
        }
        return spec;
    }
}
