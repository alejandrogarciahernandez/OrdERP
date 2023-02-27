package com.orderp.OrdERP.application.dao.category;

import com.orderp.OrdERP.application.controller.category.Category;
import com.orderp.OrdERP.application.controller.item.Item;
import com.orderp.OrdERP.application.dao.item.ItemEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface CategoryMapper {

    CategoryEntity categoryToCategoryEntity(Category category);
    Category categoryEntityToCategory(CategoryEntity category);
    List<CategoryEntity> categoriesToCategoryEntities(List<Category> categories);
    List<Category> categoryEntitiesToCategories(List<CategoryEntity> categoryEntities);
}
