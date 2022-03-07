package ru.gb.springShop.core.convertors;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.gb.springShop.api.ProductDto;
import ru.gb.springShop.core.entities.Product;


@Component
@RequiredArgsConstructor
public class ProductConverter {
  //  private final CategoryService categoryService;

    public ProductDto entityToDto(Product product) {
        return new ProductDto(product.getId(), product.getTitle(), product.getPrice()/*,product.getCategory().getTitle()*/);
    }

    public Product dtoToEntity(ProductDto productDto) {
        Product p = new Product();
        p.setId(productDto.getId());
        p.setTitle(productDto.getTitle());
        p.setPrice(productDto.getPrice());
     //   Category c = categoryService.findByTitle(productDto.getCategoryTitle()).orElseThrow(() -> new ResourceNotFoundException("Категория не найдена"));
    //    p.setCategory(c);
        return p;
    }
}
