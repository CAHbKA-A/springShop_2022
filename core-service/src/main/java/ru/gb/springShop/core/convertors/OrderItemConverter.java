package ru.gb.springShop.core.convertors;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.gb.springShop.api.OrderItemDto;
import ru.gb.springShop.core.entities.OrderItem;
import ru.gb.springShop.core.services.OrderService;


@Component
@RequiredArgsConstructor
public class OrderItemConverter {
    private final OrderService orderService;
    //  private final OrderConvertor orderConvertor;


    public OrderItemDto entityToDto(OrderItem orderItem) {
        return new OrderItemDto(orderItem.getId(),
                orderItem.getProduct().getTitle(),
                orderItem.getQuantity(),
                orderItem.getPricePerProduct(),
                orderItem.getPrice());
    }

    public OrderItem dtoToEntity(OrderItemDto orderItemDto) {
        OrderItem oi = new OrderItem();
        oi.setId(orderItemDto.getId());
        oi.setQuantity(orderItemDto.getQuantity());
        oi.setPrice(orderItemDto.getPrice());
        return oi;
    }
}
