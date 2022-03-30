package ru.gb.springShop.core.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilterData {
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
    private String textSearch;
    private int page;
}
