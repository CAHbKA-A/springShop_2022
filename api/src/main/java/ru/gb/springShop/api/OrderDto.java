package ru.gb.springShop.api;



import java.math.BigDecimal;
import java.time.LocalDateTime;


public class OrderDto {
    private Long id;
    private String username;
    private BigDecimal totalPrice;
    private LocalDateTime createdAt;
 //   private List<OrderItemDto> orderItems;

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
}
