package com.orderp.OrdERP.application.dao.item;


import com.orderp.OrdERP.application.dao.category.CategoryEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity(name = "ITEM")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ItemEntity {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;
    @Column(name = "CURRENCY")
    private String currency;
    @Column(name = "PRICE")
    private Double price;
    @Column(name = "IMG_ROUTE")
    private String imgRoute;
    @Column(name = "TITLE")
    private String title;
    @Column(name = "DESCRIPTION")
    private String description;
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private CategoryEntity category;
}
