package ru.gb.springShop.core.convertors;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.gb.springShop.api.OrderDto;
import ru.gb.springShop.core.entities.Order;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class OrderConvertor {
    private final OrderItemConverter orderItemConverter;

    public OrderDto entityToDto(Order order) {
        OrderDto o = new OrderDto();
        o.setId(order.getId());
     //  o.setUsername(order.getUser().getUsername());
        o.setTotalPrice(order.getTotalPrice());
        o.setState(order.getState());
      //  o.setOrderItems(order.getItems().stream().map(orderItemConverter::entityToDto).collect(Collectors.toList()));
        return o;
    }

    public List<OrderDto> listToDto(List<Order> orders) {
        List<OrderDto> o = new ArrayList<>();
        for (Order order : orders) {

            o.add(entityToDto(order));
        }
        return o;
    }
}
