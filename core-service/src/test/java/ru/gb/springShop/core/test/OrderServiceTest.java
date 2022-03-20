package ru.gb.springShop.core.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.gb.springShop.api.CartDto;
import ru.gb.springShop.api.CartItemDto;
import ru.gb.springShop.core.entities.Order;
import ru.gb.springShop.core.entities.OrderData;
import ru.gb.springShop.core.entities.Product;
import ru.gb.springShop.core.intergrations.CartServiceIntegration;
import ru.gb.springShop.core.repositories.OrderRepository;
import ru.gb.springShop.core.services.OrderService;
import ru.gb.springShop.core.services.ProductService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@SpringBootTest
public class OrderServiceTest {
    @Autowired //инжектим это сервис
    private OrderService orderService;

    @MockBean//заглушка, чтобы тетст не лез в картсервис
    private CartServiceIntegration cartServiceIntegration;

    @MockBean
    private ProductService productService;

    @MockBean
    private OrderRepository orderRepository;//заглушка, чтобы в БД ничего не писалось

    @Test
    public void createOrderTest() {

        //создаем тестовую корзину
        CartDto cartDto = new CartDto();
        CartItemDto cartItemDto = new CartItemDto();
        cartItemDto.setProductTitle("Tester");
        cartItemDto.setPricePerProduct(BigDecimal.valueOf(100));

        cartItemDto.setQuantity(2);
        cartItemDto.setPrice(BigDecimal.valueOf(200));
        cartItemDto.setProductId(12222L);
        cartDto.setItems(List.of(cartItemDto));
        cartDto.setTotalPrice(BigDecimal.valueOf(200));
        log.info("!"+cartDto.getTotalPrice());
        //создаем загрушку для cartServiceIntegration/ подмена возвращаемого значения/ при вызове cartServiceIntegration.getCurrentCart() возвращается cartDto
        Mockito.doReturn(cartDto).when(cartServiceIntegration).getCurrentCart();

        //заглушка для продуксСервис.с учетом
        Product product = new Product();
        product.setPrice(BigDecimal.valueOf(100));
        product.setTitle("Tester");
        product.setId(12222L);
        //подменяем
        Mockito.doReturn(Optional.of(product)).when(productService).findById(12222L);

        Order order = orderService.createOrder("bob", new OrderData("5443", "23432"));

        Assertions.assertEquals(order.getTotalPrice(), BigDecimal.valueOf(200));
    }
}
