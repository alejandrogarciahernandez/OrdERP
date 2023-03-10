package com.orderp.OrdERP.application.controller.item;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.orderp.OrdERP.application.controller.category.Category;
import com.orderp.OrdERP.application.dao.item.ItemRepository;
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
@WebMvcTest(ItemController.class)
@ContextConfiguration(classes = ItemController.class)
@AutoConfigureMockMvc(addFilters = false)
public class ItemControllerTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    private ItemService itemService;

    @MockBean
    private ItemRepository itemRepository;

    @Test
    void getAllItems() throws Exception {
        List<Item> items = new ArrayList<Item>();
        items.add(new Item(1L, "$", 19.99d, "img/imgRoute/img.jpg", "title", "description", new Category(1l, "category1")));
        items.add(new Item(2L, "$", 19.99d, "img/imgRoute/2.jpg", "title2", "description2", new Category(2l, "category2")));
        when(itemService.findAll()).thenReturn(items);
        mockMvc.perform(MockMvcRequestBuilders.get("/items")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(jsonPath("$", hasSize(2))).andDo(print());
    }

    @Test
    void insertItem() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> body = new HashMap<>();
        body.put("id", 1L);
        body.put("currency", "$");
        body.put("price", 19.99);
        body.put("imgRoute", "img/imgRoute/img.jpg");
        body.put("title", "title");
        body.put("description", "description1");
        body.put("category", Map.of("categoryId", 1L, "categoryName", "category1"));

        mockMvc.perform( MockMvcRequestBuilders
                        .post("/item")
                        .content(objectMapper.writeValueAsString(body))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists());
    }

    @Test
    void modifyItem() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> body = new HashMap<>();
        body.put("id", 2L);
        body.put("currency", "$");
        body.put("price", 20.99);
        body.put("imgRoute", "img/imgRoute/img.jpg");
        body.put("title", "title1");
        body.put("description", "description2");
        body.put("category", Map.of("categoryId", 1L, "categoryName", "category1"));

        mockMvc.perform( MockMvcRequestBuilders
                        .put("/item")
                        .content(objectMapper.writeValueAsString(body))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());
    }
}
