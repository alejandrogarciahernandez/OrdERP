package com.orderp.OrdERP.application.controller.item;

import com.orderp.OrdERP.application.controller.category.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class Item {

    private Long id;
    private String currency;
    private Double price;
    private String imgRoute;
    private String title;
    private String description;
    private Category category;
}
