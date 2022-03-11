package ru.gb.springShop.core.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.gb.springShop.api.OrderDto;
import ru.gb.springShop.api.ResourceNotFoundException;
import ru.gb.springShop.core.convertors.OrderConvertor;
import ru.gb.springShop.core.entities.Order;
import ru.gb.springShop.core.services.OrderService;
import java.util.List;

@Slf4j
@RestController

@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final OrderConvertor orderConvertor;


    //если прилетел post,создаем заказ
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder(@RequestHeader String username /*, @RequestBody OrderData orderData */) {
        orderService.createOrder(username);
    }

    @GetMapping("/{id}")
    public OrderDto findOrderById(@PathVariable Long id) {
        Order o = orderService.findById(id).orElseThrow(() -> new ResourceNotFoundException("заказ не найден, id: " + id));
        return orderConvertor.entityToDto(o);
    }


    @GetMapping
    public List<OrderDto> findAllProducts() {
        return orderConvertor.listToDto(orderService.findAllOrders());

    }

}
