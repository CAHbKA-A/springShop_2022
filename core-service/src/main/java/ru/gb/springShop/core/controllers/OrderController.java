package ru.gb.springShop.core.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.gb.springShop.api.AppError;
import ru.gb.springShop.api.OrderDto;
import ru.gb.springShop.api.ProductDto;
import ru.gb.springShop.api.ResourceNotFoundException;
import ru.gb.springShop.core.convertors.OrderConvertor;
import ru.gb.springShop.core.entities.Order;
import ru.gb.springShop.core.entities.OrderData;
import ru.gb.springShop.core.services.OrderService;
import java.util.List;

@Slf4j
@RestController
@Tag(name = "Заказы", description = "Методы работы с заказами") //для swagger
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final OrderConvertor orderConvertor;

    @Operation(
            summary = "Запрос на создание заказа",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = ProductDto.class))
                    ),
                    @ApiResponse(
                    description = "Недостающие данные", responseCode = "201",
                    content = @Content(schema = @Schema(implementation = AppError.class))
            )
            }
    )
    //если прилетел post,создаем заказ
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder(@RequestHeader String username , @RequestBody OrderData orderData ) {

        orderService.createOrder(username, orderData);
    }
    @Operation(
            summary = "Запрос на получение заказа по id",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = OrderDto.class))
                    ),
                    @ApiResponse(
                            description = "Заказ не найден", responseCode = "404",
                            content = @Content(schema = @Schema(implementation = AppError.class))
                    )
            }
    )
    @GetMapping("/{id}")
    public OrderDto findOrderById(@PathVariable @Parameter(description = "Идентификатор продукта", required = true)  Long id) {
        Order o = orderService.findById(id).orElseThrow(() -> new ResourceNotFoundException("заказ не найден, id: " + id));
        return orderConvertor.entityToDto(o);
    }

    @Operation(
            summary = "Запрос на получение списка заказов",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = List.class))
                    )
            }
    )
    @GetMapping
    public List<OrderDto> findAllOrders() {

        return orderConvertor.listToDto(orderService.findAllOrders());

    }

}
