package com.orderp.OrdERP.application.controller.item;


import com.orderp.OrdERP.application.controller.category.Category;
import com.orderp.OrdERP.application.service.item.ItemService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(SpringExtension.class)
@WebMvcTest
@AutoConfigureMockMvc(addFilters = false)
public class ItemControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    private ItemService itemService;

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
}
