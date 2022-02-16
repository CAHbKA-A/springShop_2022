package ru.gb.springShop.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.springShop.entities.Product;
import ru.gb.springShop.exceptions.AppError;
import ru.gb.springShop.exceptions.ResourceNotFoundException;
import ru.gb.springShop.services.CartService;
import ru.gb.springShop.services.ProductService;

import java.util.List;


@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor

public class ProductController {
    //Подключаем сервисы (финал -в обяз)
    private final ProductService productService;
    private final CartService cartService;


    //вытягивание всего списка
    @GetMapping
    public List<Product> findAllProducts() {
        return productService.findAll();
    }





    /*объемный вариант*/
    //выдергиваем  объект по id
/*      @GetMapping("/{id}")
    //возварщать будем респонс записль либо с ошибкой , либо с продуктом
  public ResponseEntity<?> findProductById(@PathVariable Long id) {
        //обработка ошибок
        Optional<Product> product = productService.findById(id);
        //Если не найден продукт
        if (!product.isPresent()) {
            //ответка от апи в сторону js.  упаковываем в json
            // HttpStatus - это enum, который содержит все статусы
            ResponseEntity<AppError> error = new ResponseEntity<>(new AppError(HttpStatus.NOT_FOUND.value(), "Продукт не найден, id: " + id), HttpStatus.NOT_FOUND);//последний аргумент - стаким статускодом прилетит ответ(поимо json'a
            return error;
        }
        return new ResponseEntity<>(product.get(),HttpStatus.OK);
    }
*/
    /*вариант поменьше*/

    @GetMapping("/{id}")
    public Product findProductById(@PathVariable Long id) {

        return productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Продукт не найден, id: " + id));//orElseThrow()-вернет продукт ,е сли есть, а если нет, исключение
    }

    // создаем метод, перехватывающий исключения
    @ExceptionHandler //чтобы создался этот эксепшн хендлен и перехватывал исключения
    public ResponseEntity<AppError> exeptionHandler(ResourceNotFoundException e) {
        //если поймали исключение
        return new ResponseEntity<>(new AppError(HttpStatus.NOT_FOUND.value(), e.getMessage()), HttpStatus.NOT_FOUND);
    }


    //удаляем объект по id
    @DeleteMapping("/{id}")
    void deleteProductById(@PathVariable Long id) {
        productService.deleteById(id);
    }


}
