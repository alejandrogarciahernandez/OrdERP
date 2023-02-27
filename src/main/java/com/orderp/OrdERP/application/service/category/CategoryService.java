package com.orderp.OrdERP.application.service.category;

import com.orderp.OrdERP.application.controller.category.Category;
import com.orderp.OrdERP.application.controller.item.Item;
import com.orderp.OrdERP.application.dao.category.CategoryMapper;
import com.orderp.OrdERP.application.dao.category.CategoryRepository;
import com.orderp.OrdERP.application.dao.item.ItemMapper;
import com.orderp.OrdERP.application.dao.item.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryMapper categoryMapper;

    public List<Category> findAll() {
        return this.categoryMapper.categoryEntitiesToCategories(this.categoryRepository.findAll());
    }

    public Long create(Category category) {
        return this.categoryRepository.save(this.categoryMapper.categoryToCategoryEntity(category)).getCategoryId();
    }
}
