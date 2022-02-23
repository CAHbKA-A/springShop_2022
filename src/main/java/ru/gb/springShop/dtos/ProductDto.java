package ru.gb.springShop.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


//типа фильтра, чтобы все подряд неотдавать на фронт. (для безопасности и поджать передаваемую инфу по размеру)
// data transfer object (структура для передачи)
//на фронт всегда отдаем ДТО. Ломая в ДБ необязательные поля, на стороне фронта изминений не будет.
@Data
@NoArgsConstructor
@AllArgsConstructor //нужен json'у, чтобы собрать json объект
public class ProductDto {
    private Long id;
    private String title;
    private int price;
}
