package com.orderp.OrdERP.application.controller.category;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.orderp.OrdERP.application.controller.item.Item;
import com.orderp.OrdERP.application.controller.item.ItemController;
import com.orderp.OrdERP.application.dao.category.CategoryRepository;
import com.orderp.OrdERP.application.dao.item.ItemRepository;
import com.orderp.OrdERP.application.service.category.CategoryService;
import com.orderp.OrdERP.application.service.item.ItemService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest
@ContextConfiguration(classes = CategoryController.class)
@AutoConfigureMockMvc(addFilters = false)
public class CategoryControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private CategoryService categoryService;

    @MockBean
    private CategoryRepository categoryRepository;

    @Test
    void getAllCategories() throws Exception {
        List<Category> categories = new ArrayList<Category>();
        categories.add(new Category(1l, "category1"));
        categories.add(new Category(2l, "category2"));

        when(categoryService.findAll()).thenReturn(categories);
        mockMvc.perform(MockMvcRequestBuilders.get("/categories")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(jsonPath("$", hasSize(2))).andDo(print());
    }

    @Test
    void insertCategory() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> body = new HashMap<>();
        body.put("categoryId", 1L);
        body.put("categoryName", "cat");

        mockMvc.perform( MockMvcRequestBuilders
                        .post("/category")
                        .content(objectMapper.writeValueAsString(body))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists());
    }
}
