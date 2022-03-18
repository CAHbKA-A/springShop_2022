package ru.gb.springShop.carts.propierties;
/*конфиг веб клиента. пакуем все настройки в цельный бин*/
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConstructorBinding // поля будут заполняться через конструктор
@ConfigurationProperties(prefix = "integrations.product-service")
@Data
public class ProductServiceIntegrationProperties {
    private String url;
    private Integer connectTimeout; // в aplicayion.yaml  "тире" то же самое что camelCase connect-timeout =connectTimeout
    private Integer readTimeout;
    private Integer writeTimeout;
}
