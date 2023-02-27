package com.orderp.OrdERP.application.dao.category;

import com.orderp.OrdERP.application.controller.item.Item;
import com.orderp.OrdERP.application.dao.item.ItemEntity;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "CATEGORY")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class CategoryEntity {

    @Id
    @GeneratedValue
    @Column(name = "CATEGORY_ID")
    private Long categoryId;
    @Column(name = "CATEGORY_NAME")
    private String categoryName;
    @OneToMany(cascade= CascadeType.ALL, mappedBy="category")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<ItemEntity> items;

    public CategoryEntity(Long categoryId, String categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }
}
