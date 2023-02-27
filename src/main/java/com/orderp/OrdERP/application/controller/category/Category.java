package com.orderp.OrdERP.application.controller.category;

import com.orderp.OrdERP.application.controller.item.Item;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import java.util.Set;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class Category {

    private Long categoryId;
    private String categoryName;
}
