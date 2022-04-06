package ru.gb.springShop.core.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.gb.springShop.api.AppError;
import ru.gb.springShop.api.ProductDto;
import ru.gb.springShop.api.ResourceNotFoundException;
import ru.gb.springShop.core.convertors.ProductConverter;
import ru.gb.springShop.core.entities.Product;
import ru.gb.springShop.core.services.ProductService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
@Tag(name = "Продукты", description = "Методы работы с продуктами") //для swagger
public class ProductController {
    //Подключаем сервисы (финал -в обяз)
    private final ProductService productService;
    private final ProductConverter productConverter;


    @Operation(
            summary = "Запрос на получение отфильтрованного списка продуктов",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = ProductDto.class))
                    )
            }
    )

    //вытягивание всего списка
    @GetMapping
    public List<ProductDto> findProducts(
            @RequestParam(required = false, name = "min_price") Integer minPrice,
            @RequestParam(required = false, name = "max_price") Integer maxPrice,
            @RequestParam(required = false, name = "title") String title,
            @RequestParam(defaultValue = "1", name = "p") Integer page
    )

    {// currentPage=currentPage+page;
//        if (page < 1) {
//            page = 1;
//        }

        Specification<Product> spec = productService.createSpecByFilters(minPrice, maxPrice, title);
        //получаем с  страницы н-1( с нуля), и мапим из пейджа в дто и преобразуем в лист
        return productService.findAll(spec, page - 1).map(productConverter::entityToDto).getContent();
    }

    @Operation(
            summary = "Запрос на получение продукта по id",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = ProductDto.class))
                    )
                    ,
                    @ApiResponse(
                            description = "Продукт не найден", responseCode = "404",
                            content = @Content(schema = @Schema(implementation = AppError.class))
                    )
            }
    )
    @GetMapping("/{id}")
    public ProductDto findProductById(@PathVariable @Parameter(description = "Идентификатор продукта", required = true)  Long id) {
        Product p = productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Продукт не найден, id: " + id));
        return new ProductDto(p.getId(), p.getTitle(), p.getPrice());
    }


    //удаляем объект по id
    @DeleteMapping("/{id}")
    void deleteProductById(@PathVariable Long id) {
        productService.deleteById(id);
    }

//
//    @PostMapping("/filter")
//    @ResponseStatus(HttpStatus.CREATED)
//    public  List<ProductDto>  filter(@RequestBody FilterData filterData) {
//
//        //log.info(" "+filterData.getMinPrice()+filterData.getMaxPrice()+filterData.getTextSearch());
//        return productService.findByFilter(filterData).stream().map(p -> new ProductDto(p.getId(), p.getTitle(), p.getPrice())).collect(Collectors.toList());
//
//    }

    @Operation(
            summary = "Запрос на создание нового продукта",
            responses = {
                    @ApiResponse(
                            description = "Продукт успешно создан", responseCode = "201",
                            content = @Content(schema = @Schema(implementation = ProductDto.class))
                    )
            }
    )
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDto createNewProduct(@RequestBody ProductDto productDto) {
        Product p = productService.createNewProduct(productDto);
        return productConverter.entityToDto(p);
    }

}
