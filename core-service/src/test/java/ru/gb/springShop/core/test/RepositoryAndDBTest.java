package ru.gb.springShop.core.test;

/*тестим репозиторий+ аботу с БД.  все тесты выполняются в транзакции, после теста записи в БД откатываются!*/
/*в идеале делать на тестовой бд h2, азверну в памяти на время тестов:
в application-test.yaml описываем новую бд+путь к файлу мигрции, и flyFay'ем заполняем
*/

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import ru.gb.springShop.core.entities.Product;
import ru.gb.springShop.core.repositories.ProductRepository;

import java.math.BigDecimal;
import java.util.List;

@DataJpaTest
@ActiveProfiles("forTest") //профиль для flyway  брать в application-forTest.yaml. бд на время теста
public class RepositoryAndDBTest {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private TestEntityManager entityManager; //тестер для репозитрия(бд)

    @Test
    public void ProductRepositoryTest() { //проеверяем, что при добавлении записи список продуктов увеличился на 1


//запоминаем сколько записей было
        List<Product> productList = productRepository.findAll();
        int listSize = productList.size();
        //создаем то, что будем писать в БД
        Product product = new Product();
        product.setPrice(BigDecimal.valueOf(12));
        product.setTitle("testАбраКадабра");


        entityManager.persist(product);
        entityManager.flush();

        //проверим, что записи увеличились на 1
        Assertions.assertEquals(listSize++, productList.size());


        productList = productRepository.findAllByPriceGreaterThanEqualAndTitleContains(BigDecimal.valueOf(0), "testАбраКадабра");
        Assertions.assertEquals(1, productList.size());
        //    Assertions.assertEquals("test", productList.get(1).getTitle());

    }


}
