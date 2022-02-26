package ru.gb.springShop.convertors;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.gb.springShop.dtos.OrderDto;
import ru.gb.springShop.entities.Order;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderConvertor {
    private final OrderItemConverter orderItemConverter;

    public OrderDto entityToDto(Order order) {
        OrderDto o = new OrderDto();
        o.setId(order.getId());
        o.setUsername(order.getUser().getUsername());
        o.setTotalPrice(order.getTotalPrice());
        o.setOrderItems(order.getItems().stream().map(orderItemConverter::entityToDto).collect(Collectors.toList()));
        return o;
    }
}
