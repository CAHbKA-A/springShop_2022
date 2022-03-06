package ru.gb.springShop.api;


//типа фильтра, чтобы все подряд неотдавать на фронт. (для безопасности и поджать передаваемую инфу по размеру)
// data transfer object (структура для передачи)
//на фронт всегда отдаем ДТО. Ломая в ДБ необязательные поля, на стороне фронта изминений не будет.

public class ProductDto {
    private Long id;
    private String title;
    private int price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public ProductDto(Long id, String title, int price) {
        this.id = id;
        this.title = title;
        this.price = price;
    }

    public ProductDto() {
    }
}
