package ru.gb.springShop.api;



import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.LocalDateTime;


public class OrderDto {
    @Schema(description = "Идентификатор заказа", required = true, example = "1")
    private Long id;

    @Schema(description = "Логин владелельца заказа", required = true, example = "Bob")
    private String username;

    @Schema(description = "Итоговая стоимость заказа", required = false, example = "25.00")
    private BigDecimal totalPrice;

    @Schema(description = "Время сздания заказа", required = false, example = "2022-04-07T21:39:58.693687")
    private LocalDateTime createdAt;

    @Schema(description = "статус заказа", required = false, example = "оплачен")
    private String state;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public BigDecimal  getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal  totalPrice) {
        this.totalPrice = totalPrice;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

}
