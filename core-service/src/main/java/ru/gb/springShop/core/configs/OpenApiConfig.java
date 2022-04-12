package ru.gb.springShop.core.configs;
//конфиг для swgger
//http://localhost:8189/shop-core/swagger-ui/index.html
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI api() {
        return new OpenAPI()
                .info(
                        new Info()
                                .title("GB Market - Продуктовый сервис(Core Service)")
                                .version("1")
                );
    }
}
