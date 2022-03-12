package ru.gb.springShop.api;


import java.math.BigDecimal;

//элемент корзины
public class CartItemDto {

    private Long productId;
    private String productTitle;
    private int quantity;//количество
    private BigDecimal  pricePerProduct;
    private BigDecimal  price;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal  getPricePerProduct() {
        return pricePerProduct;
    }

    public void setPricePerProduct(BigDecimal  pricePerProduct) {
        this.pricePerProduct = pricePerProduct;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal  price) {
        this.price = price;
    }
}
