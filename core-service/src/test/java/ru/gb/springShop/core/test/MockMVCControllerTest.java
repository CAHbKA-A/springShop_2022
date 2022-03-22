package ru.gb.springShop.core.test;
/*тест сервиса с залгушкой фронта. Приложение поднимается,  апач не запускается . запросы перенапраляются в нужные контроллеры.
Эмуляция бэка. Работает быстрее.

 Тестом проверяем что получили по запросу и сколько*/

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.gb.springShop.core.entities.Product;
import ru.gb.springShop.core.repositories.ProductRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.CoreMatchers.is;

@SpringBootTest
@AutoConfigureMockMvc //для MVC заглушки
public class MockMVCControllerTest {
    @Autowired
    private MockMvc mvc; //заглушка фронта.

    @MockBean
    private ProductRepository productRepository;//чтобы не трогать БД

    @Test

    public void getAllProductsTest() throws Exception {
        //создаем продукт. Будем подменять запросы к бд
        Product product = new Product();
        product.setPrice(BigDecimal.valueOf(10));
        product.setTitle("Tester");
        product.setId(123L);

        List<Product> allProducts = new ArrayList<>(Arrays.asList(
                product
        ));
        given(productRepository.findAll()).willReturn(allProducts); //когда обращаемся к бд, возвращаем список созданный выше


        /*"Эмулируем отправку GET запроса*/
        mvc
                .perform(
                        get("/api/v1/products")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print()) //выводим в консоль после выполнения
                .andExpect(status().isOk()) //проверяем, что прилетел статус ОК = assert/ можем проверять любые статусы
                .andExpect(jsonPath("$").isArray())//проверяем, что это массиа
                .andExpect(jsonPath("$", hasSize(1))) //проверяем размер массива
                .andExpect(jsonPath("$[0].title", is(allProducts.get(0).getTitle()))); //выдергиваем из нулевого элемента полуеченного массива поле title. сравниваем с моком
    }
}/*jsonPath("$")  библиотек создает парсер для преобразованиии json в удобоваримый объект.  $(корнеевой объект) - то,я то пришло */
