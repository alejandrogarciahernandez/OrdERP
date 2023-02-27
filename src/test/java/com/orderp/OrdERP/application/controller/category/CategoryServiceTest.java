package com.orderp.OrdERP.application.controller.category;

import com.orderp.OrdERP.application.controller.item.Item;
import com.orderp.OrdERP.application.dao.category.CategoryMapper;
import com.orderp.OrdERP.application.dao.category.CategoryMapperImpl;
import com.orderp.OrdERP.application.dao.category.CategoryRepository;
import com.orderp.OrdERP.application.dao.item.ItemMapper;
import com.orderp.OrdERP.application.dao.item.ItemRepository;
import com.orderp.OrdERP.application.service.category.CategoryService;
import com.orderp.OrdERP.application.service.item.ItemService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {CategoryMapperImpl.class, CategoryService.class})
public class CategoryServiceTest {

    @MockBean
    private CategoryRepository categoryRepository;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CategoryMapper categoryMapper;



    @Test
    void getCategories(){
        Category categorySample = new Category(1L, "category1");
        List<Category> categoryList = new ArrayList<>();
        categoryList.add(categorySample);
        when(this.categoryRepository.findAll()).thenReturn(categoryMapper.categoriesToCategoryEntities(categoryList));
        List<Category> categoryListResult = categoryService.findAll();

        assertEquals(categorySample.getCategoryId(), categoryListResult.get(0).getCategoryId());
        assertEquals(categorySample.getCategoryName(), categoryListResult.get(0).getCategoryName());
    }

    @Test
    void insertCategory(){
        Category category = new Category(null, "category1");
        Category categoryResult = new Category();
        categoryResult.setCategoryId(1l);
        when(categoryRepository.save(Mockito.any())).thenReturn(categoryMapper.categoryToCategoryEntity(categoryResult));

        Long result = categoryService.create(category);

        assertEquals(1l, result);
    }
}
