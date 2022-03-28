package ru.gb.springShop.core.test;
/*полный запуск сервиса на рандомном порту. выгружаем json , проверяем, что не пустой и не null
Проверка что он вообще поднялся и принимает запросы, выдает данные
Можем затестить все url и меторды контроллера*/

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ActiveProfiles;
import ru.gb.springShop.core.entities.Product;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("ProfileForAllServiceRun")
public class ServiceRunTest {
    @Autowired
    private TestRestTemplate restTemplate; //шаблон подзволяет отправлять запросы в api

    @Test
    public void fullRestTest() {
         List<Product> genres = restTemplate.getForObject("/api/v1/products", List.class); //рест запрос кладем в лист
        assertThat(genres).isNotNull();
        assertThat(genres).isNotEmpty();
    }
}
