package ru.gb.springShop.core.services.soap;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.gb.springShop.core.entities.Product;
import ru.gb.springShop.core.repositories.soap.ProductSOAPRepository;
import ru.gb.springShop.core.soap.products.ProductSOAP;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
@Slf4j
@Service
@RequiredArgsConstructor
public class ProductSoapService {
    private final ProductSOAPRepository productSOAPRepository;

    public static final Function<Product, ProductSOAP> functionEntityToSoap = se -> {
        ProductSOAP s = new ProductSOAP();
        s.setId(se.getId());
        s.setTitle(se.getTitle());
        s.setPrice(se.getPrice());
        s.setDescription(se.getDescription());
        return s;
    };

    public List<ProductSOAP> getAllProducts() {
        log.info("!!!ProductSoapService!!!");
        return productSOAPRepository.findAll().stream().map(functionEntityToSoap).collect(Collectors.toList());
    }

    public ProductSOAP getByName(Long id) {
        return productSOAPRepository.findById(id).map(functionEntityToSoap).get();
    }
}
